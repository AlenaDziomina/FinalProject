<%-- 
    Document   : editdirection
    Created on : 29.09.2014, 20:50:44
    Author     : User
--%>

<div id="main">
    
    <form id="updDirection" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactDirection" />
            
            <div class="parameterRow">
                
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
                    
                    
                    
                    
                
            <div class="parameterRowB">
                <div class="tagColumnS">
                    <h1 class="labelHT"><fmt:message key="selectHotel" bundle="${ rb }" />:</h1>
                    <div id="currHotelTag" class="checkBoxGroup2">
                        
                    </div>
                </div>
                
                <div class="tagColumnM">
                    <div class="tagColumnL">
                        <h1 class="labelHT">1. <fmt:message key="selectCountry" bundle="${ rb }" />:</h1>
                        <div class="centraleContainerS">
                            <select id="currCountry" class="selectContainer" size="1" onchange="postDir('controller', 'ifCountrySelected', 'POST')">      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach items="${countryList}" var="row">
                                    <option class="selectItem" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="tagColumnL">
                        <h1 class="labelHT">2. <fmt:message key="selectCity" bundle="${ rb }" />:</h1>
                        <div class="centraleContainerS">
                            <select id="currCity" class="selectContainer" size="1" onchange="postDir('controller', 'ifCitySelected', 'POST')">      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach items="${cityList}" var="row">
                                    <option class="selectItem" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                </c:forEach>
                            </select> 
                        </div>
                    </div>

                    <div class="tagColumnL">
                        <h1 class="labelHT">3. <fmt:message key="selectHotel" bundle="${ rb }" />:</h1>
                        <div class="centraleContainerS">
                            <select id="currHotel" class="selectContainer" size="1">      
                                <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                <c:forEach items="${hotelList}" var="row">
                                    <option class="selectItem" value="${row.idHotel}">${row.name}</option>
                                </c:forEach>
                            </select> 
                        </div> 
                    </div>

                    <div class="tagColumnL">
                        <h1 class="labelHT">4. <fmt:message key="selectHotel" bundle="${ rb }" />:</h1>
                        <div class="centraleContainerB">
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
                <div class="tagColumnS">
                    <h1 class="labelHT"><fmt:message key="directName" bundle="${ rb }" />: </h1> 
                    <input type="text" class="inputLineContainerM" name="nameDirection" value="${currDirection.name}"/> 
                    <h1 class="labelHT"><fmt:message key="directPicture" bundle="${ rb }" />:</h1> 
                    <input type="text" class="inputLineContainerM" name="pictureDirection"   value="${currDirection.picture}"/>
                </div>
                <div class="tagColumnM">
                    <h1 class="labelHT"><fmt:message key="directText" bundle="${ rb }" />: </h1> 
                        <textarea name="textDirection" class="inputMultilineineContainerB">${currDirection.text}</textarea>
                </div>    
            </div>
            
            <input type="hidden" name="idDescription" value="${currDirection.description.idDescription}"/>
            <div class="parameterRow">
                <div class="labelColumnS">
                    <h1 class="labelH"><fmt:message key="directDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumnL">
                    <div class="innerColumn">
                    <textarea name="textDescription" class="inputMultilineineContainerD">${currDirection.description.text}</textarea>
                    </div>
                </div>
            </div>        
                   
            <div class="parameterRow">
                <div class="centrale">    
                    <input type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAll()"/>
                    <div id="erNote">${errorSaveData}</div>
                </div>
            </div>   
        
            
                
        </div>  
    </form>
    

</div>

