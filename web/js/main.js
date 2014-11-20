/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//validate form of creating order and tourists
function validateOrderForm(){
    var validFirstName = validateFIO('firstName');
    var validMiddleName = validateFIO('middleName');
    var validLastName = validateFIO('lastName');
    var validPassport = validatePassport('passport');
    var valid = validFirstName && validMiddleName && validLastName && validPassport;
    return valid;
}

//validate and coloring false input strings
function validatePassport(name) {
    var flag = true;
    var elem = document.getElementsByTagName('input');
    for (var i = 0; i < elem.length; i++) {
        if (elem[i].className === name || elem[i].className === 'red ' + name) {
            if (validateStrPassport(elem[i].value)) {
                elem[i].className = name;
            } else {
                elem[i].className = 'red ' + name;
                flag = false;
            }
        }
    }
    return flag;
}

//validate passport string in format AA1234567
function validateStrPassport(str) {
    if (str === null || str === '') {
        return false;
    }
    var re = new RegExp ('[A-Z]{2}\\d{7}');
    var pars = re.exec(str);
    if (pars !== null && pars.length === 1&& str === pars[0]) {
        return true;
    } else {
        return false;
    }
}

//validate and coloring false input strings
function validateFIO(name){
    var flag = true;
    var elem = document.getElementsByTagName('input');
    for (var i = 0; i < elem.length; i++) {
        if (elem[i].className === name || elem[i].className === 'red ' + name) {
            if (validateStrFio(elem[i].value)) {
                elem[i].className = name;
            } else {
                elem[i].className = 'red ' + name;
                flag = false;
            }
        }
    }
    return flag;
}

//validate first, middle and last name strings
function validateStrFio(str) {
    if (str === null || str === '' || str.length > 60) {
        return false;
    }
    var re = new RegExp ('[а-яёA-ЯЁ a-zA-Z\'\-]+');
    var pars = re.exec(str);
    if (pars !== null && pars.length === 1 && str === pars[0]) {
        return true;
    } else {
        return false;
    }
}

//add row in table of tourists on editorder.jsp
function funcAddTourist(currSeats){
    var table = document.getElementById("currTourists");
    var size = table.lastElementChild.childElementCount;
    while (size < currSeats) {
        var row = createRow();
        table.lastElementChild.appendChild(row);
        size = table.lastElementChild.childElementCount;
        
    }
    for (var i = currSeats; i < size; i++) {
        table.deleteRow(i);
    }
}

//create new row on tourist table
function createRow(){
    var tr = document.createElement('tr');
    tr.appendChild(createCell('lastName'));
    tr.appendChild(createCell('firstName'));
    tr.appendChild(createCell('middleName'));
    tr.appendChild(createCell('passport'));
    
    return tr;
}

//create new cell on row in tourist table
function createCell(name){
    var td = document.createElement('td');
    var input = document.createElement('input');
    input.type = 'text';
    input.name = name;
    input.className = name;
    td.appendChild(input);
    return td;
}

//validate edittour.jsp
function validateTourForm(){
    var valid = isValidDate("from", "dateErrMsg")
        && isValidDate("to", "dateErrMsg")
        && isPositiveFloat(12, "price", "priceErrMsg")
        && isValidDiscount(10, "discount", "discountErrMsg")
        && isPositiveIntegerValid(10, "totalSeats", "totalSeatsErrMsg")
        && isValidFreeSeats(10, "freeSeats", "freeSeatsErrMsg", "totalSeats");
    
    return valid;
}

function isValidDate(elemId, errId) {
    var elem = document.getElementById(elemId);
    var errElem = document.getElementById(errId);
    if (elem === null || elem.value === '') {
        errElem.hidden = false;
        return false;
    }
    
    var re = new RegExp ('\\d{4}\\-\\d{2}\\-\\d{2}');
    var pars = re.exec(elem.value);
    if (pars !== null) {
        elem.value = pars[0];
        errElem.hidden = true;
        return true;
    } else {
        errElem.hidden = false;
        return false;
    }
    
}

