textchange = false;
let url = "http://127.0.0.1:5500/html/notification.html"
$("#nav-notification").click(function() {
    if(textchange) {
        textchange = false;
        let container = document.getElementById("container-notification");
        container.innerHTML = "";
    } else {
        textchange = true;
        let iframe = document.createElement("iframe");
        iframe.src = url;
        iframe.style.width = "100%";
        iframe.style.height = "100%";
        iframe.frameBorder = "0";
    
        let container = document.getElementById("container-notification");
        // container.innerHTML = "";
        container.appendChild(iframe);
    }
})