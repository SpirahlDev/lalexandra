package com.lalexandra.controllers;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lalexandra.helper.*;
import com.lalexandra.model.Category;
import com.lalexandra.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/lalexandra/test")
public class Test extends CustomServlet{
	private static final long serialVersionUID = 1L;
       
    
    public Test() {
        super();
        loadProd(3);
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	 	String param2=request.getHeader("referer");
		setRedirectTo(request);

	        
		PrintWriter toClient=response.getWriter();

		toClient.println(request.getSession().getAttribute("REDIRECT_TO"));
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
	     	BufferedReader reader = request.getReader();
	     	Map<String, String> postValues=new HashMap<>();
	
	        StringBuilder requestBody = new StringBuilder();

	   
	        String line;
	        while ((line = reader.readLine()) != null) {
	            requestBody.append(line);
	        }
	        
	        postValues=getPostValues(requestBody.toString());
	        PrintWriter toClient=response.getWriter();
	        
	        String name=postValues.get("name_prod");
	        String firstName=postValues.get("price_prod");
	        
//	        if(name!=null) {
//	        	toClient.println(postValues.get("id_category"));
//	        }
//	        
//	        if(firstName!=null) {
//	        	toClient.println(postValues.get("description_prod"));	     	        	
//	        }
	
	        
	        
	        System.out.println("Paramètre : " );
	        Map<String, String[]> params = request.getParameterMap();
	        for (Map.Entry<String, String[]> entry : params.entrySet()) {
	            String paramName = entry.getKey();
	            String[] paramValues = entry.getValue();
	            
	            System.out.println("Valeur(s) : ");
	            for (String paramValue : paramValues) {
	            	System.out.println("- " + paramValue);
	            }
	            System.out.println("------");
	        }
	}




	@Override
	public void renderPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