//validate positive float value
function isPositiveFloat(size, elemId, errId){
    var elem = document.getElementById(elemId);
    var errElem = document.getElementById(errId);
    if (elem === null || elem.value === '' || elem.value.length > size) {
        errElem.hidden = false;
        return false;
    }
    
    var re = new RegExp('\\d[\\.]?\\d');
    var pars = re.exec(elem.value);
    if (pars !== null && pars.length === 1) {
        elem.value = parseFloat(elem.value);
        errElem.hidden = true;
        return true;
    } else {
        errElem.hidden = false;
        return false;
    }
}

//validate positive integer value
function isPositiveIntegerValid(size, elemId, errId){
    var elem = document.getElementById(elemId);
    var errElem = document.getElementById(errId);
    if (elem === null || elem.value === '' || elem.value.length > size) {
        errElem.hidden = false;
        return false;
    }
    
    var re = new RegExp ('[\\d]{1,10}');
    var pars = re.exec(elem.value);
    if (pars !== null && pars.length === 1) {
        elem.value = pars[0];
        errElem.hidden = true;
        return true;
    } else {
        errElem.hidden = false;
        return false;
    }
}

//validate discount
function isValidDiscount(size, elemId, errId) {
    if (isPositiveIntegerValid(size, elemId, errId)) {
        var elem = document.getElementById(elemId);
        var errElem = document.getElementById(errId);
        var str = elem.value;
        var i = parseInt(str);
        if (i <= 100) {
            errElem.hidden = true;
            return true;
        } else {
            errElem.hidden = false;
            return false;
        }
    } else {
        return false;
    }
}

//validate free seats
function isValidFreeSeats(size, freeId, errId, totalId) {
    if (isPositiveIntegerValid(size, freeId, errId)) {
        var totalElem = document.getElementById(totalId);
        var freeElem = document.getElementById(freeId);
        var errElem = document.getElementById(errId);
        var strTotal = totalElem.value;
        var intTotal = parseInt(strTotal);
        var strFree = freeElem.value;
        var intFree = parseInt(strFree);
        if (intFree <= intTotal) {
            errElem.hidden = true;
            return true;
        } else {
            errElem.hidden = false;
            return false;
        }
    } else {
        return false;
    }
}

//validate editdirection.jsp
function validateDirectionForm(){
    var valid = isSelectedElem("tourType", "selectTourTypeErrMsg") 
            && isSelectedElem("transMode", "selectTransModeErrMsg")
            && isStringValid(80, "nameDirection", "nameErrMsg") 
            && isStringValid(60, "pictureDirection", "pictureErrMsg") 
            && isStringValid(63355, "textDirection", "textErrMsg");
    return valid;
}

//validate edithotel.jsp
function validateHotelForm(){
    var valid = isSelectedElem("currCity", "selectCityErrMsg") 
            && isStringValid(40, "nameHotel", "nameErrMsg") 
            &&  isSelectedElem("currStars", "selectStarsErrMsg")
            && isStringValid(60, "pictureHotel", "pictureErrMsg");
    return valid;
}

//validate editcity.jsp
function validateCityForm(){
    var valid =  isSelectedElem("currCountry", "selectCountryErrMsg") 
            && isStringValid(40, "nameCity", "nameErrMsg") 
            && isStringValid(60, "pictureCity", "pictureErrMsg");
    return valid;
}

//validate editcountry.jsp
function validateCountryForm(){
    var valid = isStringValid(40, "nameCountry", "nameErrMsg") 
            && isStringValid(60, "pictureCountry", "pictureErrMsg");
    return valid;
}

//if text input is valid length
function isStringValid(size, elemId, errId){
    var elem = document.getElementById(elemId);
    var errElem = document.getElementById(errId);
    if (elem !== null && elem.value.length > 0 && elem.value.length <= size) {
        errElem.hidden = true;
        return true;
    } else {
        errElem.hidden = false;
        return false;
    }
}

//is any element in container selected
function isSelectedElem(idElem, errId){
    var select = document.getElementById(idElem).value;
    var errElem = document.getElementById(errId);
    if (select !== null && select > 0) {
        errElem.hidden = true;
        return true;
    } else {
        errElem.hidden = false;
        return false;
    }
}

//registration form validation
function validateForm(formId) {
	var valid = isValidLogin(formId) && isValidEmail(formId) 
                && isValidPhone(formId) && isValidPassword(formId) 
                && isValidRepeatPass(formId);
        
        return valid;
}

