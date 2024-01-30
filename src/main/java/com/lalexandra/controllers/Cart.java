package com.lalexandra.controllers;

import com.lalexandra.model.AbstractModel;
import com.lalexandra.model.CartContent;
import com.lalexandra.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Cart extends CustomServlet{
    private Product product_to_detail;
    public static String PAGE_PATH="/WEB-INF/templates/detail_produit.jsp";
    public static String PAGE_TITLE="";
    private static final long serialVersionUID = 1L;

    private ArrayList<CartContent> cartProducts=new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession clientSession = request.getSession();

        if (clientSession.isNew()) {
            response.sendRedirect("/lalexandra/authenticate");
            return;
        }

        Integer id_client = (Integer) clientSession.getAttribute("id_client");

        if (id_client == null || id_client == 0) {
            request.getRequestDispatcher("/WEB-INF/templates/connect_btn.jsp").forward(request,response);
            return;
        }

        String[] paths = request.getRequestURI().split("/");
        handlePostUri(request,response, paths,id_client);
    }


    private void handlePostUri(HttpServletRequest request,HttpServletResponse response,String[] paths,int id_client){
        if(paths.length>3){
            switch (paths[3]){
                case "addToCart":
                    handleAddToCart(response,request,id_client);
                    break;
                case "removeFromCart":

                    break;
                case "changeQuantity":
                    break;
                case "getCartProducts":
                        refreshCart(request,response);
                    break;
            }
        }
    }

    private void handleAddToCart(HttpServletResponse response,HttpServletRequest request,int id_client){
        int id_prod=Integer.parseInt(request.getParameter("id_prod"));
        int order_quantity=Integer.parseInt(request.getParameter("quantity_prod"));

        order_quantity=(order_quantity<=0)?1:order_quantity;

        if(id_prod>0){
            Product product=new Product(id_prod);
            CartContent cartContent=new CartContent().setProduct(product).setCart_prod_quantity(order_quantity);

            if(product.getQuantity_prod()-order_quantity<0){
                sendJsonMsg(response,"ERR","Quantité choisit invalide");
                return;
            }

            com.lalexandra.model.Cart client_cart=new com.lalexandra.model.Cart(id_client);

            String insertTry=client_cart.addToCart(cartContent);

            if(insertTry== AbstractModel.SUCCESS){
                try {
                    sendConfirmMsg(response,"/lalexandra/details/"+URLEncoder.encode(product.getName_prod(),"UTF-8").trim().replaceAll(" ","-"),URLEncoder.encode("Produit ajouté","UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                if(insertTry== AbstractModel.DUPLICATE_KEY){
                    try {
                        sendFailMsg(response,"/lalexandra/details/"+ URLEncoder.encode(product.getName_prod(),"UTF-8").trim().replaceAll(" ","-"),URLEncoder.encode("Ce produit existe déja dans votre panier","UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    System.out.println("/lalexandra/details/"+product.getName_prod().trim().replaceAll(" ","-"));
                }else{
                    try {
                        sendFailMsg(response,URLEncoder.encode(product.getName_prod(),"UTF-8").trim().replaceAll(" ","-"),"Une erreur s'est produite, produit non ajouté");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

        }else{
            System.err.println("Erreur lors d'un ajout de produit en panier, identifiant invalide");
            sendJsonMsg(response,"ERR","Produit inconnu");
            //sendFailMsg(response,"/lalexandra/details/"+product.getName_prod().replaceAll(" ","-"),"Produit inconnu");
        }
    }
    @Override
    public void renderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    private void refreshCart(HttpServletRequest request,HttpServletResponse response){
        int id_cart=(Integer)request.getSession().getAttribute("id_cart");

        cartProducts=CartContent.getProductListIn(id_cart); //verifier si le retour n'est pas vide

        System.out.println("l'id : "+request.getSession().getAttribute("id_client"));

        try{
            System.out.println(cartProducts.get(0).getProduct().getName_prod());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("cartProducts est vide hein pour l'id : "+id_cart);
        }

        request.setAttribute("cart_products",cartProducts);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/templates/cart_content.jsp");


        try {
            dispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void renderCartInfo(HttpServletRequest request,HttpServletResponse response){
        System.out.println("reçu getCartProducts");
        response.setContentType("html");
        try {
            response.getWriter().println("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
