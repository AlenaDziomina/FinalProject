<%-- 
    Document   : editcity
    Created on : 27.09.2014, 21:59:23
    Author     : User
--%>

<div id="main">
    
    <form id="updCity" name="updateCity" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactCity" />
            
            <input type="hidden" name="id_country" value="${currCity.country.idCountry}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectCity" bundle="${ rb }" />:</h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select class="selectContainer" size="1" onclick="if(this.value)(selectCountry(this.value))">      
                            <option class="selectItem" value=""> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${countryList}" var="row">
                                <c:choose>
                                    <c:when test="${row.idCountry == currCity.country.idCountry}">
                                        <option class="selectItem" value="${row.idCountry}" selected="true"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option class="selectItem" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="id_city" value="${currCity.idCity}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="cityName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="name_city" value="${currCity.name}"/>
                    </div>
                </div>
            </div>
            
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="cityPicture" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="picture_city"   value="${currCity.picture}"/>
                    </div>
                </div>      
            </div>
            
            <input type="hidden" name="id_description" value="${currCity.description.idDescription}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="cityDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <textarea name="text_description" class="inputMultilineineContainer">${currCity.description.text}</textarea>
                    </div>
                </div>
            </div>
            
            <br/>
        
            <div class="parameterRow">
                <div class="centrale">
                    <input type="submit" value="<fmt:message key="save" bundle="${ rb }" />"/>
                    <div id="erNote">${errorSaveData}</div>
                </div>
            </div>
            
        </div>
        
    </form>

</div>

