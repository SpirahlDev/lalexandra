package com.lalexandra.helper;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public interface ControllerHelper {
	
	public static final String pageTitle="";
	
	default Map<String, String> getPostValues(String formData){

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
	public static String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	public static boolean verifyPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}


	public void renderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
}
