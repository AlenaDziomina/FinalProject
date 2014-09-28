/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function popup() {
    alert("Hello World");
}

function fsrc(n){
    
    
    
    document.getElementById("images").src = n;
    
    
    
    var div = document.getElementById("textDiv");
    div.textContent = n;
    var text = div.textContent;
    
        
//    var StringArray = Java.type("java.lang.String[]");
//    var a = new StringArray(5);
//
//    // Set the value of the first element
//    a[0] = "Scripting is great!";
//    
//    var div2 = document.getElementById("textDiv2");
//    div2.textContent = a[0];
//    var text2 = div2.textContent;
    
}

function selectCountry(n){
    var form = document.getElementById("updCity");
    var id = form.id_country;
    id.setAttribute("value", n);
    
    
    
}

function selectCity(n){
    var form = document.getElementByName("updateHotel");
    var id = form.elements("id_city");
    id.setAttribute("value", n);
    
}

function selectStars(n){
    var form = document.getElementByName("updateHotel");
    var id = form.elements("stars_hotel");
    id.setAttribute("value", n);
}

function selectCountryShowCities(list){
    
    var form = document.getElementByName("updateHotel");
    var objSel = form.elements("citySelection");
    objSel.options.length=1;
    
    for (var city in list) {
        var id = city.getAttribute("idCity");
        var name = city.getAttribute("name");
        addOption(objSel, name, id, false);
    }
    
    
    
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