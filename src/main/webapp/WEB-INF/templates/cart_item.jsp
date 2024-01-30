<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String imageURI_prod=(String)request.getParameter("imageURI_prod");
    imageURI_prod=(imageURI_prod!=null)?imageURI_prod:"default_img";

    String name_prod=(String)request.getParameter("name_prod");
    String sys_name=name_prod.trim().replaceAll(" ","-");
    
    String unit_prod_price=request.getParameter("price_prod");
    String cart_prod_quantity=request.getParameter("cart_prod_quantity");

    Float price=Float.parseFloat(unit_prod_price)*Float.parseFloat(cart_prod_quantity);
%>

<div class="w-commerce-commercecartitem cart-item async-cart-action">
    <div class="cart-image-wrapper">
        <img src="/lalexandra/<%= imageURI_prod%>" 
        data-wf-bindings="ImageRef" alt="" class="w-commerce-commercecartitemimage cart-image w-dyn-bind-empty"/>
    </div>
    <div class="w-commerce-commercecartiteminfo">
        <div class="margin-bottom margin-tiny">
            <div data-wf-bindings="product.f_name_" class="w-commerce-commercecartproductname text-size-medium text-weight-medium text-color-neutral-800 w-dyn-bind-empty"><a href="/lalexandra/details/<%= sys_name%>"><%= name_prod%></a></div>
        </div>

        <div class="margin-bottom margin-tiny">
            <div data-wf-bindings="product.f_summary_" class="w-dyn-bind-empty">Prix unitaire : <%=unit_prod_price%> FCFA</div>
        </div>
        <input type="hidden" name="id_prod" value="">
        <div class="margin-bottom margin-tiny">
            <div data-wf-bindings="product.f_summary_" class="w-dyn-bind-empty">Prix Total : <span class="total-price-product"><%=price%></span>  FCFA</div>
        </div>
     
        <button role="button" class="remove-button w-inline-block remove-product-btn" data-wf-cart-action="remove-item" data-commerce-sku-id="" aria-label="Remove item from cart">
           Retirer
        </button>
    </div>
    <input type="number" class="w-commerce-commercecartquantity cart-quantity" required="" pattern="^[0-9]+$" inputMode="numeric" name="quantity" autoComplete="off" data-wf-cart-action="update-item-quantity" data-commerce-sku-id="" value="<%=cart_prod_quantity%>"/>
</div>