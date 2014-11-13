<%-- 
    Document   : edithotel
    Created on : 27.09.2014, 22:04:21
    Author     : User
--%>

<div id="main">
    <form id="updHotel" method="POST" action="controller" onsubmit="return validateHotelForm();">
        <div class="inner">
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="selectCountry" bundle="${ rb }" />: </h1>
                </div>
                <div class="input inner">
                    <select id="currCountry" class="selectContainer" size="1" onchange="postHot('controller', 'ifCountrySelected', 'POST')">      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach items="${countryList}" var="row">
                            <option class="selectItem" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                        </c:forEach>
                    </select>
                    <script>select("currCountry", ${currIdCountry});</script>
                </div>
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="selectCity" bundle="${ rb }" />: </h1>
                </div>
                <div class="input inner">
                    <select id="currCity" name="citySelection" class="selectContainer" size="1">      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach items="${currCityList}" var="row">
                            <option class="selectItem" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                        </c:forEach>
                    </select>
                    <div id="erNote"><a id="selectCityErrMsg" hidden="true"><fmt:message key="errorSelectCity" bundle="${ rb }" /></a></div> 
                    <script>select("currCity", ${currIdCity});</script>
                </div>
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="hotelName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="nameHotel" name="nameHotel" class="inputLineContainer"  value="${currHotel.name}"/>
                    <div id="erNote"><a id="nameErrMsg" hidden="true"><fmt:message key="errorName" bundle="${ rb }" /></a></div>
                </div>
            </div>
                    
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="selectHotelStars" bundle="${ rb }" />: </h1>
                </div>
                <div class="input inner">
                    <select id="currStars" name="starsSelection" class="selectContainer" size="1" >      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach var="i" begin="1" end="5">
                            <option class="selectItem" value="${i}">${i}*</option>
                        </c:forEach>
                    </select>
                    <script>select("currStars", ${currHotel.stars});</script>
                    <div id="erNote"><a id="selectStarsErrMsg" hidden="true"><fmt:message key="errorSelectStars" bundle="${ rb }" /></a></div>
                </div>
            </div>   
          
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="hotelPicture" bundle="${ rb }" />:</h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="pictureHotel" name="pictureHotel" class="inputLineContainer" value="${currHotel.picture}"/>
                    <div id="erNote"><a id="pictureErrMsg" hidden="true"><fmt:message key="errorPicture" bundle="${ rb }" /></a></div>
                </div>      
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="hotelDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="input inner">
                    <textarea name="textDescription" class="inputMultilineineContainer">${currHotel.description.text}</textarea>
                </div>
            </div>
            
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAllHotel('saveRedactHotel')" />
                <div id="erNote"><fmt:message key="${errorSave}" bundle="${ rb }" /></div>
                <div id="erNote"><fmt:message key="${errorSaveReason}" bundle="${ rb }" /></div>
            </div>
        </div>
    </form>
</div>

