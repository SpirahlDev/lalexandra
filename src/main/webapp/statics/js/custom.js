let cart;
let cart_btn;
let close_cart;
let addToCart_btn=document.getElementById("addToCart_btn");

document.addEventListener("DOMContentLoaded",()=>{
    cart=document.getElementById("cart");
    cart_btn=document.getElementById("cart_btn");
    close_cart=document.getElementById("close_cart");
    
    cart_btn.addEventListener("click",toggleCart);
    close_cart.addEventListener("click",toggleCart);

    window.addEventListener("click",(event)=>{
      const element=event.target;
      if (!cart.contains(element)) {
          cart.style.display=="none";
      }
      
      handleCartUpdate(element);
        
    });

    // addToCart_btn.addEventListener("click",(event)=>{
    //   event.preventDefault();
    //   addToCart();
    // });
});

function handleCartUpdate(element){
  let parent=element.parentElement;
  const classlist=parent.classList;

  if(classlist.contains("async-cart-action")){
    const id_prod=parseInt(parent.querySelector(`input[type=hidden]`).value);
    cartItemLoading(parent);

    let formData=new FormData();
    let link="";

    if(element.tagName=="INPUT"){
      link="/lalexandra/cart/changeQuantity";
      const quantity=parseInt(parent.querySelector(`input[type=number]`).value);
      formData.append("id_prod",id_prod);
      formData.append("cart_prod_quantity",quantity);
    }

    if(element.tagName="BUTTON"){

    }

    callServer(link,formData)
    .then((response)=>{
      console.log(response);
      return response.json();
    })
    .then((response)=>{
      console.log("json :"+response);
    })
    .catch((error)=>{
      console.log(error);
    });

  }
}

function cartItemLoading(element){
  element.style.opacity="0.4";
  element.style.scale="0.95";
  
  setTimeout(()=>{
    cartItemFinished(element);
  }, 1000);
}

function cartItemFinished(element){
  element.style.opacity="1";
  element.style.scale="1";
}

function sendUpdate(element){

}

function toggleCart(){
    if(cart.style.display=="none"){
        cart.style.display="flex";
        document.getElementsByTagName("body")[0].style.overflowY="hidden";
        //fetchage des elements du panier
        getCartProducts();
    }else{
        cart.style.display="none";
        document.getElementsByTagName("body")[0].style.overflowY="unset";
    }
}

function send(link,data) {
    
  const options = {
    method: 'POST',
    body: data,
    headers:{
      'Content-Type': 'application/json', 
    }
  };

  fetch(link, options)
    .then(response => {
      if (!response.ok) {
        throw new Error(`La requête a échoué avec le statut : ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      console.log('Réponse du serveur :', data);
    })
    .catch(error => {
      console.error('Erreur lors de la requête Fetch :', error);
    });
}



async function getCartProducts(){
  let cart_screen=document.getElementById("cart-list-screen");

  let cartProducts=null;
  const action_link="/lalexandra/cart/getCartProducts";

    try {
      const serverResponse = await callServer(action_link);

      if (serverResponse) {
        cartProducts = await serverResponse.text();
        // AjaxDebug(cartProducts);
        cart_screen.innerHTML=cartProducts;
      } else {
        console.log("La réponse du serveur est invalide");
      }
    } catch (error) {
      console.error("Une erreur est survenue : ", error);
    }

    return cartProducts;
}

function callServer(link,body=null,gotHeaders=null){
  if (link===null) {
      throw new Error('Le lien de la requête est indéfini.');
  }
  
  if (typeof gotHeaders==="undefined" || gotHeaders===null || !gotHeaders.has('Content-Type')) {
    gotHeaders=new Headers();
    gotHeaders.append('Content-Type', 'application/x-www-form-urlencoded');
  }


  
  return new Promise((resolve,reject)=>{
      fetch(link,{
          method: "POST",
          headers:gotHeaders,
          body: body
      })
      .then(response=>{
          if(response.ok){
              resolve(response);
          }else{
            reject(response);
          }
          // console.log("Reponse depuis callServer :"+response);
      })
      .catch(error=>{
          console.error("erreur depuis callServer :"+response);
          reject(error);
      })
  });
}

function addToCart() {
  const url = "/lalexandra/cart/addToCart";

  let form = document.getElementById("add_to_cart_form");
  let formData = new FormData(form);

  callServer(url, formData)
    .then(response => {
      // Décodage du JSON et traitement de la réponse
      return response.json();
    })
    .then(data => {
      console.log(data);
    })
    .catch(error => {
      // Gérer les erreurs de la requête
      console.error(error);
    });
}