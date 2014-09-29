/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function selectCountry(n){
    var form = document.getElementById("updCity");
    var id = form.id_country;
    id.setAttribute("value", n);
  
}

function selectCity(n){
    var form = document.getElementById("updHotel");
    var id = form.id_city;
    id.setAttribute("value", n);
    
}

function selectStars(n){
    var form = document.getElementById("updHotel");
    var id = form.stars_hotel;
    id.setAttribute("value", n);
}


function post(path, params, method) {
    method = method || "post";
    
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    for (var key in params) {
        if (params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);
            
            form.appendChild(hiddenField);
        }
    }
    
    document.body.appendChild(form);
    form.submit();
    
}