//login validation
function isValidLogin(formId) {
    var form = document.getElementById(formId);
    var login = form.login.value;
    var size = 20;
    var elem = document.getElementById("loginErrMsg");
    if (login === null || login === "" || login.length > size) {
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
function isValidEmail(formId) {
    var form = document.getElementById(formId);
    var email = form.email.value;
    var size = 60;
    var elem = document.getElementById("emailErrMsg");
    if (email === null || email === "" || email.length > size) {
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
function isValidPhone(formId) {
    var form = document.getElementById(formId);
    var phone = form.phone.value;
    var size = 17;
    var elem = document.getElementById("phoneErrMsg");
    if (phone === null || phone === "" || phone.length > size) {
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
function isValidPassword(formId) {
    var form = document.getElementById(formId);
    var password = form.password.value;
    var size = 20;
    var elem = document.getElementById("passwordErrMsg");
    if (password === null || password === "" || password.length > size) {
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
function isValidRepeatPass(formId) {
    var form = document.getElementById(formId);
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
//in cities.jsp, countries.jsp, hotels.jsp, direction.jsp
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
    saveBox(form, "validTourStatus");
    saveBox(form, "invalidTourStatus");
    saveBox(form, "validTourDate");
    saveBox(form, "invalidTourDate");
    
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

//save tour properties in editTour.jsp when click save-button
function saveAllTour(command) {
    if (validateTourForm()){
        var form = document.getElementById("updTour");
        saveCommand(form, command);
    }
}

//save direction properties in editDirection.jsp when click save-button
function saveAllDirection(command){
    if (validateDirectionForm()) {
        var form = document.getElementById("updDirection");
        saveCommand(form, command);
        saveCurrElem(form, "currCountry", "currIdCountry");
        saveCurrElem(form, "currCity", "currIdCity");
        saveCurrElem(form, "transMode", "currTransMode");
        saveCurrElem(form, "tourType", "currTourType");
        saveCurrTags(form, "countryTag", "currCountryTag");
        saveCurrTags(form, "cityTag", "currCityTag");
        saveCurrTags(form, "hotelTag", "currHotelTag");
    }
}

//save selected check-boxes in container 
function saveCurrTags(form, tagName, elemName){
    var tableInputTags = document.getElementsByTagName("input");
    for (var i=0; i<tableInputTags.length; i++) 
    {
        if(tableInputTags[i].type === "checkbox" && tableInputTags[i].name === tagName) 
        {
            var tag = tableInputTags[i];
            var val = tag.value;
            if (tag.checked) {
                var elem = document.createElement("input");
                elem.type = "hidden";
                elem.name = elemName;
                elem.value=val;
                form.appendChild(elem);
            }
        }
    }
}

//save hotel properties on editHotel.jsp when click save-button
function saveAllHotel(command) {
    if (validateHotelForm()) {
        var form = document.getElementById("updHotel");
        saveCommand(form, command);
        saveCurrElem(form, "currStars", "currStars");
        saveCurrElem(form, "currCountry", "currIdCountry");
        saveCurrElem(form, "currCity", "currIdCity");
        saveTextVal(form, "nameHotel");
        saveTextVal(form, "pictureHotel");
        saveMultiTextVal(form, "textDescription");
    }
}

//save city properties on editCity.jsp when click save-button
function saveAllCity(command) {
    if (validateCityForm()) {
        var form = document.getElementById("updCity");
        saveCommand(form, command);
        saveCurrElem(form, "currCountry", "currIdCountry");
        saveTextVal(form, "nameCity");
        saveTextVal(form, "pictureCity");
        saveMultiTextVal(form, "textDescription");
    }
}

//save country properties on editCountry.jsp when click save-button
function saveAllCountry(command) {
    if (validateCountryForm()) {
        var form = document.getElementById("updCountry");
        saveCommand(form, command);
        saveTextVal(form, "nameCountry");
        saveTextVal(form, "pictureCountry");
        saveMultiTextVal(form, "textDescription");
    }
}

//go edit or block user
function postUser(path, comnd, method) {
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    saveCommand(form, comnd);
    document.body.appendChild(form);
    form.submit();
}

//go edit or cancel order
function postOrder(path, comnd, method) {
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    saveCommand(form, comnd);
    document.body.appendChild(form);
    form.submit();
}

//save order properties of userorder.jsp and orders.jsp
function postOrders(path, comnd, method) {
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveCommand(form, comnd);
    saveBox(form, "validOrderStatus");
    saveBox(form, "invalidOrderStatus");
    saveCurrElem(form, "currIdTour", "currIdTour");
    
    document.body.appendChild(form);
    form.submit();
}

//save parameters on tour.jsp and direction.jsp when check boxes of tour
function postTourDir(path, comnd, method) {
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveBox(form, "validTourStatus");
    saveBox(form, "invalidTourStatus");
    saveBox(form, "validTourDate");
    saveBox(form, "invalidTourDate");
    saveCommand(form, comnd);
    
    document.body.appendChild(form);
    form.submit();
}

//save parameters of editdirection.jsp when country or city selected
//and relatively city or hotel list is changed 
function postDir(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveCommand(form, comnd);
    saveCurrElem(form, "currCountry", "currIdCountry");
    saveCurrElem(form, "currCity", "currIdCity");
    saveCurrElem(form, "transMode", "currTransMode");
    saveCurrElem(form, "tourType", "currTourType");
    saveCurrTags(form, "countryTag", "currCountryTag");
    saveCurrTags(form, "cityTag", "currCityTag");
    saveCurrTags(form, "hotelTag", "currHotelTag");
    saveTextVal(form, "nameDirection");
    saveTextVal(form, "pictureDirection");
    saveMultiTextVal(form, "textDirection");
    saveMultiTextVal(form, "textDescription");
       
    document.body.appendChild(form);
    form.submit();
}

//add selected hotel as checkbox in container of hotel-tags
//on editdirection.jsp
function funcAdd(){
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

//save parameters of directions.jsp for reload page 
//according checked boxes of direction's status
function postDirections(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveBox(form, "validDirectionStatus");
    saveBox(form, "invalidDirectionStatus");
    saveCommand(form, comnd);
    
    document.body.appendChild(form);
    form.submit();
}

//change city-list if selected country on edithotel.jsp
function postHot(path, comnd, method) {
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveSelected(form, "currStars", "currStars");
    saveSelected(form, "currCountry", "currIdCountry");
    saveSelected(form, "currCity", "currIdCity");
    saveTextVal(form, "nameHotel");
    saveTextVal(form, "pictureHotel");
    saveMultiTextVal(form, "textDescription");
    saveCommand(form, comnd);
        
    document.body.appendChild(form);
    form.submit();
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
    if (select !== null && (select > 0 || select !== '')) {
        var elem = document.createElement("input");
        elem.type = "hidden";
        elem.name = nameParam;
        elem.value=select;
        form.appendChild(elem);
    }
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

//restore checked hotels in hotelTagList
function restoreCheck(str, txt){
    
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

//check checkBox
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

//hide part of searching parameters
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

//hide all searching parameters
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

//check checkBox
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

//save parameters of buyTour
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

//save all searching parameters when pressed button 'search'
function saveAllSearch() {
    var form = document.getElementById("srcTour");
    saveCurrTags(form, "countryTag", "currCountryTag");
    saveCurrTags(form, "cityTag", "currCityTag");
    saveCurrTags(form, "hotelTag", "currHotelTag");
    saveCurrElem(form, "currStars", "currStars");
    saveCurrElem(form, "transMode", "currTransMode");
    saveCurrElem(form, "tourType", "currTourType");
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

//save searching parameters of tours.jsp when country or city selected
//and list of cities or hotels must be changed
function postTour(path, comnd, method) {
    
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    
    saveCurrTags(form, "countryTag", "currCountryTag");
    saveCurrTags(form, "cityTag", "currCityTag");
    saveCurrTags(form, "hotelTag", "currHotelTag");
    saveCurrElem(form, "currStars", "currStars");
    saveCurrElem(form, "transMode", "currTransMode");
    saveCurrElem(form, "tourType", "currTourType");
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
    saveCommand(form, comnd);
        
    document.body.appendChild(form);
    form.submit();
}

//save option of hidden parameters
function saveIsHidden(form, idElem){
    var srch = document.getElementById(idElem);
    var elem = document.createElement("input");
    elem.type = "hidden";
    elem.name = "isHidden";
    elem.value=srch.hidden;
    form.appendChild(elem);
}







