/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//registration form validation
function validateForm() {
	var valid = isValidLogin() && isValidEmail() && isValidPhone() 
                && isValidPassword() && isValidRepeatPass();
        
        return valid;
}

//login validation
function isValidLogin() {
    var form = document.getElementById("registration");
    var login = form.login.value;
    var elem = document.getElementById("loginErrMsg");
    if (login === null || login === "") {
        elem.hidden = false;
        return false;
    }
    
    var re = new RegExp ('[\\S]{4,}');
    var pars = re.exec(login);
    if (pars !== null && pars.length === 1) {
        form.login.value = pars[0];
        elem.hidden = true;
        return true;
    } else {
        elem.hidden = false;
        return false;
    }
}

//email validation
function isValidEmail() {
    var form = document.getElementById("registration");
    var email = form.email.value;
    var elem = document.getElementById("emailErrMsg");
    if (email === null || email === "") {
        elem.hidden = false;
        return false;
    }
    
    var re = new RegExp ('\\w+@\\w+\\.[a-z]{2,}');
    var pars = re.exec(email);
    if (pars !== null) {
        form.email.value = pars[0];
        elem.hidden = true;
        return true;
    } else {
        elem.hidden = false;
        return false;
    }
}

//phone number validation
function isValidPhone() {
    var form = document.getElementById("registration");
    var phone = form.phone.value;
    var elem = document.getElementById("phoneErrMsg");
    if (phone === null || phone === "") {
        elem.hidden = false;
        return false;
    }
    
    var re = new RegExp ('\\+375\\-\\d{2}\\-\\d{3}\\-\\d{2}-\\d{2}');
    var pars = re.exec(phone);
    if (pars !== null) {
        form.phone.value = pars[0];
        elem.hidden = true;
        return true;
    } else {
        elem.hidden = false;
        return false;
    }
}

//password validation
function isValidPassword() {
    var form = document.getElementById("registration");
    var password = form.password.value;
    var elem = document.getElementById("passwordErrMsg");
    if (password === null || password === "") {
        elem.hidden = false;
        return false;
    }
    
    var re = new RegExp ('[\\w]{4,}');
    var pars = re.exec(password);
    if (pars !== null) {
        form.password.value = pars[0];
        elem.hidden = true;
        return true;
    } else {
        elem.hidden = false;
        return false;
    }
    
}

//repeat password validation
function isValidRepeatPass() {
    var form = document.getElementById("registration");
    var password = form.password.value;
    var repeatPass = form.repeatPassword.value;
    var elem = document.getElementById("repeatPassErrMsg");
    if (repeatPass !== null && repeatPass === password) {
        elem.hidden = true;
        return true;
    } else {
        elem.hidden = false;
        return false;
    }
}

