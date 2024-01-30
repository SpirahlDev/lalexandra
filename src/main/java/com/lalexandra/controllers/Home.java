package com.lalexandra.controllers;


import java.io.IOException;

import com.lalexandra.helper.ControllerHelper;
import com.lalexandra.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Home extends CustomServlet{
	public static final String pageTitle="Accueil";
	
	private static final long serialVersionUID = 1L;
       
  
    public Home() {
        super();
        loadProd(3);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(!productList.isEmpty()) {
        	Product pr=productList.get(0);
        	System.out.println(pr.getName_prod());
        }else {
        	System.out.println("toujours rien");
        }
		renderPage(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void renderPage(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("collections",this.collectionList);

		request.setAttribute("productList", productList);
		request.setAttribute("pageTitle",pageTitle);
		request.setAttribute("pagePath", "/WEB-INF/templates/home.jsp");
		
		try {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
