<%@ page import="com.lalexandra.model.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Product prod=(Product) request.getAttribute("product_found");

    String name;
    int id_prod;
    String image;
    String price_prod;
    String description_prod;
    int quantity_prod;
    String category_name;

    if(prod!=null){
        name=prod.getName_prod();
        id_prod=prod.getId_prod();
        image=prod.getImageURI_prod();
        price_prod=(String)prod.getPrice_prod();
        description_prod=prod.getDescription_prod();
        quantity_prod=prod.getQuantity_prod();
        category_name=prod.cat.system_name();

    }else{
        name="Introuvable";
        id_prod=0;
        image="unknown";
        price_prod="XXXX";
        description_prod="Désolé, produit introuvable";
        quantity_prod=0;
        category_name=" ";
    }


%>
<!-- <main> -->
      <header class="section-product">
          <div class="page-padding">
            <div class="container-large">
              <div class="padding-top padding-xxlarge">
                <div class="padding-bottom padding-xxhuge">
                  <div class="product-header-component">
                    <div class="w-layout-grid product-header-layout">
                      <div class="product-header-gallery">
                        <a href="#" class="product-header-lightbox-link w-inline-block w-lightbox">
                          <div class="product-header-main-image-wrapper"><img src="/lalexandra/<%=image%>" loading="lazy" alt="" class="product-header-main-image"></div>
                        </a>
                      </div>
                      <div class="product-header-product-details">
                        <div class="breadcrumb">
                          <a href="/lalexandra/collection/all-products" class="breadcrumb-link product w-inline-block">
                            <div>Tout nos produit</div>
                          </a>
                          <div class="breadcrumb-divider w-embed"><svg width="16" height="16" viewbox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                              <path d="M6 3L11 8L6 13" stroke="CurrentColor" stroke-width="1.5"></path>
                            </svg></div>
                          <a href="/lalexandra/collection/<%=category_name%>" class="breadcrumb-link product w-inline-block">
                            <div><%=category_name%></div>
                          </a>
                        </div>
                        <div class="margin-bottom margin-small">
                          <div class="product-header-heading-wrapper">
                            <h1 class="heading-large"><%=name%></h1>
                            <div class="heading-xsmall"><%=price_prod%> FCFA</div>
                          </div>
                        </div>
                        <div class="margin-bottom margin-small">
                          <p><%=description_prod%></p>
                          <p>Quantité en stock : <%=quantity_prod%></p>
                        </div>
                        <div>
                          <form data-node-type="commerce-add-to-cart-form" method="post" action="/lalexandra/cart/addToCart" class="w-commerce-commerceaddtocartform" id="add_to_cart_form">
                            <div class="product-header-option-list">
                              <div class="product-header-option">
                              <label for="quantity-ac1706985f73fb30d0f0098a33f32f6a" class="form-label">Quantité</label><input type="number" pattern="^[0-9]+$" inputmode="numeric" id="quantity-ac1706985f73fb30d0f0098a33f32f6a" name="quantity_prod" min="1" max="<%=quantity_prod%>" class="w-commerce-commerceaddtocartquantityinput form-input is-quantity" value="1"></div>
                            </div>
                            
                            <% if(quantity_prod>0){%>
                              <input type="hidden" name="id_prod" value="<%=id_prod%>">
                              <input type="submit" data-node-type="commerce-add-to-cart-button" data-loading-text="Adding to cart..." value="ajouter au panier" aria-busy="false" aria-haspopup="dialog" class="w-commerce-commerceaddtocartbutton button max-width-full" id="addToCart_btn">
                            <%}else{%>
                              <div tabindex="0" style="display:none" class="w-commerce-commerceaddtocartoutofstock out-of-stock-state">
                                <div>Stock vide</div>
                              </div>
                            <%}%>
                            <a data-node-type="commerce-buy-now-button" data-default-text="Buy now" data-subscription-text="Subscribe now" aria-busy="false" aria-haspopup="false" style="display:none" class="w-commerce-commercebuynowbutton" href="checkout.html">Buy now</a>
                          </form>
                         
                          <div aria-live="assertive" data-node-type="commerce-add-to-cart-error" style="display:none" class="w-commerce-commerceaddtocarterror error-state">
                            <div data-node-type="commerce-add-to-cart-error" data-w-add-to-cart-quantity-error="Product is not available in this quantity." data-w-add-to-cart-general-error="Something went wrong when adding this item to the cart." data-w-add-to-cart-mixed-cart-error="You can’t purchase another product with a subscription." data-w-add-to-cart-buy-now-error="Something went wrong when trying to purchase this item." data-w-add-to-cart-checkout-disabled-error="Checkout is disabled on this site." data-w-add-to-cart-select-all-options-error="Please select an option in each set.">Product is not available in this quantity.</div>
                          </div>
                        </div>
                        <div class="margin-top margin-xlarge">
                          <div class="w-layout-grid faq-list-grid">
                            <div class="faq-accordion shadow-xsmall">
                              <div data-w-id="0b92d1ba-c32c-34f4-1c05-3eea71cf523a" class="faq-question">
                                <div class="text-size-medium text-weight-semibold">Details</div>
                                <div class="faq-icon">add</div>
                              </div>
                              <div style="width:100%;height:0px" class="faq-answer">
                                <div class="margin-bottom margin-small">
                                  <div class="max-width-xlarge">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique. Duis cursus, mi quis viverra ornare, eros dolor interdum nulla, ut commodo diam libero vitae erat. Aenean faucibus nibh et justo cursus id rutrum lorem imperdiet. Nunc ut sem vitae risus tristique posuere.</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="faq-accordion shadow-xsmall">
                              <div data-w-id="0b92d1ba-c32c-34f4-1c05-3eea71cf5245" class="faq-question">
                                <div class="text-size-medium text-weight-semibold">Shipping</div>
                                <div class="faq-icon">add</div>
                              </div>
                              <div style="width:100%;height:0px" class="faq-answer">
                                <div class="margin-bottom margin-small">
                                  <div class="max-width-xlarge">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique. Duis cursus, mi quis viverra ornare, eros dolor interdum nulla, ut commodo diam libero vitae erat. Aenean faucibus nibh et justo cursus id rutrum lorem imperdiet. Nunc ut sem vitae risus tristique posuere.</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="faq-accordion shadow-xsmall">
                              <div data-w-id="0b92d1ba-c32c-34f4-1c05-3eea71cf5250" class="faq-question">
                                <div class="text-size-medium text-weight-semibold">Returns</div>
                                <div class="faq-icon">add</div>
                              </div>
                              <div style="width:100%;height:0px" class="faq-answer">
                                <div class="margin-bottom margin-small">
                                  <div class="max-width-xlarge">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique. Duis cursus, mi quis viverra ornare, eros dolor interdum nulla, ut commodo diam libero vitae erat. Aenean faucibus nibh et justo cursus id rutrum lorem imperdiet. Nunc ut sem vitae risus tristique posuere.</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </header>
        <section class="section-steps">
          <div class="page-padding">
            <div class="container-large">
              <div class="padding-bottom padding-xxhuge">
                <div class="margin-bottom margin-xxlarge">
                  <div class="container-small">
                    <div class="text-align-center z-index-1">
                      <div class="margin-bottom margin-small">
                        <div class="tagline">Comment cela fonctionne-t-il ?</div>
                      </div>
                      <div class="margin-bottom margin-small">
                        <h2>C'est aussi simple que  1, 2, 3</h2>
                      </div>
                      <p class="text-size-medium">Utiliser notre produit n’a jamais été aussi simple ! 🌟
  
                        Suivez ces trois étapes faciles pour une peau radieuse :.</p>
                    </div>
                  </div>
                </div>
                <div class="home-steps-row z-index-1">
                  <div class="home-steps-item">
                    <div class="margin-bottom margin-medium">
                      <div class="icon-wrapper">
                        <div class="icon-medium">clean_hands</div>
                      </div>
                    </div>
                    <div class="margin-bottom margin-small">
                      <h3 class="heading-small">Appliquer le produit</h3>
                    </div>
                    <p>Commencez par prendre une petite quantité de notre produit et appliquez-la uniformément sur votre visage et votre cou...</p>
                  </div>
                  <div class="home-steps-item">
                    <div class="margin-bottom margin-medium">
                      <div class="icon-wrapper">
                        <div class="icon-medium">timelapse</div>
                      </div>
                    </div>
                    <div class="margin-bottom margin-small">
                      <h3 class="heading-small">Attendez que la peau absorbe</h3>
                    </div>
                    <p>Laissez votre peau absorber le produit. Cela ne prendra que quelques instants...</p>
                  </div>
                  <div class="home-steps-item">
                    <div class="margin-bottom margin-medium">
                      <div class="icon-wrapper">
                        <div class="icon-medium">check_circle</div>
                      </div>
                    </div>
                    <div class="margin-bottom margin-small">
                      <h3 class="heading-small">Et c’est tout</h3>
                    </div>
                    <p>Voilà, c’est aussi simple que ça ! Pas besoin de rincer ou de faire quoi que ce soit d’autre.</p>
                  </div>
                </div>
              </div>
            </div>
          </div><img src="images/Pink-Graphic.svg" loading="lazy" alt="" class="steps-graphic"><img src="images/Pink-Graphic.svg" loading="lazy" alt="" class="steps-graphic-2">
        </section>
        <!-- SECTION HOME PRODUIT -->
        <!-- FIN -->
