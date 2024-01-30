<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String name=(String)request.getParameter("name_category");
	String image=(String)request.getParameter("image_category");
	String system_name=(String)request.getParameter("system_name");
	String description_category=(String)request.getParameter("description_category");
%>

  <!-- Element de liste -->
  <div role="listitem" class="w-dyn-item">
    <div class="navbar-dropdown-link-list">
      <a href="/lalexandra/collection/<%= system_name%>" class="navbar-dropdown-link w-inline-block">
        <div id="w-node-_52602f76-9c80-81bf-9e6e-e820b59bf1af-b59bf198" class="navbar-icon-wrapper"><img src="/lalexandra/<%=image %>" loading="lazy" alt="" class="megamenu-image"></div>
        <div class="navbar-item-right">
          <div class="text-weight-semibold text-color-neutral-900"> <%=name %></div>
          <p class="text-size-small">
          	<%=description_category %>
          </p>
        </div>
      </a>
    </div>
  </div>
  <!-- FIN d'element -->