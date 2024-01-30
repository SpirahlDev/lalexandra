<%@ page import="com.lalexandra.model.Category" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    ArrayList<Category> collections = (ArrayList<Category>) request.getAttribute("collections");

	Category category_choiced =(Category)request.getAttribute("category_choice");
	String description_category=category_choiced.getDescription();
	String category_name=category_choiced.getName();
%>
  <div class="page-wrapper">
    <main class="main-wrapper">
      <section class="section-product-category">
        <div class="page-padding">
          <div class="container-large z-index-1">
            <div class="padding-vertical padding-xhuge">
              <div class="margin-bottom margin-xxlarge">
                <div class="margin-bottom margin-xsmall">
                  <h1><%=category_name%></h1>
                </div>
                <div><%=description_category%></div>
              </div>
              <div class="product-category-component">
                <div class="product-categories-wrapper">
                  <div class="margin-bottom margin-small">
                    <div class="heading-small">Categories</div>
                  </div>
                  <div class="w-dyn-list">
                    <div role="list" class="w-dyn-items">
                      <%
                            for (Category category : collections) {
                        %>
                            <div role="listitem" class="product-category-item w-dyn-item">
                               <a href="/lalexandra/collection/<%= category.system_name() %>" class="product-category-item-link w-inline-block custom-a">
                                 <div><%= category.getName() %></div>
                               </a>
                             </div>
                        <%
                            }
                     %>

                    </div>
                   
                  </div>
                </div>
                <!-- BLOCK LISTE PRODUIT ICI -->
                <jsp:include page="/WEB-INF/templates/block_list_produit.jsp" />
                <!-- FIN -->
              </div>
            </div>
          </div>
        </div><img src="/lalexandra/statics/images/Pink-Graphic.svg" loading="lazy" alt="" class="product-category-graphic">
      </section>
    </main>

  </div>
