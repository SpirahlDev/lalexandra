function displayNotif(msg,statusCode,time=4000){
    let notifBox=document.getElementById("shape-notify");

    if(notifBox){
        let close_btn=notifBox.querySelector("#close-btn-notify");
        function handleClose() {
            clearTimeout(timeOut);
            notifBox.style.display="none";
            close_btn.removeEventListener("click", handleClose);
        }
        
        
        close_btn.addEventListener("click", handleClose);
        
        notifBox.style.display="block";
        var timeOut=setTimeout(function(){
            notifBox.style.display="none"
            close_btn.removeEventListener("click", handleClose);
        },time);
        let p=notifBox.querySelector('p');
        
        p.innerHTML=msg;

        if(statusCode==="SUC"){
            notifBox.style.backgroundColor="#599c60";
        }
        else if(statusCode==="ERR"){
            notifBox.style.backgroundColor="#CC1616"; 
        }
        
    }
}
