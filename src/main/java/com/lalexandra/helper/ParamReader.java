package com.lalexandra.helper;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;



public class ParamReader {
	public static Map<String,Object> getAddproduct_params(HttpServletRequest request, HttpServletResponse response){
		
	    Map<String, Object> data = new HashMap<>();
	    
	    String name_prod_str = request.getParameter("name_prod");
	    
	    String description_prod = request.getParameter("description_prod");
	    String price_prod_str = request.getParameter("price_prod");
	    String quantity_prod_str = request.getParameter("quantity_prod");
	    String id_category_str = request.getParameter("id_category");

	    float price_prod = 0.0f; // Valeur par défaut si la conversion échoue
	    int quantity_prod = 0;
	    int id_category = 0;
	    String fileName = "";

	    if (price_prod_str != null && !price_prod_str.isEmpty()) {
	        price_prod = Float.parseFloat(price_prod_str);
	    }
	    if (quantity_prod_str != null && !quantity_prod_str.isEmpty()) {
	        quantity_prod = Integer.parseInt(quantity_prod_str);
	    }
	    if (id_category_str != null && !id_category_str.isEmpty()) {
	        id_category = Integer.parseInt(id_category_str);
	    }

	    fileName = name_prod_str + "-" + price_prod;

	    data.put("name_prod", name_prod_str);
	    data.put("description_prod", description_prod);
	    data.put("price_prod", price_prod);
	    data.put("quantity_prod", quantity_prod);
	    data.put("id_category", id_category);
	    data.put("imageURI_prod", fileName);

	    return data;
	}

}
