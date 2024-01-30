<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lalexandra.model.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
ArrayList<Product> list_product = (ArrayList<Product>) request.getAttribute("productList"); 
%>
<section class="section-home-product">
    <div class="page-padding">
      <div class="container-large">
        <div class="padding-bottom padding-large">
          <div class="margin-bottom margin-xxlarge">
            <div class="container-small">
              <div class="text-align-center z-index-1">
                <div class="margin-bottom margin-xsmall">
                  <div class="tagline">Best sellers</div>
                </div>
                <h2>Produits les plus populaires</h2>
              </div>
            </div>
          </div>
          <div class="w-dyn-list">
            <div role="list" class="home-product-list w-dyn-items"> 
              <% 
              for (Product product : list_product) {
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
                 <!-- CARTES PRODUIT ICI -->
                <!-- FIN -->
            </div>

          </div>
        </div>
      </div>
    </div>
  </section>