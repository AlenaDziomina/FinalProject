/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function saveAllSearch() {
    var form = document.getElementById("srcTour");
    saveCountryTags(form);
    saveCityTags(form);
    saveHotelTags(form);
    saveCurrHotelStars(form);
    saveMode(form, "transMode", "currTransMode");
    saveTourType(form, "tourType", "currTourType");
    saveCurrElem(form, "currCountry", "currIdCountry");
    saveCurrElem(form, "currCity", "currIdCity");
    saveCurrElem(form, "dateFrom", "currDepartDateFrom");
    saveCurrElem(form, "dateTo", "currDepartDateTo");
    saveCurrElem(form, "currDaysCountFrom", "currDaysCountFrom");
    saveCurrElem(form, "currDaysCountTo", "currDaysCountTo");
    saveCurrElem(form, "currPriceFrom", "currPriceFrom");
    saveCurrElem(form, "currPriceTo", "currPriceTo");
    saveCurrElem(form, "currDiscountFrom", "currDiscountFrom");
    saveBox(form, "allDepartDate");
    saveBox(form, "allDaysCount");
    saveBox(form, "allPrice");
    saveBox(form, "allCountries");
    saveBox(form, "allCities");
    saveBox(form, "allHotels");
    saveIsHidden(form, "searching");
}

function postTour(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveCountryTags(form);
    saveCityTags(form);
    saveHotelTags(form);
    saveCurrHotelStars(form);
    saveMode(form, "transMode", "currTransMode");
    saveTourType(form, "tourType", "currTourType");
    saveCurrElem(form, "currCountry", "currIdCountry");
    saveCurrElem(form, "currCity", "currIdCity");
    saveCurrElem(form, "dateFrom", "currDepartDateFrom");
    saveCurrElem(form, "dateTo", "currDepartDateTo");
    saveCurrElem(form, "currDaysCountFrom", "currDaysCountFrom");
    saveCurrElem(form, "currDaysCountTo", "currDaysCountTo");
    saveCurrElem(form, "currPriceFrom", "currPriceFrom");
    saveCurrElem(form, "currPriceTo", "currPriceTo");
    saveCurrElem(form, "currDiscountFrom", "currDiscountFrom");
    saveBox(form, "allDepartDate");
    saveBox(form, "allDaysCount");
    saveBox(form, "allPrice");
    saveBox(form, "allCountries");
    saveBox(form, "allCities");
    saveBox(form, "allHotels");
    saveIsHidden(form, "searching");
    

    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "command";
    elem.value = comnd;
    form.appendChild(elem);
        
    document.body.appendChild(form);
    form.submit();
    
}

function saveIsHidden(form, idElem){
    var srch = document.getElementById("searching");
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "isHidden";
    elem.value=srch.hidden;
    form.appendChild(elem);
}

function saveBox(form, idElem){
    var elem = document.getElementById(idElem);
    if (elem.checked) {
        var box = document.createElement("input");
        box.type = "hidden";
        box.name = idElem;
        box.value=true;
        form.appendChild(box);
    }else {
        var box = document.createElement("input");
        box.type = "hidden";
        box.name = idElem;
        box.value= false;
        form.appendChild(box);
    }
}

function saveCurrElem(form, idElem, nameParam){
    var select = document.getElementById(idElem).value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = nameParam;
    elem.value=select;
    form.appendChild(elem);
}

function checkBox(boxId, val, elemId) {
    var box = document.getElementById(boxId);
    var elem = document.getElementById(elemId);
    box.checked = val;
    if (val) {
        elem.hidden = true;
    } else {
        elem.hidden = false;
    }
   
}

function disable(elemId, boxId) {
    var box = document.getElementById(boxId);
    var elem = document.getElementById(elemId);
    var val = box.checked;
    if (val) {
        elem.hidden = true;
    } else {
        elem.hidden = false;
    }
    
}

function hideSearching(bool){
    var elem = document.getElementById("searching");
    var show = document.getElementById("show");
    var hide = document.getElementById("hide");
    if (bool === true) {
        elem.hidden = true;
        hide.hidden = true;
        show.hidden = false;
    } else {
        elem.hidden = false;
        hide.hidden = false;
        show.hidden = true;
    }
 
}

function postHot(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveCurrHotelStars(form);
    saveCurrIdCountry(form);
    saveCurrIdCity(form);
    saveTextVal(form, "nameHotel");
    saveTextVal(form, "pictureHotel");
    saveMultiTextVal(form, "textDescription");

    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "command";
    elem.value = comnd;
    form.appendChild(elem);
        
    document.body.appendChild(form);
    form.submit();
    
}

function saveCurrHotelStars(form) {
    var select = document.getElementById("currStars").value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "currStars";
    elem.value=select;
    form.appendChild(elem);
}

function saveAllHotel() {
    var form = document.getElementById("updHotel");
    saveCurrHotelStars(form);
    saveCurrIdCountry(form);
    saveCurrIdCity(form);
    saveTextVal(form, "nameHotel");
    saveTextVal(form, "pictureHotel");
    saveMultiTextVal(form, "textDescription");
}

