<%-- 
    Document   : tours
    Created on : 29.09.2014, 20:47:48
    Author     : User
--%>


<div id="main">
   
        
        <div class="parameterRowB" >
            <div id="show">
                <div class="labelColumn">
                    <h1 class="labelHT">TourSearching...</h1>
                </div>
                <div class="inputColumn">
                    <input class="yellow awesome" type="submit" value="<fmt:message key="showParams" bundle="${ rb }" />" onclick="hideSearching(false)"/>
                </div>
            </div>
            
            <div id="hide">
                <div class="labelColumn">
                    <h1 class="labelHT">TourSearching:</h1>
                </div>
                <div class="inputColumn">
                    <input class="yellow awesome" type="submit" value="<fmt:message key="hideParams" bundle="${ rb }" />" onclick="hideSearching(true)"/>
                </div>
            </div>

            <div id="searching">
                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="tourDepartDate" bundle="${ rb }" />:</label>
                    </div>
                    <div class="inputColumn">
                        <input type="checkbox" id="allDepartDate" name="departDateBox" class="boxMar" value="0" onchange="disable('departDate', 'allDepartDate')"><fmt:message key="anyDepartDate" bundle="${ rb }" /><br>
                        <div id="departDate">
                            <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/redmond/jquery-ui.css">
                            <script src="//code.jquery.com/jquery-1.10.2.js"></script>
                            <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
                            <link rel="stylesheet" href="/resources/demos/style.css">

                            <script>
                                $(function() {
                                    $( "#dateFrom" ).datepicker({ numberOfMonths: 3, showButtonPanel: true, firstDay: 1, minDate: 1, dateFormat: "yy-mm-dd",
                                        onClose: function( selectedDate ) {$( "#dateTo" ).datepicker( "option", "minDate", selectedDate );}});
                                    $( "#dateTo" ).datepicker({ numberOfMonths: 3, showButtonPanel: true, firstDay: 1, minDate: 1, dateFormat: "yy-mm-dd",
                                        onClose: function( selectedDate ) {$( "#dateFrom" ).datepicker( "option", "maxDate", selectedDate );}});
                                    $( "#dateFrom" ).datepicker( "setDate", "${currDepartDateFrom}");
                                    $( "#dateTo" ).datepicker( "setDate", "${currDepartDateTo}");   
                                    $( "#dateFrom" ).datepicker( "option", "dayNamesMin", [ 
                                        "<fmt:message key="daySu" bundle="${ rb }" />", 
                                        "<fmt:message key="dayMo" bundle="${ rb }" />", 
                                        "<fmt:message key="dayTu" bundle="${ rb }" />",
                                        "<fmt:message key="dayWe" bundle="${ rb }" />", 
                                        "<fmt:message key="dayTh" bundle="${ rb }" />", 
                                        "<fmt:message key="dayFr" bundle="${ rb }" />", 
                                        "<fmt:message key="daySa" bundle="${ rb }" />" ] ); 
                                    $( "#dateFrom" ).datepicker( "option", "monthNames", [ 
                                        "<fmt:message key="January" bundle="${ rb }" />", 
                                        "<fmt:message key="February" bundle="${ rb }" />", 
                                        "<fmt:message key="March" bundle="${ rb }" />",
                                        "<fmt:message key="April" bundle="${ rb }" />",
                                        "<fmt:message key="May" bundle="${ rb }" />",
                                        "<fmt:message key="June" bundle="${ rb }" />",
                                        "<fmt:message key="July" bundle="${ rb }" />",
                                        "<fmt:message key="August" bundle="${ rb }" />",
                                        "<fmt:message key="September" bundle="${ rb }" />",
                                        "<fmt:message key="October" bundle="${ rb }" />",
                                        "<fmt:message key="November" bundle="${ rb }" />",
                                        "<fmt:message key="December" bundle="${ rb }" />" ] );
                                    $( "#dateTo" ).datepicker( "option", "dayNamesMin", [ 
                                        "<fmt:message key="daySu" bundle="${ rb }" />", 
                                        "<fmt:message key="dayMo" bundle="${ rb }" />", 
                                        "<fmt:message key="dayTu" bundle="${ rb }" />",
                                        "<fmt:message key="dayWe" bundle="${ rb }" />", 
                                        "<fmt:message key="dayTh" bundle="${ rb }" />", 
                                        "<fmt:message key="dayFr" bundle="${ rb }" />", 
                                        "<fmt:message key="daySa" bundle="${ rb }" />" ] ); 
                                    $( "#dateTo" ).datepicker( "option", "monthNames", [ 
                                        "<fmt:message key="January" bundle="${ rb }" />", 
                                        "<fmt:message key="February" bundle="${ rb }" />", 
                                        "<fmt:message key="March" bundle="${ rb }" />",
                                        "<fmt:message key="April" bundle="${ rb }" />",
                                        "<fmt:message key="May" bundle="${ rb }" />",
                                        "<fmt:message key="June" bundle="${ rb }" />",
                                        "<fmt:message key="July" bundle="${ rb }" />",
                                        "<fmt:message key="August" bundle="${ rb }" />",
                                        "<fmt:message key="September" bundle="${ rb }" />",
                                        "<fmt:message key="October" bundle="${ rb }" />",
                                        "<fmt:message key="November" bundle="${ rb }" />",
                                        "<fmt:message key="December" bundle="${ rb }" />" ] );
                                });
                            </script>
                            <label class="labelHRm"><fmt:message key="from" bundle="${ rb }" /></label>
                            <input type="text" id="dateFrom" name="departDateFrom" class="inputLineContainer" >
                            <label class="labelHRm"><fmt:message key="to" bundle="${ rb }" />:</label>
                            <input type="text" id="dateTo" name="departDateTo" class="inputLineContainer" >
                        </div>
                    </div>
                </div>

                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="tourDaysCount" bundle="${ rb }" />:</label>
                    </div>
                    <div class="inputColumn">
                        <input type="checkbox" id="allDaysCount" name="daysCountBox" class="boxMar" value="0" onchange="disable('daysCount', 'allDaysCount')"><fmt:message key="anyDaysCount" bundle="${ rb }" /><br>
                        <div id="daysCount">
                            <label class="labelHRm"><fmt:message key="from" bundle="${ rb }" /></label>
                            <select id="currDaysCountFrom" name="daysCountFromSelection" class="selectContainer" size="1" >      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach var="i" begin="1" end="45">
                                    <option class="selectItem" value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                            <label class="labelHRm"><fmt:message key="to" bundle="${ rb }" />:</label>
                            <select id="currDaysCountTo" name="daysCountToSelection" class="selectContainer" size="1" >      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach var="i" begin="1" end="45">
                                    <option class="selectItem" value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="tourPrice" bundle="${ rb }" />:</label>
                    </div>
                    <div class="inputColumn">
                        <input type="checkbox" id="allPrice" name="priceBox" class="boxMar" value="0" onchange="disable('prices', 'allPrice')"><fmt:message key="anyPrice" bundle="${ rb }" /><br>
                        <div id="prices">
                            <label class="labelHRm"><fmt:message key="from" bundle="${ rb }" /></label>
                            <select id="currPriceFrom" name="priceFromSelection" class="selectContainer" size="1" >      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach var="i" begin="1" end="20">
                                    <option class="selectItem" value="${i}">${i*200}</option>
                                </c:forEach>
                            </select>
                            <label class="labelHRm"><fmt:message key="to" bundle="${ rb }" />:</label>
                            <select id="currPriceTo" name="priceToSelection" class="selectContainer" size="1" >      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach var="i" begin="1" end="20">
                                    <option class="selectItem" value="${i}">${i*200}</option>
                                </c:forEach>
                            </select>
                            <label class="labelHRm"><fmt:message key="$" bundle="${ rb }" /></label>
                        </div>                 
                    </div>
                </div>
                        
                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="tourDiscount" bundle="${ rb }" />:</label>
                    </div>
                    <div class="inputColumn">
                        <label class="labelHRm"><fmt:message key="from" bundle="${ rb }" /></label>
                        <select id="currDiscountFrom" name="discountFromSelection" class="selectContainer" size="1" >      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach var="i" begin="1" end="10">
                                <option class="selectItem" value="${i}">${i*10}</option>
                            </c:forEach>
                        </select>
                        <label class="labelHRm">%</label>
                    </div>
                </div>     
                            
                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="selectHotelStars" bundle="${ rb }" />:</label>    
                    </div>
                    <div class="inputColumn">
                        <label class="labelHRm"><fmt:message key="from" bundle="${ rb }" /></label>
                        <select id="currStars" name="starsSelection" class="selectContainer" size="1" >      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach var="i" begin="1" end="5">
                                <option class="selectItem" value="${i}">${i}*</option>
                            </c:forEach>
                        </select>
                    </div>    
                </div>                

                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="selectTourType" bundle="${ rb }" />:</label>
                    </div>
                    <div class="inputColumn">
                        <select id="tourType" class="selectContainer" size="1">      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach items="${tourTypeList}" var="row">
                                    <option class="selectItem" value="${row.idTourType}"><fmt:message key="${row.nameTourType}" bundle="${ rb }" /></option>   
                                </c:forEach>
                        </select>
                    </div>    
                </div>
                                
                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="selectTransMode" bundle="${ rb }" />:</label>    
                    </div>
                    <div class="inputColumn">
                        <select id="transMode" class="selectContainer" size="1">      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${transModeList}" var="row">
                                <option class="selectItem" value="${row.idMode}"><fmt:message key="${row.nameMode}" bundle="${ rb }" /></option>
                            </c:forEach>
                        </select>
                    </div>    
                </div>
                            
                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="selectCountries" bundle="${ rb }" />: </label>  
                    </div>
                    <div class="inputColumn">
                        <input type="checkbox" id="allCountries" name="countryTag" class="boxMar" value="0" onchange="disable('countries', 'allCountries')"><fmt:message key="anyCountry" bundle="${ rb }" /><br>
                        <div id="countries" class="checkBoxGr">
                            <c:forEach items="${countryTagList}" var="row">
                                <input type="checkbox" name="countryTag" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                            </c:forEach>
                        </div>  
                    </div>
                </div>
                        
                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="selectCities" bundle="${ rb }" />: </label>  
                    </div>
                    <div class="inputColumn">
                        <input type="checkbox" id="allCities" name="cityTag" class="boxMar" value="0" onchange="disable('cities', 'allCities')"><fmt:message key="anyCity" bundle="${ rb }" /><br>
                        <div id="cities" class="checkBoxGr">
                            <c:forEach items="${cityTagList}" var="row">
                                <input type="checkbox" name="cityTag" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                            </c:forEach>
                        </div>  
                    </div>
                </div>
                        
                <div class="parameterRow">
                    <div class="labelColumn">
                        <label class="labelHL"><fmt:message key="selectHotels" bundle="${ rb }" />: </label>  
                    </div>
                    <div class="inputColumn">
                        <input type="checkbox" id="allHotels" name="hotelTag" class="boxMar" value="0" onchange="disable('hotels', 'allHotels')"><fmt:message key="anyHotel" bundle="${ rb }" /><br>
                        <div id="hotels" class="parameterRow">
                            <div class="tagColumnS">
                                <div id="currHotelTag" class="checkBoxGr"></div>
                            </div>
                            <div class="tagColumnM">
                                <div class="parameterRow">
                                    <div class="labelColumn">
                                        <label class="labelHLm">1. <fmt:message key="selectCountry" bundle="${ rb }" />:</label>
                                    </div>
                                    <div class="inputColumn">
                                        <select id="currCountry" class="selectContainer" size="1" onchange="postTour('controller', 'ifCountrySelected', 'POST')">      
                                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                            <c:forEach items="${countryList}" var="row">
                                                <option class="selectItem" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="parameterRow">
                                    <div class="labelColumn">
                                        <label class="labelHLm">2. <fmt:message key="selectCity" bundle="${ rb }" />:</label>
                                    </div>
                                    <div class="inputColumn">
                                        <select id="currCity" class="selectContainer" size="1" onchange="postTour('controller', 'ifCitySelected', 'POST')">      
                                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                            <c:forEach items="${cityList}" var="row">
                                                <option class="selectItem" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                </div>
                                <div class="parameterRow">
                                    <div class="labelColumn">
                                        <label class="labelHLm">3. <fmt:message key="selectHotel" bundle="${ rb }" />:</label>
                                    </div>
                                    <div class="inputColumn">
                                        <select id="currHotel" class="selectContainer" size="1">      
                                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                            <c:forEach items="${hotelList}" var="row">
                                                <option class="selectItem" value="${row.idHotel}">${row.name}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                </div>
                                <div class="parameterRow">
                                    <div class="labelColumn">
                                        <label class="labelHLm">4. <fmt:message key="selectHotel" bundle="${ rb }" />:</label>
                                    </div>
                                    <div class="inputColumn">
                                        <a class="small blue awesome" onclick="funcAdd()">ADD HOTEL</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <form id="srcTour" name="searchTour" method="POST" action="controller">
                    <div class="parameterRow centrale">
                        <input class="large green awesome" type="submit" value="<fmt:message key="search" bundle="${ rb }" />" onclick="saveAllSearch()" />
                        <input type="hidden" name="command" value="searchTour" />
                        <div id="erNote">${errorSaveData}</div>
                        <div id="erNote">${errorReason}</div>
                        <div id="erAdminNote">${errorAdminMsg}</div>
                    </div>
                </form>                        
                

            </div>

            <script>
                hideSearching(${isHidden});
                checkBox('allDepartDate', ${allDepartDate}, 'departDate');
                checkBox('allDaysCount', ${allDaysCount}, 'daysCount');
                checkBox('allPrice', ${allPrice}, 'prices');
                checkBox('allCountries', ${allCountries}, 'countries');
                checkBox('allCities', ${allCities}, 'cities');
                checkBox('allHotels', ${allHotels}, 'hotels');
            </script>

            <c:forEach items="${currCountryTag}" var="tag">
                <script type="text/javascript">
                    check("countryTag", ${tag});
                </script>
            </c:forEach>

            <c:forEach items="${currCityTag}" var="tag">
                <script type="text/javascript">
                    check("cityTag", ${tag});
                </script>
            </c:forEach>

            <script type="text/javascript">
                select("tourType", ${currTourType});
                select("transMode", ${currTransMode});
                select("currCountry", ${currIdCountry});
                select("currCity", ${currIdCity});
                select("currStars", ${currStars});
                select("currPriceFrom", ${currPriceFrom});
                select("currPriceTo", ${currPriceTo});
                select("currDiscountFrom", ${currDiscountFrom});
                select("currDaysCountFrom", ${currDaysCountFrom});
                select("currDaysCountTo", ${currDaysCountTo});
            </script>

            <c:forEach items="${hotelTagList}" var="row">
                <script type='text/javascript'>
                    restoreCheck("${row.idHotel}", "${row.name}");
                </script>
            </c:forEach>

        </div>
    
                
        
    <c:forEach items="${tourList}" var="row">
        <div class="parameterRowB">
            <img class="smallimg" id="images" src="<%=request.getContextPath()%>${row.direction.picture}">
            <div class="innerColumnD">
                <a class="labelHTD" href="controller?command=showDirection&selectId=${row.direction.idDirection}">${row.direction.name} ${row.departDate}</a>
            </div>
            <h2 class="labelHD">${row.direction.text}</h2>
            <h2 class="labelHD">${row.price}</h2>
            <div>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCountryTags:</div>
                    <c:forEach items="${row.direction.countryCollection}" var="cntr">
                        <li>${cntr.name}</li>
                    </c:forEach>
                </ul>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCityTags:</div>
                    <c:forEach items="${row.direction.cityCollection}" var="ct">
                        <li>${ct.name}</li>
                    </c:forEach>
                </ul>
                <ul class="containerLabelR">
                    <div class="lblH">DirectionStayHotels:</div>
                    <c:forEach items="${row.direction.stayCollection}" var="st">
                        <li>${st.hotel.name} ${st.hotel.stars}* (${st.hotel.city.name}) </li>
                    </c:forEach>
                </ul>
            </div>                     
        </div>
    </c:forEach>
    
</div>