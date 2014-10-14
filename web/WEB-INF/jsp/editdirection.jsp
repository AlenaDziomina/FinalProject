<%-- 
    Document   : editdirection
    Created on : 29.09.2014, 20:50:44
    Author     : User
--%>

<div id="main">
    <form id="updDirection" method="POST" action="controller">
        <div class="inner">
            <input type="hidden" name="command" value="saveRedactDirection" />
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
                    </div>
                    <h1 class="cntr labelH"><fmt:message key="selectTransMode" bundle="${ rb }" />:</h1>    
                    <div class="centraleContainer">
                        <select id="transMode" class="selectContainer" size="1">      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${transModeList}" var="row">
                                <option class="selectItem" value="${row.idMode}"><fmt:message key="${row.nameMode}" bundle="${ rb }" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                
                <div class="small padinput">         
                    <h1 class="cntr labelH"><fmt:message key="selectCountries" bundle="${ rb }" />: </h1>     
                    <div class="checkBoxGroup">
                        <c:forEach items="${countryTagList}" var="row">
                            <input type="checkbox" name="countryTag" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                        </c:forEach>
                    </div>  
                </div>    
                    
                <div class="small padinput">         
                    <h1 class="cntr labelH"><fmt:message key="selectCities" bundle="${ rb }" />: </h1>     
                    <div class="checkBoxGroup">
                        <c:forEach items="${cityTagList}" var="row">
                            <input type="checkbox" name="cityTag" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /><br>
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
            </script>
                
            <c:forEach items="${hotelTagList}" var="row">
                <script type='text/javascript'>
                    restoreCheck("${row.idHotel}", "${row.name}");
                </script>
            </c:forEach>
        
            <input type="hidden" name="idDirection" value="${currDirection.idDirection}"/>
            <div class="parameterRow">
                <div class="padinput">
                    <h1 class="cntr labelH"><fmt:message key="directName" bundle="${ rb }" />: </h1> 
                    <input type="text" class="large inputLineContainer" name="nameDirection" value="${currDirection.name}"/> 
                    <h1 class="cntr labelH"><fmt:message key="directPicture" bundle="${ rb }" />:</h1> 
                    <input type="text" class="large inputLineContainer" name="pictureDirection"   value="${currDirection.picture}"/>
                </div>
                <div class="large padinput">
                    <h1 class="cntr labelH"><fmt:message key="directText" bundle="${ rb }" />: </h1> 
                    <textarea name="textDirection" class="small inputMultilineineContainer">${currDirection.text}</textarea>
                </div>    
            </div>
            
            <input type="hidden" name="idDescription" value="${currDirection.description.idDescription}"/>
            <div class="parameterRow">
                <div class="small input">
                    <h1 class="labelH"><fmt:message key="directDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="large input inner">
                    <textarea name="textDescription" class="large inputMultilineineContainer">${currDirection.description.text}</textarea>
                </div>
            </div>        
                   
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAll()"/>
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
            </div>   
        </div>  
    </form>
</div>

