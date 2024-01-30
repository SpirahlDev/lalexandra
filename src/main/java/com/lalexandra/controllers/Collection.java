package com.lalexandra.controllers;

import java.util.ArrayList;

import java.io.IOException;

import com.lalexandra.helper.ControllerHelper;
import com.lalexandra.model.Category;
import com.lalexandra.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Collection extends CustomServlet{
	private static final String[] collectionsName={""};

	private static final long serialVersionUID = 1L;

	private int specific_collection_id=0;
       
	public static final String pageTitle="Collection";

	private ArrayList<Product> productList=new ArrayList<>();
	

    public Collection() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		String[] pathArray=path.split("/");
		
		try {
			handleUri(request,pathArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		renderPage(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	@Override
	public void renderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("productList",this.productList);
		request.setAttribute("pagePath", "/WEB-INF/templates/detail_category.jsp");
		request.setAttribute("pageTitle",pageTitle);
		request.setAttribute("collections", collectionList);
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

	private void handleUri(HttpServletRequest request,String[] pathes) throws Exception{
		if(pathes.length>3) {
			switch (pathes[3]) {
				case "all-products":
					this.productList=Product.getProductList(0);
					break;
				case "body-care":
					specific_collection_id=1;
					this.productList=Product.getProductList(1);
					break;
				case "facial-care":
					specific_collection_id=2;
					this.productList=Product.getProductList(2);
					break;
				case "hair-care":
					specific_collection_id=3;
					this.productList=Product.getProductList(3);
					break;
				case "makeup":
					specific_collection_id=4;
					this.productList=Product.getProductList(4);
					break;
				case "perfumes":
					specific_collection_id=5;
					this.productList=Product.getProductList(5);
					break;
				case "research":
					handleResearch(request);
					break;
				default:
					this.productList=Product.getProductList(specific_collection_id);
					break;
			}

			if(!pathes[3].equals("all-products") && !pathes[3].equals("research")){
				loadSpecificCollection(request);
			}else if(pathes[3].equals("research")){
				loadResearchTerm(request);
			}else{
				loadAllCollection(request);
			}

		}else {
			loadAllCollection(request);
		}

		

		
	}


	private void loadAllCollection(HttpServletRequest request) {
		Category cat=new Category(1);
		request.setAttribute("category_choice",cat);
		
	}
	private void loadSpecificCollection(HttpServletRequest request){
		if(!productList.isEmpty()) {
			request.setAttribute("category_choice",productList.get(0).cat);
		}else {
			System.out.println("Liste de produit vide");
			Category cat=new Category(specific_collection_id);
			request.setAttribute("category_choice",cat);
		}
	}

	private void loadResearchTerm(HttpServletRequest request){
		Category category=new Category();
		category.setName_category("Résultat de recherche");
		category.setDescription_category("Produits correspondant");
		request.setAttribute("category_choice",category);
	}
	
	private void handleResearch(HttpServletRequest request){
		String searchPattern=sanitizeInput(request.getParameter("searchPattern"));

			System.out.println("Recherche :"+searchPattern);
		if(searchPattern!=null && searchPattern.length()>=3){
			System.out.println("searchPattern :"+searchPattern);
			this.productList=Product.searchProduct(searchPattern);
		}
	}
}
