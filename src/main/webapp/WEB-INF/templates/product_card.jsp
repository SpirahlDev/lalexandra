<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  String name=(String)request.getParameter("name_prod");
  String sys_name=name.trim().replaceAll(" ","-");
	String image=(String)request.getParameter("imageURI_prod");
	String price_prod=(String)request.getParameter("price_prod");
	String description_prod=(String)request.getParameter("description_prod");
	
	 if (description_prod != null && description_prod.length() > 35) {
	        description_prod = description_prod.substring(0, 35);
	  }
  
%>

 <div role="listitem" class="card w-dyn-item">
    <a href="/lalexandra/details/<%=sys_name%>" class="home-product-image-wrapper w-inline-block">
      <img src="/lalexandra/<%=image%>" loading="lazy" alt="" class="home-product-image">
    </a>
    <!-- PARTIE INFO DE PRODUIT -->
      <div class="home-product-content-wrapper">
        <div class="home-product-content">
          
          <div class="home-product-row">
            <a href="#" class="heading-xsmall"><%=name%></a>
            <div class="home-product-price">
              <div class="text-weight-medium"><%=price_prod%> FCFA</div>
            </div>
          </div>

          <div class="text-size-small"><%=description_prod%>...</div>
        </div>
        <div class="margin-top margin-small">
          <a href="/lalexandra/details/<%=sys_name%>" class="button-secondary max-width-full w-button">Voir plus</a>
        </div>
      </div>
    <!-- FIN -->
  </div>
<!-- FIN -->