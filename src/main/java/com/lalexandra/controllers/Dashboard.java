package com.lalexandra.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.lalexandra.helper.ParamReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

//@WebServlet("/lalexandra/dashboard")
public class Dashboard extends CustomServlet{
    private static String pagePath="/WEB-INF/dashboard.jsp";
	private static final long serialVersionUID = 1L;
	public static String pageTitle="Dashboard";

    
	public Dashboard() {
		super(false);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getRequestURI();
		String[] pathArray=path.split("/");
		
		try {
			handleUri(request, response, pathArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		String path = request.getRequestURI();
		System.out.println("un truc :"+request.getParameter("name_prod"));
		String[] pathArray=path.split("/");
		
		
		try {
			handleAction(request,response,pathArray);
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void renderPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        request.setAttribute("pageTitle",pageTitle);
        request.setAttribute("pagePath", pagePath);
        
        try {
            this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}

	private void handleUri(HttpServletRequest request, HttpServletResponse response, String[] pathes) throws SQLException{
		if(pathes.length>3) {
			
			switch (pathes[3]) {
			case "add-product":
				loadAddingPage(request,response);
				break;
				
			case "list-order":
				
				break;
			default:
				loadHome(request,response);
				break;
			}
			
			
		}else {
			loadHome(request,response);
		}
	}

	private void handleAction(HttpServletRequest request, HttpServletResponse response, String[] pathes) throws SQLException, IOException, ServletException {
		if(pathes.length>3) {
				
				switch (pathes[3]) {
				case "new-product":
					handleAddProduct(request,response);
					
					break;
					
				case "list-order":
					
					break;
				default:
					loadHome(request,response);
					break;
				}

			}else {
				loadHome(request,response);
			}
	}
    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
			Map<String, Object> data = new HashMap<>();
            data=ParamReader.getAddproduct_params(request, response);
           
            
            System.out.println("un truc :"+request.getParameter("name_prod"));
//            sendJsonMsg(response,"ERR",data.get("name_prod").toString());
//            Part filePart = request.getPart("imageURI_prod");
//			String fileName = data.get("fileName") + "." + getFileExtension(extractFileName(filePart));
//			
//			if(!handleFile(filePart,fileName)){
//				sendJsonMsg(response, "ERR", "Erreur de fichier");
//				return;
//			}
//            String imageURI_prod = IMAGE_URL + "/" + fileName;
//    
//            data.put("imageURI_prod", imageURI_prod);
//
//			Product product = new Product();
//			
//			Category cat=new Category(Integer.valueOf((String) data.get("id_category")));
//			product.setCategory(cat);
//			product.setData(data);
//			
//			if(product.save()){
//				sendJsonMsg(response,"ERR","Réussi");
//			}else{
//				sendJsonMsg(response,"ERR","Echec de l'ajout du produit");
//			}

        } catch (NumberFormatException e) {
            sendJsonMsg(response, "ERR", "Erreur de format dans les champs numériques");
            System.out.println("Erreur de format dans les champs numériques");
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Une erreur s'est produite lors de l'ajout du produit");
            sendJsonMsg(response, "ERR", "Une erreur s'est produite lors de l'ajout du produit");
        }
    }


	private boolean handleFile(Part filePart, String fileName){

		try(InputStream fileContent = filePart.getInputStream()){
			Path filePath = Paths.get(IMAGE_LOCAL_STORE, fileName);
			Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
			return true;
		}catch(Exception e){
			
		}
		return false;
	}
	
	private void loadHome(HttpServletRequest request, HttpServletResponse response) {
		pagePath="/WEB-INF/templates/home_dashboard.jsp";
		try {
			renderPage(request,response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadAddingPage(HttpServletRequest request, HttpServletResponse response) {
		pagePath="/WEB-INF/templates/add_product.jsp";
		try {
			renderPage(request,response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
