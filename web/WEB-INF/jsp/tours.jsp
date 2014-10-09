<%-- 
    Document   : tours
    Created on : 29.09.2014, 20:47:48
    Author     : User
--%>


<div id="main">
    <div class="parameterRowB" >
        <h2 class="labelHT">TourSearching</h2>
        <div id="show">
            <input class="small yellow awesome" type="submit" value="<fmt:message key="showParams" bundle="${ rb }" />" onclick="showHidden(false)"/>
        </div>
        <div id="hide">
            <input class="small yellow awesome" type="submit" value="<fmt:message key="hideParams" bundle="${ rb }" />" onclick="showHidden(true)"/>
        </div>

        <div id="search" class="parameterRow">
            <div class="tagColumn">
                <h1 class="labelHT"><fmt:message key="selectTourType" bundle="${ rb }" />:</h1>
                <div class="centraleContainer">
                    <select id="tourType" class="selectContainer" size="1">      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach items="${tourTypeList}" var="row">
                            <option class="selectItem" value="${row.idTourType}"><fmt:message key="${row.nameTourType}" bundle="${ rb }" /></option>   
                        </c:forEach>
                    </select>
                </div>
                <h1 class="labelHT"><fmt:message key="selectTransMode" bundle="${ rb }" />:</h1>    
                <div class="centraleContainer">
                    <select id="transMode" class="selectContainer" size="1">      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach items="${transModeList}" var="row">
                            <option class="selectItem" value="${row.idMode}"><fmt:message key="${row.nameMode}" bundle="${ rb }" /></option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="tagColumn">         
                <h1 class="labelHT"><fmt:message key="selectCountries" bundle="${ rb }" />: </h1>     
                <div class="checkBoxGroup">
                    <c:forEach items="${countryTagList}" var="row">
                        <input type="checkbox" name="countryTag" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                    </c:forEach>
                </div>  
            </div>    

            <div class="tagColumn">         
                <h1 class="labelHT"><fmt:message key="selectCities" bundle="${ rb }" />: </h1>     
                <div class="checkBoxGroup">
                    <c:forEach items="${cityTagList}" var="row">
                        <input type="checkbox" name="cityTag" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                    </c:forEach>
                </div>  
            </div>  
        </div>
                
        <script>
            showHidden(true);
        </script>
        
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