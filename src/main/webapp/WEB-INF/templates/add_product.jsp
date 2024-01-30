<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="separator">

    <section id="lotPart">
        <div id="titre-lotPart"><span>Ajout de produit</span></div>

        <!-- PARTIE CONTROLLE DE NOMBRE DE BOITE ET ENREGISTREMENT-->
        <div id="control-box">
            <div>
                <label for="compteur">Ajouter des lots</label for="compteur">
                <div class="screen-ctrl">
                    <input type="number" value="1" min="1" id="compteur" class="num">
                    <button id="plus">+</button>
                </div>
                <button class="modifier" id="save">Enregistrer</button>
            </div>
        </div>
        <!-- FIN -->

        <!-- SECTION DES BOITES PRODUITS -->
        <form action="/lalexandra/dashboard/new-product" method="post" class="box-lot">
            <div class="number">
                <span>Produit n°<span class="numero-lot">1</span>
                </span>
            </div>

            <!-- UNE BOITE PRODUIT -->
            <article class="principal-ctn">
                <div class="supr">
                    <div class="supr-btn">
                        <img src="/lalexandra/statics/icon/delete-icon.svg" width="30px" alt="">
                        <span>Supprimer</span>
                    </div>
                </div>
                <ul>
                    <li class="champs">
                        <div>
                            <label for="lot-name">Nom du produit</label>
                            <input type="text" name="name_prod" id="lot-name">
                        </div>
                        <div>
                            <label for="estimation">Prix</label>
                            <input type="text" name="price_prod" id="estimation">
                        </div>
                        <div id="category_path">
                            <label for="category">Categorie du produit</label>
                            <select name="id_category" id="">
                                <option value="3">Soins capillaire</option>
                                <option value="2">Soins du visage</option>
                                <option value="4">Maquillage</option>
                                <option value="5">Parfums</option>
                            </select>
                        </div>
                    </li>
                    <li class="champs" id="description_path">
                            <label for="descript">Description du produit</label>
                            <textarea name="description_prod" id="descript" cols="30" rows="10"></textarea>
                        
                    </li>

                    <li class="champs">
                        <div>
                            <label for="mot-etat" id="quantity_path">Quantité du produit</label>
                            <input type="number" name="quantity_prod" id="" min="1" id="mot-etat">
                        </div>
                    </li>

                    <li class="champs" id="image_path">
                        <span>Image produit</span>
                        <div class="img-list-part">
                            <div class="bloc-img">
                                <input type="file" class="img-input" name="image1" id="image1">
                                <div class="img-disp">
                                    <img src="" alt="" onerror="this.src='/lalexandra/statics/icon/not-set.svg'; this.setAttribute('style', 'width:30px;height:30px')" class="preview">
                                </div>
                            </div>
                        </div>
                        <input type="submit" value="Enregistrer" id="sub_btn">
                    </li>
                </ul>
            </article>
            <!-- UNE BOITE PRODUIT -->
        </form>
        <!-- FIN -->
    </section>
    <div id="shape-notify">
        <div id="content-notify">
            <p>Contenu de la forme ici</p>
            <div id="close-btn-notify">&times;</div>
        </div>
    </div>
</div>

<!-- MODELE DE BOITE DE DIALOGUE -->
    <div class="notify" id="notification-screen">
        <div id="notification">
            <span class="noti-text"></span>
            <img src="" alt="" class="close-btn">
        </div>
    </div>

    <div id="modal">
        <div class="modal-content">
          <p class="modal-screen"></p>
          <div class="modal-buttons">
            <a href="/lalexandra/dashboard">Retourner au tableaux de Bord Tableaux de bord</a>
          </div>
        </div>
    </div>
<!-- FIN -->
