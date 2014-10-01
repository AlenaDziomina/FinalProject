/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function check(atrName, atr){
    var str = String(atr);
    var tags = document.getElementsByTagName("input");
    for (var i=0; i<tags.length; i++) 
    {
        if(tags[i].type==="checkbox" && tags[i].name===atrName) 
        {
            var tag = tags[i];
            var val = tag.value;
                if (val === str){
                    tag.checked = true;
                } 
        }
    }
}

function select(selName, atr){
    var select = document.getElementById(selName);
    select.value=atr;
}

function postDir(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveCountryTags(form);
    saveCityTags(form);
    saveMode(form);
    saveTourType(form);
    saveCurrIdCountry(form);
    saveCurrIdCity(form);

    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "command";
    elem.value = comnd;
    form.appendChild(elem);
        
    document.body.appendChild(form);
    form.submit();
    
}

function saveCurrIdCity(form){
    var select = document.getElementById("currCity").value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "currIdCity";
    elem.value=select;
    form.appendChild(elem);
}

function saveCurrIdCountry(form){
    var select = document.getElementById("currCountry").value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "currIdCountry";
    elem.value=select;
    form.appendChild(elem);
}

function saveMode(form){
    var select = document.getElementById("transMode").value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "currTransMode";
    elem.value=select;
    form.appendChild(elem);
}
function saveTourType(form){
    var select = document.getElementById("tourType").value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "currTourType";
    elem.value=select;
    form.appendChild(elem);
    
}


function saveCountryTags(form) {
    var tableInputTags = document.getElementsByTagName("input");
    for (var i=0; i<tableInputTags.length; i++) 
    {
        if(tableInputTags[i].type==="checkbox" && tableInputTags[i].name==="countryTag") 
        {
            var tag = tableInputTags[i];
            var val = tag.value;
            if (tag.checked) {
                var elem = document.createElement("input");
                elem.type = "hidden";
                elem.name = "currCountryTag";
                elem.value=val;
                form.appendChild(elem);
            }
        }
    }
}

function saveCityTags(form) {
    
    var tableInputTags = document.getElementsByTagName("input");
    for (var i=0; i<tableInputTags.length; i++) 
    {
        if(tableInputTags[i].type==="checkbox" && tableInputTags[i].name==="cityTag") 
        {
            var tag = tableInputTags[i];
            var val = tag.value;
            if (tag.checked) {
                var elem = document.createElement("input");
                elem.type = "hidden";
                elem.name = "currCityTag";
                elem.value=val;
                form.appendChild(elem);
            }
        }
    }
}

function funcAdd(){
    var form = document.getElementById("updDirection");
    var elem = document.getElementById("currHotelTag");
    
    var select = document.getElementById("currHotel");
    var i = select.value;
    var txt = select.options[i].text;
    
    var checkbox = document.createElement('input');
    checkbox.type = "checkbox";
    checkbox.name = "hotelTag";
    checkbox.value = i;
    checkbox.id = "id"+i;
    

    var label = document.createElement('label');
    label.htmlFor = "id"+i;
    
    label.appendChild(document.createTextNode(txt));      
    elem.appendChild(checkbox);
    elem.appendChild(label);
    elem.appendChild(document.createElement("br"));
}



function selectTourType(n){
    var form = document.getElementById("updDirection");
    var id = form.idTourType;
    id.setAttribute("value", n);
}

function selectTourType(n){
    var form = document.getElementById("updDirection");
    var id = form.idTransMode;
    id.setAttribute("value", n);
}


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





