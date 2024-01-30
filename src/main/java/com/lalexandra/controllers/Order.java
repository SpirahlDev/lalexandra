package com.lalexandra.controllers;

import com.lalexandra.model.Cart;
import com.lalexandra.model.CartContent;
import com.lalexandra.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static com.lalexandra.model.CartContent.getProductListIn;

@WebServlet("/lalexandra/checkout")
public class Order extends CustomServlet{
    public final static float TAXE=1000;
    public final static String PAGE_PATH="/WEB-INF/templates/checkout.jsp";
    public final static String PAGE_TITLE="Récapitulatif";
    private int id_cart;

    public Order(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession clientSession = request.getSession();
        if (clientSession.isNew()) {
            response.sendRedirect("/lalexandra/authenticate");
            return;
        }

        Integer id_client = (Integer) clientSession.getAttribute("id_client");

        if (id_client == null || id_client == 0) {
            response.sendRedirect("/lalexandra/authenticate");
            return;
        }
        id_cart=(Integer)clientSession.getAttribute("id_cart");

        renderPage(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void renderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagePath", PAGE_PATH);
        request.setAttribute("pageTitle",PAGE_TITLE);
        request.setAttribute("PRODUCTS_PRICE", Cart.getCartPriceInfo(id_cart).get("TOTAL_PRODUCT"));
        request.setAttribute("TOTAL", Cart.getCartPriceInfo(id_cart).get("TOTAL"));

        super.renderPage(request,response);
    }

    private void handlePostUri(HttpServletRequest request,HttpServletResponse response,String[] paths,int id_client){
        if(paths.length>3){
            switch (paths[3]){
                case "processOrder":

                    break;
            }
        }
    }

    private void processFunction(HttpServletResponse response,HttpServletRequest request){

    }

}
