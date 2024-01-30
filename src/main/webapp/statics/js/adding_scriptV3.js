const controller_uri = '/lalexandra/dashboard/new-product';

var lastNombre=0;
$(document).ready(function(){
    document.getElementById("save").addEventListener("click",empackage);
    var plus_btn=document.getElementById("plus");
    lastNombre=parseInt(1);
    $(".img-input").on("change", eventPreview); 
    $(".supr-btn").click(function(){
        let parent=$(this).parentNode.parentNode.parentNode;
        parent.reset();
    });
    plus_btn.addEventListener("click",function(){
        var objetNombre=document.getElementById("compteur").value;
        var lotPart=document.getElementById("lotPart");
        for(let i=1;i<=objetNombre;i++){
            var box_lot=document.createElement("form"); 
            box_lot.innerHTML=box; 
            let del=box_lot.getElementsByClassName("supr-btn")[0];
            let img_input=$(box_lot).find(".img-input");
            img_input.on("change", eventPreview);
            del.addEventListener("click",orderReview);

            lotPart.appendChild(box_lot);
            box_lot.getElementsByClassName("numero-lot")[0].innerHTML=parseInt(lastNombre+i);
        } 
        lastNombre+=parseInt(objetNombre);
        let msg={mood:"OK",value:objetNombre+" lot ajouté"}
        inform(msg);
        
    });
});


var json_data;


function empackage() {
  var forms = document.getElementsByTagName("form");

  for (let i = 0; i < forms.length; i++) {
    let formData = new FormData(forms[i]);
    var data = {};

    formData.forEach((value, name) => {
      data[name] = value;
    });

    console.log(data,formData);

    send(formData);
  }
}

function send(formData) {
    
  const options = {
  
    method: 'POST',
    body: formData,
  };

  fetch(controller_uri, options)
    .then(response => {
      if (!response.ok) {
        throw new Error(`La requête a échoué avec le statut : ${response.status}`);
      }
      return response.text(); // Ajout de la lecture de la réponse JSON ici
    })
    .then(data => {
      console.log('Réponse du serveur :', data);
    })
    .catch(error => {
      console.error('Erreur lors de la requête Fetch :', error);
    });
}


function serverResponse(resp){
    resp=JSON.parse(resp);
    if(resp.status=="OK"){
        resp["mood"]="OK";
        inform(resp);
    }
    else if(resp.status=="ERROR"){
        resp["mood"]="ER";
        inform(resp);
    }
    if(resp.conf=="OK"){
        resp["mood"]="OK";
        displayModal(resp);
    }
    else if(resp.conf=="ERROR"){
        resp["mood"]="ER";
        inform(resp);
    }
}

 
function orderReview(){
    let parent=this.parentNode.parentNode.parentNode;
    let lotNumber=parseInt(parent.getElementsByClassName("numero-lot")[0].innerHTML)-1;
    console.log(lotNumber);
    parent.remove();
    let Allnumber=document.getElementsByClassName("numero-lot");
    for(let j=lotNumber;j<Allnumber.length;j++){
        let nb=Allnumber[j].innerHTML;
        Allnumber[j].innerHTML=parseInt(nb)-1;

    }
    lastNombre=lastNombre-1;
    let msg={mood:"ER",value:"Supprimé"}
        inform(msg);
}
function eventPreview(){
    // Récupérer les informations sur le fichier sélectionné
       const fichier = $(this)[0].files[0];
       console.log(fichier);
       if(fichier) {
           // Créer un objet URL pour afficher l'aperçu de l'image
           const url = URL.createObjectURL(fichier);
           let bloc_img=$(this)[0].parentNode;
           let preview=bloc_img.getElementsByClassName("preview")[0];
           preview.setAttribute("src",url);
           preview.setAttribute("style","width:100%; height:100%")
       }
       else{
           let bloc_img=$(this)[0].parentNode;
           let preview=bloc_img.getElementsByClassName("preview")[0];
           preview.setAttribute("src","/lalexandra/icon/not-set.svg");
           preview.setAttribute("style","width:30px; height:30px");
       }
}

function displayModal(reponse){
    const modal = document.getElementById("modal");
    let p=modal.getElementsByClassName("modal-screen")[0];
    let ctn=modal.getElementsByClassName("modal-content")[0];
    p.innerHTML=reponse.value;
    modal.style.display = "block";
    if(reponse.mood==="OK"){
        ctn.style.backgroundColor="#599c60";
    }
    else{
        ctn.style.backgroundColor="#CC1616";
    }

}

var box=` 
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
                            <label for="mot-etat" id="quantity_path">Quantitï¿½ du produit</label>
                            <input type="text" name="quantity_prod" id="mot-etat">
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
                                <label for="image1">Image 1 (principal)</label>
                            </div>
                        </div>
                    </li>
                </ul>
            </article>
`;