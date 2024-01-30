 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
  <!-- panneau de gestion -->

<div class="row-container_cus">
    <h5 style="margin-top: 2rem;margin-left:2rem;">Tableau de bord <i class="bi bi-window-split" style="cursor: pointer;"></i></h5>
    <div id="date">
      <div class="date-ctn">
          <div class="day">Jours</div>
          <div id="calendar">
              <img src="statics/icon/date.svg" alt="" height="20px" style="margin-right: 5px;">
              <span>12/12/2022</span>
          </div>
    </div>
  </div>
  </div>

  <div style="display: flex;justify-content:space-around;margin-top:2rem">

    <div class="panels">
      <div class="panel_info">
        <img src="statics/icon/commande_icon.svg" height="50px" width="50px" >
        <h6>Commandes</h6>
        <a href="#">Voir la liste complète</a>
      </div>
      <div class="action_btn" >
        <p>17 Commandes reçus</p>
      </div>
    </div>

    <div class="panels">
      <div class="panel_info">
        <img src="statics/icon/stock_icon.svg" height="50px" width="50px" >
        <h6>Stock</h6>
        <a href="#">Voir la liste complète</a>
      </div>
      <div class="action_btn">
        <img src="statics/icon/arrow.svg" >
      </div>
    </div>
    <div class="panels">
      <div class="panel_info">
        <h6>Ajouter produit</h6>
      </div>
      <a href="dashboard/add-product">
          <div class="action_btn" id="adding_panel">
          <img src="statics/icon/plus_dash.svg">
        </div>
        </a>
    </div>
  </div>
  
  <div class="row-container_cus" id="product_nb_panel">
    <div class="panels">
      <div class="panel_info">
        <img src="statics/icon/pourcentage.svg" height="50px" width="50px" >
        <h6>Nombre de produits en vente</h6>
      </div>
      <div class="action_btn" >
        <p>17</p>
      </div>
    </div>
</div>
<!-- fin -->