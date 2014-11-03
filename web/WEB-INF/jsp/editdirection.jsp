<%-- 
    Document   : editdirection
    Created on : 29.09.2014, 20:50:44
    Author     : User
--%>

<div id="main">
    <form id="updDirection" method="POST" action="controller" onsubmit="return validateDirectionForm();">
        <div class="inner">
            <div class="parameterRow">
                <div class="small padinput">
                    <h1 class="cntr labelH"><fmt:message key="selectTourType" bundle="${ rb }" />:</h1>
                    <div class="centraleContainer">
                        <select id="tourType" class="selectContainer" size="1">      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${tourTypeList}" var="row">
                                <option class="selectItem" value="${row.idTourType}"><fmt:message key="${row.nameTourType}" bundle="${ rb }" /></option>   
                            </c:forEach>
                        </select>
                        <div id="erNote"><a id="selectTourTypeErrMsg" hidden="true"><fmt:message key="errorSelectTourType" bundle="${ rb }" /></a></div>
                    </div>
                    <script>select("tourType", ${currDirection.tourType.idTourType});</script>
                            
                    <h1 class="cntr labelH"><fmt:message key="selectTransMode" bundle="${ rb }" />:</h1>    
                    <div class="centraleContainer">
                        <select id="transMode" class="selectContainer" size="1">      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${transModeList}" var="row">
                                <option class="selectItem" value="${row.idMode}"><fmt:message key="${row.nameMode}" bundle="${ rb }" /></option>
                            </c:forEach>
                        </select>
                        <div id="erNote"><a id="selectTransModeErrMsg" hidden="true"><fmt:message key="errorSelectTransMode" bundle="${ rb }" /></a></div>
                    </div>
                    <script>select("transMode", ${currDirection.transMode.idMode});</script>
                </div>
                
                <div class="small padinput">         
                    <h1 class="cntr labelH"><fmt:message key="selectCountries" bundle="${ rb }" />: </h1>     
                    <div class="checkBoxGroup">
                        <c:forEach items="${countryTagList}" var="row">
                            <input type="checkbox" name="countryTag" value="${row.idCountry}"/><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                        </c:forEach>
                    </div>  
                </div>    
                    
                <div class="small padinput">         
                    <h1 class="cntr labelH"><fmt:message key="selectCities" bundle="${ rb }" />: </h1>     
                    <div class="checkBoxGroup">
                        <c:forEach items="${cityTagList}" var="row">
                            <input type="checkbox" name="cityTag" value="${row.idCity}"/><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                        </c:forEach>
                    </div>  
                </div>  
            </div>
                
            <div class="parameterRowB">
                <div class="padinput">
                    <h1 class="cntr labelH"><fmt:message key="selectHotel" bundle="${ rb }" />:</h1>
                    <div id="currHotelTag" class="bger checkBoxGroup"></div>
                </div>
                <div class="large padinput">
                    <div class="mid padinput">
                        <h1 class="cntr labelH">1. <fmt:message key="selectCountry" bundle="${ rb }" />:</h1>
                        <div class="lftS centraleContainer">
                            <select id="currCountry" class="selectContainer" size="1" onchange="postDir('controller', 'ifCountrySelected', 'POST')">      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach items="${countryList}" var="row">
                                    <option class="selectItem" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mid padinput">
                        <h1 class="cntr labelH">2. <fmt:message key="selectCity" bundle="${ rb }" />:</h1>
                        <div class="lftS centraleContainer">
                            <select id="currCity" class="selectContainer" size="1" onchange="postDir('controller', 'ifCitySelected', 'POST')">      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach items="${cityList}" var="row">
                                    <option class="selectItem" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                </c:forEach>
                            </select> 
                        </div>
                    </div>
                    <div class="mid padinput">
                        <h1 class="cntr labelH">3. <fmt:message key="selectHotel" bundle="${ rb }" />:</h1>
                        <div class="lftS centraleContainer">
                            <select id="currHotel" class="selectContainer" size="1">      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach items="${hotelList}" var="row">
                                    <option class="selectItem" value="${row.idHotel}">${row.name}</option>
                                </c:forEach>
                            </select> 
                        </div> 
                    </div>
                    <div class="mid padinput">
                        <h1 class="cntr labelH">4. <fmt:message key="selectHotel" bundle="${ rb }" />:</h1>
                        <div class="lftL centraleContainer">
                            <label id="menuText" onclick="funcAdd()">ADD HOTEL</label>
                        </div>
                    </div>
                </div>
            </div>
                        
            <script>
                select("currCountry", ${currIdCountry});
                select("currCity", ${currIdCity});
            </script>        
                        
            <c:forEach items="${currCountryTag}" var="tag">
                <script>check("countryTag", ${tag});</script>
            </c:forEach>
                
            <c:forEach items="${currCityTag}" var="tag">
                <script>check("cityTag", ${tag});</script>
            </c:forEach>
                
            <c:forEach items="${hotelTagList}" var="row">
                <script>restoreCheck("${row.idHotel}", "${row.name}");</script>
            </c:forEach>
        
            <div class="parameterRow">
                <div class="padinput">
                    <h1 class="cntr labelH"><fmt:message key="directName" bundle="${ rb }" />: </h1> 
                    <input type="text" class="large inputLineContainer" id="nameDirection" name="nameDirection" value="${currDirection.name}"/> 
                    <div id="erNote"><a id="nameErrMsg" hidden="true"><fmt:message key="errorName" bundle="${ rb }" /></a></div>
                    
                    <h1 class="cntr labelH"><fmt:message key="directPicture" bundle="${ rb }" />:</h1> 
                    <input type="text" class="large inputLineContainer" id="pictureDirection" name="pictureDirection"   value="${currDirection.picture}"/>
                    <div id="erNote"><a id="pictureErrMsg" hidden="true"><fmt:message key="errorPicture" bundle="${ rb }" /></a></div>
                </div>
                <div class="large padinput">
                    <h1 class="cntr labelH"><fmt:message key="directText" bundle="${ rb }" />: </h1> 
                    <textarea id="textDirection" name="textDirection" class="small inputMultilineineContainer">${currDirection.text}</textarea>
                    <div id="erNote"><a id="textErrMsg" hidden="true"><fmt:message key="errorTextDir" bundle="${ rb }" /></a></div>
                </div>    
            </div>
            
            <div class="parameterRow">
                <div class="small input">
                    <h1 class="labelH"><fmt:message key="directDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="large input inner">
                    <textarea name="textDescription" class="large inputMultilineineContainer">${currDirection.description.text}</textarea>
                </div>
            </div>        
                   
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAllDirection('saveRedactDirection')"/>
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
            </div>   
        </div>  
    </form>
</div>

