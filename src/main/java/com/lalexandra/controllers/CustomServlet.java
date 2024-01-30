package com.lalexandra.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.lalexandra.model.Category;
import com.lalexandra.model.Client;
import com.lalexandra.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;


/**
 * AbstractController
 */
public abstract class CustomServlet extends HttpServlet {
	public static final String SUCCESS_GET ="confirm";
	public static final String FAIL_GET ="alert";
	public static final String IMAGE_LOCAL_STORE="C:\\Users\\user\\eclipse-workspace\\lalexandra\\src\\main\\webapp\\statics\\images\\products";
	public static final String IMAGE_URL="/lalexandra/statics/images/products";
	public static String pageTitle="";
    protected ArrayList<Category> collectionList=new ArrayList<>();
    protected ArrayList<Product> productList=new ArrayList<>();
    

	private static final long serialVersionUID = 1L;

    public CustomServlet(){
        super();
        this.collectionList=Category.getCategories(0);
    }
    
    public CustomServlet(boolean withCategories) {
    	super();
    }
    
    protected void loadProd(int limit) {
    	try {
			this.productList=Product.getProductList(1, limit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible de charger les produit par limit");
			e.printStackTrace();
		}
    }
 
	
	public static Map<String, String> getPostValues(String formData){

		String[] dataArray=formData.split("&");
		Map<String, String> keyValue=new HashMap<>();
		
		for(String formField:dataArray) {
			String[] keyVal=formField.split("=");
			if(keyVal.length==2){
				keyValue.put(keyVal[0], keyVal[1]);				
			}
		}
		
		return keyValue;
	}

	public void renderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("collections", collectionList);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}


    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }

	public void sendJsonMsg(HttpServletResponse response, String status, String msg){
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", status);
		errorResponse.put("message", msg);
	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	
		try (PrintWriter out = response.getWriter()) {
			out.println(new Gson().toJson(errorResponse));
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	protected String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }

	public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return null; // Pas d'extension trouvée
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
    
	private void sendJsonResponse(HttpServletResponse response, Map<String, Object> data) throws IOException {
		
	}

	public static String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	public static boolean verifyPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}

	public void setRedirectTo(HttpServletRequest request){
		String pathWithoutHost=getRefererUri(request);
		request.getSession().setAttribute("REDIRECT_TO",pathWithoutHost);
	}

	public String getRefererUri(HttpServletRequest request){
		String referer = request.getHeader("referer");
		try{
			java.net.URL url = new java.net.URL(referer);
			String pathWithoutHost = url.getPath();
			return pathWithoutHost;
		}catch (MalformedURLException e){
			System.out.println("Url impossible à obtenir");
			e.printStackTrace();
		}
		return null;
	}
	public void removeRedirectTo(HttpServletRequest request){
		HttpSession clientSession=request.getSession();
		String page=(String) clientSession.getAttribute("REDIRECT_TO");

		if(!clientSession.isNew() && page!=null){
			clientSession.removeAttribute("REDIRECT_TO");
		}
	}

	public boolean isOneEmpty(HttpServletRequest request){
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String[] paramValues = entry.getValue();

			for (String paramValue : paramValues) {
				if (paramValue == null || paramValue.isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

	public static String sanitizeInput(String input) {
		if (input == null) {
			return null; // Si l'entrée est null, retourner null
		}
		// Suppression des balises HTML à l'aide d'une expression régulière
		return input.replaceAll("\\<.*?\\>", "");
	}

	public static void sendConfirmMsg(HttpServletResponse response,String pagePath,String msg){
		response.setCharacterEncoding("UTF-8");
		if(pagePath.isEmpty()){
			pagePath="/lalexandra/home";
		}

		if(msg.isEmpty()){
			msg="Succès";
		}

		try {
			response.sendRedirect(pagePath+"?"+SUCCESS_GET+"="+msg);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void sendFailMsg(HttpServletResponse response,String pagePath,String msg){
		response.setCharacterEncoding("UTF-8");
		//response.setStatus(HttpServletResponse.SC_FOUND);
		if(pagePath.isEmpty()){
			pagePath="/lalexandra/home";
		}

		if(msg.isEmpty()){
			msg="Echec";
		}

		try {
			response.sendRedirect(pagePath+"?"+FAIL_GET+"="+msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setClientSession(HttpServletRequest request, Client client){
		HttpSession clientSession=request.getSession();
		clientSession.setAttribute("id_client",client.getClientId());
		clientSession.setAttribute("name_client",client.getName());
		clientSession.setAttribute("firstname_client",client.getFirstname());
		clientSession.setAttribute("mail_client",client.getMail());
		clientSession.setAttribute("id_cart",client.getId_cart());
	}

	public void removeClientSession(HttpServletRequest request){
		HttpSession clientSession=request.getSession();
		clientSession.removeAttribute("id_client");
		clientSession.removeAttribute("name_client");
		clientSession.removeAttribute("firstname_client");
		clientSession.removeAttribute("mail_client");
		clientSession.removeAttribute("id_cart");
	}
}