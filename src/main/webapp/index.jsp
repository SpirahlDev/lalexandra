 <%@ page import="java.util.ArrayList" %>
<%@ page import="com.lalexandra.model.Category" %>
<%@ page import="com.lalexandra.controllers.CustomServlet" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <%
    	String pagePath=(String) request.getAttribute("pagePath");
  		String pageTitle=(String) request.getAttribute("pageTitle");
  		pageTitle=(pageTitle!=null)?pageTitle:"Acceuil";
  		
  		/* ArrayList<Category> collections = (ArrayList<Category>) request.getAttribute("collections"); */
  		/* pageContext.setAttribute("collections", collections); */
  %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <jsp:include page="/WEB-INF/templates/head-content.jsp"/>
    <title>
    <%= pageTitle%>
    </title>
</head>
<body>
  
        <jsp:include page="/WEB-INF/templates/nav_bar.jsp" />
    <main class="main-wrapper">
        <!-- CONTENU ICI -->
        
        <% if(pagePath!=null){%>
        	<jsp:include page="<%= pagePath %>"/>
        <%}else{%>
        	<jsp:include page="/WEB-INF/templates/home.jsp"/>
        <% }%>
    </main>
    
     <jsp:include page="/WEB-INF/templates/footer.jsp" />
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" type="text/javascript"></script>
  <script src="/lalexandra/statics/js/webflow.js" type="text/javascript"></script>
  <script src="/lalexandra/statics/js/custom.js" type="text/javascript"></script>

   <div id="shape-notify">
        <div id="content-notify">
            <p></p>
            <div id="close-btn-notify">&times;</div>
        </div>
    </div>
    <script src="/lalexandra/statics/js/popUp.js"></script>

    <script>
        <%

            String confirm=(String)request.getParameter("confirm");
            String alert=(String)request.getParameter("alert");
            String statusCode="ERR";

            String notify_func="";
            if(confirm!=null){
                notify_func="displayNotif(\""+confirm.replaceAll("<[^>]*>", "")+"\",\"SUC\",5000);";
            }else if(alert!=null){
                notify_func="displayNotif(\""+alert.replaceAll("<[^>]*>", "")+"\",\"ERR\",5000);";
            }
        %>
        <%=notify_func%>
    </script>

</body>
</html>