function saveAllCountry() {
    var form = document.getElementById("updCountry");
    saveTextVal(form, "nameCountry");
    saveTextVal(form, "pictureCountry");
    saveMultiTextVal(form, "textDescription");
}

function saveAllCity() {
    var form = document.getElementById("updCity");
    saveCurrIdCountry(form);
    saveTextVal(form, "nameCity");
    saveTextVal(form, "pictureCity");
    saveMultiTextVal(form, "textDescription");
}

function saveTextVal(form, name) {
    var tableInputTags = document.getElementsByTagName("input");
    for (var i=0; i<tableInputTags.length; i++) 
    {
        if(tableInputTags[i].type==="text")  
        {
            if (tableInputTags[i].name===name){
                var elem = document.createElement("input");
                elem.type = "hidden";
                elem.name = name;
                elem.value= tableInputTags[i].value;
                form.appendChild(elem);   
            }
        }
    }
}

function saveMultiTextVal(form, name) {
    var tableInputTags = document.getElementsByTagName("textarea");
    for (var i=0; i<tableInputTags.length; i++) 
    {
        if(tableInputTags[i].name=== name) {
            var elem = document.createElement("input");
            elem.type = "hidden";
            elem.name = name;
            elem.value= tableInputTags[i].value;
            form.appendChild(elem);   
        }
    }
}

function restoreCheck(str, txt){
    
    var form = document.getElementById("updDirection");
    var elem = document.getElementById("currHotelTag");
      
    var checkbox = document.createElement('input');
    checkbox.type = "checkbox";
    checkbox.name = "hotelTag";
    checkbox.value = str;
    checkbox.id = "id"+str;
    checkbox.checked = "true";


    var label = document.createElement('label');
    label.htmlFor = "id"+str;

    label.appendChild(document.createTextNode(txt));      
    elem.appendChild(checkbox);
    elem.appendChild(label);
    elem.appendChild(document.createElement("br"));
    
  
    
}

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
    if (atr !== null) {
        var select = document.getElementById(selName);
        select.value=atr;
    }
}

function saveAll(){
    var form = document.getElementById("updDirection");
    saveCountryTags(form);
    saveCityTags(form);
    saveMode(form);
    saveTourType(form);
    saveCurrIdCountry(form);
    saveCurrIdCity(form);
    saveHotelTags(form);
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
    saveHotelTags(form);
    saveTextValDir(form);
    saveMultitextValDir(form);

    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "command";
    elem.value = comnd;
    form.appendChild(elem);
        
    document.body.appendChild(form);
    form.submit();
    
}

function saveMultitextValDir(form) {
    var tableInputTags = document.getElementsByTagName("textarea");
    for (var i=0; i<tableInputTags.length; i++) 
    {
        if(tableInputTags[i].name==="textDirection") {
            var elem = document.createElement("input");
            elem.type = "hidden";
            elem.name = "textDirection";
            elem.value= tableInputTags[i].value;
            form.appendChild(elem);   
        }
        if(tableInputTags[i].name==="textDescription") {
            var elem = document.createElement("input");
            elem.type = "hidden";
            elem.name = "textDescription";
            elem.value= tableInputTags[i].value;
            form.appendChild(elem);   
        }
    }
}

function saveTextValDir(form) {
    var tableInputTags = document.getElementsByTagName("input");
    for (var i=0; i<tableInputTags.length; i++) 
    {
        if(tableInputTags[i].type==="text")  
        {
            if (tableInputTags[i].name==="nameDirection"){
                var elem = document.createElement("input");
                elem.type = "hidden";
                elem.name = "nameDirection";
                elem.value= tableInputTags[i].value;
                form.appendChild(elem);   
            }
            if (tableInputTags[i].name==="pictureDirection"){
                var elem = document.createElement("input");
                elem.type = "hidden";
                elem.name = "pictureDirection";
                elem.value= tableInputTags[i].value;
                form.appendChild(elem);   
            }
        }
    }
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
function saveStars(form){
    var select = document.getElementById("currStars").value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "currStars";
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

function saveHotelTags(form) {
    
    var tableInputTags = document.getElementsByTagName("input");
    for (var i=0; i<tableInputTags.length; i++) 
    {
        if(tableInputTags[i].type==="checkbox" && tableInputTags[i].name==="hotelTag") 
        {
            var tag = tableInputTags[i];
            var val = tag.value;
            if (tag.checked) {
                var elem = document.createElement("input");
                elem.type = "hidden";
                elem.name = "currHotelTag";
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
    var n = select.selectedIndex;
    if (i !== "0") {
        var txt = select.options[n].text;
    
        var checkbox = document.createElement('input');
        checkbox.type = "checkbox";
        checkbox.name = "hotelTag";
        checkbox.value = i;
        checkbox.id = "id"+i;
        checkbox.checked = "true";


        var label = document.createElement('label');
        label.htmlFor = "id"+i;

        label.appendChild(document.createTextNode(txt));      
        elem.appendChild(checkbox);
        elem.appendChild(label);
        elem.appendChild(document.createElement("br"));
    }
    
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
    var id = form.idCountry;
    id.setAttribute("value", n);
  
}

function selectCity(n){
    var form = document.getElementById("updHotel");
    var id = form.idCity;
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





