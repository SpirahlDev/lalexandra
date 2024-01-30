package com.lalexandra.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;

import com.lalexandra.helper.ControllerHelper;
import com.lalexandra.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DataReceiverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        // Récupération des données de la requête POST
        String name = request.getParameter("test_val");
        String email = request.getParameter("mail_client");

        // Affichage des données reçues
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Données reçues via POST iiiiiii:</h2>");
        out.println("<p>Nom : " + name + "</p>");
        out.println("<p>Email : " + email + "</p>");
        out.println("</body></html>");
    }
}
