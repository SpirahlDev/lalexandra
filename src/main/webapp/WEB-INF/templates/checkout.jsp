
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String product_price=String.valueOf(request.getAttribute("PRODUCTS_PRICE"));
    String total=String.valueOf(request.getAttribute("TOTAL"));
%>
 <section class="section-utility-header">
        <div class="page-padding">
          <div class="padding-vertical padding-huge">
            <div class="utility-header-content">
              <div class="container-large">
                <div class="max-width-large">
                  <div class="margin-bottom margin-xxsmall">
                    <h1>Caisse</h1>
                  </div>
                  <p class="text-size-medium">Veuillez consulter vos détails de paiement ci-dessous. Si tout est correct, passez votre commande et vous recevrez plus d'informations par e-mail.</p>
                </div>
              </div>
            </div>
          </div>
        </div><img src="statics/images/Graphic.svg" loading="lazy" alt="" class="utility-graphic">
      </section>
      <div data-node-type="commerce-checkout-form-container" data-wf-checkout-query="" data-wf-page-link-href-prefix=""
        class="w-commerce-commercecheckoutformcontainer section-checkout-form">
        <div class="w-commerce-commercelayoutcontainer container-large w-container">
          <div class="w-commerce-commercelayoutmain customer-info">

            <form data-node-type="commerce-checkout-shipping-address-wrapper"
              class="w-commerce-commercecheckoutshippingaddresswrapper checkout-card shadow-small">
              <div class="w-commerce-commercecheckoutblockheader checkout-header">
                <h3 class="checkout-card-heading">Addresse de Livraison</h3>
                <div class="checkout-required">
                  <div class="text-size-regular">* Requis</div>
                </div>
              </div>
              <fieldset class="w-commerce-commercecheckoutblockcontent checkout-content">
                <div class="margin-bottom margin-medium"><label
                    class="w-commerce-commercecheckoutlabel form-label"> Addresse *</label>
                  <div class="margin-bottom margin-small"><input type="text"
                      class="w-commerce-commercecheckoutshippingstreetaddress form-input" name="address_line1"
                      required="Information réquise"></div>
                </div>
                <div class="w-commerce-commercecheckoutrow margin-bottom margin-medium">
                  <div class="w-commerce-commercecheckoutcolumn column is-left"><label
                      class="w-commerce-commercecheckoutlabel form-label">Ville *</label><input type="text"
                      class="w-commerce-commercecheckoutshippingcity form-input" name="address_city" required=""></div>
                 
                </div>
              </fieldset>
            </form>
            <form data-node-type="commerce-checkout-shipping-methods-wrapper"
              class="w-commerce-commercecheckoutshippingmethodswrapper checkout-card shadow-small">
              <div class="w-commerce-commercecheckoutblockheader checkout-header">
                <h3 class="checkout-card-heading"> Methode de Livraison</h3>
              </div>
              <fieldset class="checkout-content">
                <script type="text/x-wf-template"
                  id="wf-template-c8aabfa7-3cd0-ad3b-fa40-af618b382ef9">%3Clabel%20class%3D%22w-commerce-commercecheckoutshippingmethoditem%22%3E%3Cinput%20type%3D%22radio%22%20required%3D%22%22%20name%3D%22shipping-method-choice%22%2F%3E%3Cdiv%20class%3D%22w-commerce-commercecheckoutshippingmethoddescriptionblock%22%3E%3Cdiv%20class%3D%22w-commerce-commerceboldtextblock%20field-label%22%3E%3C%2Fdiv%3E%3Cdiv%20class%3D%22text-size-regular%22%3E%3C%2Fdiv%3E%3C%2Fdiv%3E%3Cdiv%20class%3D%22checkout-price%22%3E%3C%2Fdiv%3E%3C%2Flabel%3E</script>
                <div data-node-type="commerce-checkout-shipping-methods-list"
                  class="w-commerce-commercecheckoutshippingmethodslist"
                  data-wf-collection="database.commerceOrder.availableShippingMethods"
                  data-wf-template-id="wf-template-c8aabfa7-3cd0-ad3b-fa40-af618b382ef9"><label
                    class="w-commerce-commercecheckoutshippingmethoditem">

                    <input type="radio" required="" id="shipping" name="shipping-method-choice" value="A domicile">
                    <label for="shipping">A domicile</label>
                    <!-- <input type="radio" required="" name="shipping-method-choice" value=""> -->

                    <div class="w-commerce-commercecheckoutshippingmethoddescriptionblock">
                      <div class="w-commerce-commerceboldtextblock field-label"></div>
                      <div class="text-size-regular"></div>
                    </div>
                    <div class="checkout-price"></div>
                  </label></div>
                <div data-node-type="commerce-checkout-shipping-methods-empty-state" style="display:none"
                  class="w-commerce-commercecheckoutshippingmethodsemptystate">
                  <div>Aucun mode d’expédition n’est disponible pour l’adresse indiquée.</div>
                </div>
              </fieldset>
            </form>
            <!-- PAYMENT INFO PART -->
            <div class="w-commerce-commercecheckoutpaymentinfowrapper checkout-card shadow-small">
              <div class="w-commerce-commercecheckoutblockheader checkout-header">
                <h3 class="checkout-card-heading">Info paiement</h3>
                <div class="checkout-required">
                  <div class="text-size-regular">* Requis</div>
                </div>
              </div>
              <div class="paiement">
                <button type="button" class="btn btn-primary btn-lg" id="btn">Paiement en ligne</button>
                <button type="button" class="btn btn-secondary btn-lg" id="btn1">Paiement en espèce</button>
              </div>

              <fieldset class="w-commerce-commercecheckoutblockcontent checkout-content">
                <div class="info" style="display: none" ;>

                  <div class="margin-bottom margin-medium"><label
                      class="w-commerce-commercecheckoutlabel form-label">Nom *</label>
                    <!-- <div data-wf-stripe-element-type="cardNumber" data-wf-style-map="{}" style="position:relative"
                        class="w-commerce-commercecheckoutcardnumber form-input">
                      <div></div>
                      <div style="position:absolute;top:0;left:0;width:100%;height:100%;opacity:0"></div>
                    </div> -->
                    <input type="text">
                  </div>
                  <div class="w-commerce-commercecheckoutrow margin-bottom margin-medium">
                    <div class="w-commerce-commercecheckoutcolumn column is-left"><label
                        class="w-commerce-commercecheckoutlabel form-label">Numéro*</label>
                      <!-- <div data-wf-stripe-element-type="cardExpiry" data-wf-style-map="{}" style="position:relative"
                        class="w-commerce-commercecheckoutcardexpirationdate form-input">
                        <div></div>
                        <div style="position:absolute;top:0;left:0;width:100%;height:100%;opacity:0"></div>
                      </div> -->
                    <input type="text">

                    </div>
                      <!-- <div class="w-commerce-commercecheckoutcolumn column is-right"><label
                        class="w-commerce-commercecheckoutlabel form-label">Security Code *</label>
                        <div data-wf-stripe-element-type="cardCvc" data-wf-style-map="{}" style="position:relative"
                        class="w-commerce-commercecheckoutcardsecuritycode form-input">
                        <div></div>
                        <div style="position:absolute;top:0;left:0;width:100%;height:100%;opacity:0"></div>
                      </div>
                      <input type="text" >
                    </div> -->
                  </div>

                  <button type="button" class="btn btn-success">CONFIRMER</button>


                  <div class="w-commerce-commercecheckoutbillingaddresstogglewrapper form-checkbox"><input
                      type="checkbox" id="billing-address-toggle"
                      data-node-type="commerce-checkout-billing-address-toggle-checkbox"
                      class="w-commerce-commercecheckoutbillingaddresstogglecheckbox form-checkbox-icon margin-0"
                      checked=""><label for="billing-address-toggle"
                      class="w-commerce-commercecheckoutbillingaddresstogglelabel form-checkbox-label no-margin-bottom">Facturation
                      Adresse identique à celle de l’expédition</label></div>
                  <button type="button" class="btn btn-secondary btn-sm" id="revenir">Retour</button>
                </div>
              </fieldset>
            </div>
            <!-- FIN -->



          <script>
            // Sélectionnez les éléments nécessaires
    let boutonPaiementEnLigne = document.querySelector(".btn-primary.btn-lg");
    let blocPaiement = document.querySelector(".paiement");
    let champPaiementEnLigne = document.querySelector(".info");
    let boutonsPaiement = document.getElementById("btn");
    let boutonsPaiement1 = document.getElementById("btn1");
    let boutonsRevenir = document.getElementById("revenir");


    // Bouton revenir 
    boutonsRevenir.addEventListener("click", () => {
      champPaiementEnLigne.style.display = "none";
      blocPaiement.style.display = "block";

    })

    // Ajoutez un gestionnaire d'événement au bouton "Paiement en ligne"
    boutonPaiementEnLigne.addEventListener("click", () => {
      // Inversez la valeur de l'attribut "display" du champ de paiement

      champPaiementEnLigne.style.display = "block";


      // Masquez les boutons de paiement
      /*boutonsPaiement.style.display = "none";
      boutonsPaiement1.style.display = "none";*/
      blocPaiement.style.display = "none";

    });
          </script>

          </div>
          <!-- MENU FIXE DE DROITE -->
          <div class="w-commerce-commercelayoutsidebar order-summary-wrapper">
            <div class="w-commerce-commercecheckoutordersummarywrapper order-summary">
              <div class="checkout-card shadow-small">
                <div class="w-commerce-commercecheckoutsummaryblockheader checkout-header">
                  <h3 class="checkout-card-heading">Commandes</h3>
                </div>
                <fieldset class="w-commerce-commercecheckoutblockcontent checkout-content">
                  <div class="w-commerce-commercecheckoutsummarylineitem line-item">
                    <div class="form-label">Total produit</div>
                    <div class="checkout-price"><%=product_price%> FCFA</div>
                  </div>
                  <script type="text/x-wf-template"
                    id="wf-template-c8aabfa7-3cd0-ad3b-fa40-af618b382f5f">%3Cdiv%20class%3D%22w-commerce-commercecheckoutordersummaryextraitemslistitem%20line-item%22%3E%3Cdiv%20class%3D%22form-label%22%3E%3C%2Fdiv%3E%3Cdiv%20class%3D%22checkout-price%22%3E%3C%2Fdiv%3E%3C%2Fdiv%3E</script>
                  <div class="w-commerce-commercecheckoutordersummaryextraitemslist"
                    data-wf-collection="database.commerceOrder.extraItems"
                    data-wf-template-id="wf-template-c8aabfa7-3cd0-ad3b-fa40-af618b382f5f">
                    <div class="w-commerce-commercecheckoutordersummaryextraitemslistitem line-item">
                      <div class="form-label"></div>
                      <div class="checkout-price"></div>
                    </div>
                  </div>
                  <div class="w-commerce-commercecheckoutsummarylineitem line-item is-last">
                    <div class="form-label">Prix total</div>
                    <div class="w-commerce-commercecheckoutsummarytotal checkout-price"><%=total%> FCFA (frais inclus)</div>
                  </div>
                </fieldset>
              </div>
            </div>
            <a href="#" value="Place Order" data-node-type="commerce-checkout-place-order-button"
              class="w-commerce-commercecheckoutplaceorderbutton button" data-loading-text="Placing Order...">Commander</a>
            <div data-node-type="commerce-checkout-error-state" style="display:none"
              class="w-commerce-commercecheckouterrorstate error-message">
              <div aria-live="polite" class="w-checkout-error-msg"
                data-w-info-error="There was an error processing your customer info. Please try again, or contact us if you continue to have problems."
                data-w-shipping-error="Sorry. We can’t ship your order to the address provided."
                data-w-billing-error="Your payment could not be completed with the payment information provided. Please make sure that your card and billing address information is correct, or try a different payment card, to complete this order. Contact us if you continue to have problems."
                data-w-payment-error="There was an error processing your payment. Please try again, or contact us if you continue to have problems."
                data-w-pricing-error="The prices of one or more items in your cart have changed. Please refresh this page and try again."
                data-w-minimum-error="The order minimum was not met. Add more items to your cart to continue."
                data-w-extras-error="A merchant setting has changed that impacts your cart. Please refresh and try again."
                data-w-product-error="One or more of the products in your cart have been removed. Please refresh the page and try again."
                data-w-invalid-discount-error="This discount is invalid."
                data-w-expired-discount-error="This discount is no longer available."
                data-w-usage-reached-discount-error="This discount is no longer available."
                data-w-requirements-not-met-error="Your order does not meet the requirements for this discount.">Là
                c'était une erreur lors du traitement de vos informations client. Veuillez réessayer ou contactez-nous si vous continuez à avoir des
                Problèmes.</div>
            </div>
          </div>
          <!-- FIN MENU-->
        </div>
      </div>