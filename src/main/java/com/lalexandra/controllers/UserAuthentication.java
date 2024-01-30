package com.lalexandra.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.lalexandra.model.Client;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UserAuthentication extends CustomServlet{
    private static final String DEFAULT_SIGNING_PAGE ="/WEB-INF/templates/sign_in.jsp",DEFAULT_SIGNUP_PAGE="/WEB-INF/templates/sign_up.jsp";
    private static final String SIGNUP_TITLE="S'inscrire",SIGNIN_TITLE="Se connecter";
    public static String pageTitle="Se Connecter",pagePath= DEFAULT_SIGNING_PAGE;
    private static final long serialVersionUID = 1L;

    public UserAuthentication() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String[] pathes=request.getRequestURI().split("/");
        handleURI(request,pathes);
        request.setCharacterEncoding("UTF-8");
        renderPage(request,response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String[] pathes=request.getRequestURI().split("/");
        handlePostUri(request,response,pathes);
    }

    @Override
    public void renderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagePath",pagePath);
        request.setAttribute("collections",this.collectionList);
        request.setAttribute("pageTitle",pageTitle);
        try {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void handleURI(HttpServletRequest request,String[] pathes) {
        if (pathes.length > 3) {
           switch (pathes[3]){
               case "sign-in"://se connecter
                    pagePath= DEFAULT_SIGNING_PAGE;
                   break;
               case "sign-up"://s'inscrire
                   pagePath=DEFAULT_SIGNUP_PAGE;
                   pageTitle=SIGNUP_TITLE;
                   break;
           }

        }

    }

    private void handlePostUri(HttpServletRequest request,HttpServletResponse response,String[] pathes){
        switch (pathes[3]){
            case "connect":
                handleConnection(request,response);
                break;
            case "sign-up":
                handleSignUp(request,response);
                break;
            case "disconnect":

                break;
        }
    }

    private void handleConnection(HttpServletRequest request,HttpServletResponse response){
        String mail_client=(String)request.getParameter("mail_client");
        String sent_password=(String)request.getParameter("password_client");

        if(mail_client==null || sent_password==null){
            sendFailMsg(response,"/lalexandra/authenticate/sign-in","Champs incorrectement remplis");
            return;
        }

        Client client=Client.getClientByEmail(mail_client);

        if(client!=null && client.getClientId()>0){
            String hashed_password=client.getPassword();

            if(verifyPassword(sent_password,hashed_password)){
                setClientSession(request,client);

                //rediriger le client vers la page ayant reclamer la connexion, stocké dans sa session
                HttpSession clientSession=request.getSession();
                String redirectPage=(String) clientSession.getAttribute("REDIRECT_TO");//obtention de l'URL de la page
                if(redirectPage!=null){//si la page existe, redirection vers celle-ci
                    removeRedirectTo(request);
                    sendConfirmMsg(response,redirectPage,"Bienvenue !");
                }else{//redirection vers la page d'accueil
                    sendConfirmMsg(response,"/lalexandra/home","Bienvenue "+client.getFirstname());
                }
            }else{
                sendFailMsg(response,"/lalexandra/authenticate/sign-in","Mot de passe incorrecte");
            }

        }else{
            sendFailMsg(response,"/lalexandra/authenticate/sign-in","Compte introuvable");
        }
    }

    public void handleSignUp(HttpServletRequest request,HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        if(!isOneEmpty(request)){
            String name_client=request.getParameter("name_client");
            String firstname_client=request.getParameter("firstname_client");
            String mail_client=request.getParameter("mail_client");
            String phonenumber_client=request.getParameter("phonenumber_client");
            String password_client=request.getParameter("password_client");

            Map<String, Object> paramMap = new HashMap<>();

            paramMap.put("name_client", (Object) name_client);
            paramMap.put("firstname_client", (Object) firstname_client);
            paramMap.put("mail_client", (Object) mail_client);
            paramMap.put("phonenumber_client", (Object) phonenumber_client);
            paramMap.put("password_client", (Object) password_client);

            Client client=new Client();
            client.setData(paramMap);

            String feedbackMsg="Echec";

            try{
                if(client.save()){
                    feedbackMsg=SUCCESS_GET+"="+URLEncoder.encode("Vous êtes désormais un de nos client","UTF-8");
                }else{
                    feedbackMsg=FAIL_GET+"="+URLEncoder.encode("Inscription impossible","UTF-8");
                }
            }catch (Exception e){
                e.printStackTrace();
            }


            HttpSession clientSession=request.getSession();
            String REDIRECT_TO=(String)clientSession.getAttribute("REDIRECT_TO");
                try{
                    if(REDIRECT_TO!=null){
                        response.sendRedirect(REDIRECT_TO+"?"+ feedbackMsg);
                    }else{
                        response.sendRedirect(getRefererUri(request)+"?"+feedbackMsg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

        }else{
            try{
                response.sendRedirect("/lalexandra/authenticate");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
