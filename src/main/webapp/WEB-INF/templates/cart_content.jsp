<%@ page import="com.lalexandra.model.CartContent" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    ArrayList<CartContent> cart_contents=(ArrayList<CartContent>) request.getAttribute("cart_products");
%>

<% for (CartContent content : cart_contents) {%>
    <jsp:include page="/WEB-INF/templates/cart_item.jsp" >
      <jsp:param name="name_prod" value="<%= content.getProduct().getName_prod() %>" />
      <jsp:param name="imageURI_prod" value="<%= content.getProduct().getImageURI_prod() %>" />
      <jsp:param name="price_prod" value="<%= content.getProduct().getFloatPrice() %>" />
      <jsp:param name="cart_prod_quantity" value="<%= content.getCart_prod_quantity() %>" />
    </jsp:include>
<%}%>

