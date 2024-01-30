<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lalexandra.model.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    ArrayList<Product> productList=(ArrayList<Product>)request.getAttribute("productList");
%>
<div class="product-category-list-wrapper w-dyn-list">

    <div role="list" class="product-category-list w-dyn-items">
        <!-- CARTES PRODUIT ICI -->
             <%
                         for (Product product : productList) {
                     %>
                             <jsp:include page="/WEB-INF/templates/product_card.jsp" >
                               <jsp:param name="name_prod" value="<%= product.getName_prod() %>" />
                               <jsp:param name="imageURI_prod" value="<%= product.getImageURI_prod() %>" />
                               <jsp:param name="price_prod" value="<%= product.getPrice_prod() %>" />
                               <jsp:param name="description_prod" value="<%= product.getDescription_prod() %>" />
                             </jsp:include>
                     <%
                         }
                     %>
        <!-- FIN -->
    </div>

    <!-- SI AUCUN PRODUIT TROUVE -->
    <%if(productList==null){%>
       <div class="w-dyn-empty">
      <div>Pas de produit trouvé</div>
    </div>
    <%}%>
</div>