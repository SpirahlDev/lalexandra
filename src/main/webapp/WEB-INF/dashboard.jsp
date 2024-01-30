<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
     <%
    	String pagePath=(String) request.getAttribute("pagePath");
  		String pageTitle=(String) request.getAttribute("pageTitle");
  		pageTitle=(pageTitle!=null)?pageTitle:"Dashboard";
  %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="/lalexandra/statics/css/style.css">
    <link rel="stylesheet" href="/lalexandra/statics/css/style_ajout.css">
    <script src="/lalexandra/statics/js/jquery.js"></script>

    <title><%= pageTitle%></title>
</head>
<body>

      <jsp:include page="/WEB-INF/templates/navbar_dashboard.jsp" />
      
        <% if(pagePath!=null){%>
        	<jsp:include page="<%= pagePath %>"/>
        <%}else{%>
        	<jsp:include page="/WEB-INF/templates/home_dashboard.jsp"/>
        <% }%>
      <script src="/lalexandra/statics/js/adding_scriptV3.js"></script>
</body>
</html>