//call servlet to action on click on select container
//in cities.jsp, countries.jsp, hotels.jsp
function post(path, params, method) {
    method = method || "post";
    
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveBox(form, "validCountryStatus");
    saveBox(form, "invalidCountryStatus");
    saveBox(form, "validCityStatus");
    saveBox(form, "invalidCityStatus");
    saveBox(form, "validHotelStatus");
    saveBox(form, "invalidHotelStatus");
    
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

//save hotel properties on editHotel.jsp when click save-button
function saveAllHotel() {
    var form = document.getElementById("updHotel");
    saveCurrElem(form, "currStars", "currStars");
    saveCurrElem(form, "currCountry", "currIdCountry");
    saveCurrElem(form, "currCity", "currIdCity");
    saveTextVal(form, "nameHotel");
    saveTextVal(form, "pictureHotel");
    saveMultiTextVal(form, "textDescription");
}

//save city properties on editCity.jsp when click save-button
function saveAllCity() {
    var form = document.getElementById("updCity");
    saveCurrElem(form, "currCountry", "currIdCountry");
    saveTextVal(form, "nameCity");
    saveTextVal(form, "pictureCity");
    saveMultiTextVal(form, "textDescription");
}

//save country properties on editCountry.jsp when click save-button
function saveAllCountry() {
    var form = document.getElementById("updCountry");
    saveTextVal(form, "nameCountry");
    saveTextVal(form, "pictureCountry");
    saveMultiTextVal(form, "textDescription");
}

//save parameters of hotels.jsp for reload page 
//according checked boxes of hotel's status
function postHotel(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveBox(form, "validHotelStatus");
    saveBox(form, "invalidHotelStatus");
    saveSelected(form, "currHotel", "selectId");
    saveCommand(form, comnd);

    document.body.appendChild(form);
    form.submit();
}

//save parameters of cities.jsp for reload page 
//according checked boxes of city's and hotel's status
function postCity(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveBox(form, "validCityStatus");
    saveBox(form, "invalidCityStatus");
    saveBox(form, "validHotelStatus");
    saveBox(form, "invalidHotelStatus");
    saveSelected(form, "currCity", "selectId");
    saveCommand(form, comnd);

    document.body.appendChild(form);
    form.submit();
}

//save parameters of countries.jsp for reload page 
//according checked boxes of country's and city's status
function postCountry(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveBox(form, "validCountryStatus");
    saveBox(form, "invalidCountryStatus");
    saveBox(form, "validCityStatus");
    saveBox(form, "invalidCityStatus");
    saveSelected(form, "currCountry", "selectId");
    saveCommand(form, comnd);
    
    document.body.appendChild(form);
    form.submit();
}

//save current params from jsp page
function saveCurrElem(form, idElem, nameParam){
    var select = document.getElementById(idElem).value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = nameParam;
    elem.value=select;
    form.appendChild(elem);
}

//save text input
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

//save multitext input
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

//save checked parameter of checkBox
function saveBox(form, idElem){
    var elem = document.getElementById(idElem);
    if (elem !== null) {
        var box = document.createElement("input");
        box.type = "hidden";
        box.name = idElem;
        box.value=elem.checked;
        form.appendChild(box);
    }
}

//save parameter from select multiline container
function saveSelected(form, elemId, elemName){
    var sel = document.getElementById(elemId);
    if (sel !== null) {
        var val = sel.value;
        if (val !== '') {
            var elem = document.createElement("input");
            elem.type = "hidden";
            elem.name = elemName;
            elem.value = val;
            form.appendChild(elem);
        }
    }
}

//save command name, colled on click
function saveCommand(form, comnd) {
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "command";
    elem.value = comnd;
    form.appendChild(elem);
}

//rechecking boxes when load or reload any page
function boxChecking(boxId, val) {
    var box = document.getElementById(boxId);
    if (box !== null) {
        box.checked = val;
    }
}

//reselecting in conteiner when load or reload any page
function select(selName, atr){
    if (atr !== null) {
        var select = document.getElementById(selName);
        if (select !== null) {
            select.value=atr;
        }
    }
}





function nextPage(){
    var elem = document.getElementsByTagName("PageTableTag");
    for (var i=0; i<elem.length; i++) 
    {
        var curr = elem[i];
        var t = curr.type;  
        var pageNo = curr.pageNo;
    }
        
    var n = elem.pageNo;
    elem.pageNo = n + 1;
}

function buyTour(path, comnd, method){
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "command";
    elem.value = comnd;
    form.appendChild(elem);
        
    var idTour = document.getElementById("currIdTour").value;
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "currIdTour";
    elem.value = idTour;
    form.appendChild(elem);
        
    document.body.appendChild(form);
    form.submit();
}


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

//function selectTourType(n){
//    var form = document.getElementById("updDirection");
//    var id = form.idTransMode;
//    id.setAttribute("value", n);
//}


//function selectCountry(n){
//    var form = document.getElementById("updCity");
//    var id = form.idCountry;
//    id.setAttribute("value", n);
//  
//}
//
//function selectCity(n){
//    var form = document.getElementById("updHotel");
//    var id = form.idCity;
//    id.setAttribute("value", n);
//    
//}
//
//function selectStars(n){
//    var form = document.getElementById("updHotel");
//    var id = form.stars_hotel;
//    id.setAttribute("value", n);
//}









