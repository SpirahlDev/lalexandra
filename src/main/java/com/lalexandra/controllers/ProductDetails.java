package com.lalexandra.controllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.lalexandra.helper.ControllerHelper;
import com.lalexandra.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//@WebServlet("/lalexandra/details")
public class ProductDetails extends CustomServlet {
	private Product product_to_detail;
	public static String PAGE_PATH="/WEB-INF/templates/detail_produit.jsp";
	public static String PAGE_TITLE="Détails produit";
	private static final long serialVersionUID = 1L;


	public ProductDetails() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathes=request.getRequestURI().split("/");
		handleURI(request,pathes);
		renderPage(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	@Override
	public void renderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pagePath", PAGE_PATH);
		String title=(product_to_detail!=null)?product_to_detail.getName_prod():PAGE_TITLE;
		request.setAttribute("pageTitle",title);

		request.setAttribute("collections", collectionList);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void handleURI(HttpServletRequest request,String[] pathes) {
		if (pathes.length > 3) {
			String productName= null;

			try {
				productName = URLDecoder.decode(pathes[3], "UTF-8");
				System.out.println("Dans product details :"+productName);
				productName=sanitizeInput(productName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.println("Erreur decodage URL");
				System.err.println(e.getMessage());
			}

			if(productName!=null && !productName.isEmpty()){
				productName=productName.trim().replaceAll("-"," ");
				System.out.println("Cherche :"+productName);
				product_to_detail=Product.getProductByName(productName);

				request.setAttribute("product_found",product_to_detail);
			}

		}

	}

	private void handlePostUri(HttpServletRequest request,String[] paths){
		if(paths.length>3){
			switch (paths[3]){
				case "addToCart":
					handleAddToCart(request);
					break;
				case "sign":

					break;
				case "disconnect":

					break;
			}
		}
	}

	private void handleAddToCart(HttpServletRequest request){
		int id_prod=Integer.parseInt(request.getParameter("id_prod"));
		if(id_prod>0){
			Product product=new Product(id_prod);
		}else{
			System.err.println("Erreur lors d'un ajout de produit en panier, identifiant invalide");
		}
	}
}