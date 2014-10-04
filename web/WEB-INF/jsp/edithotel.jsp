<%-- 
    Document   : edithotel
    Created on : 27.09.2014, 22:04:21
    Author     : User
--%>

<div id="main">
    
    <form id="updHotel" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactHotel" />
            
            
            <input type="hidden" name="idCountry" value="${currCountry.idCountry}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectCountry" bundle="${ rb }" />: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select class="selectContainer" size="1" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showCitiesOfCountry'}, 'POST'))">      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${countryList}" var="row">
                                <c:choose>
                                    <c:when test="${row.idCountry == currCountry.idCountry}">
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
            
            <input type="hidden" name="idCity" value="${currHotel.city.idCity}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectCity" bundle="${ rb }" />: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select name="citySelection" class="selectContainer" size="1" onclick="if(this.value)(selectCity(this.value))">      
                            <option class="selectItem" value=""> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${currCityList}" var="row">
                                <c:choose>
                                    <c:when test="${row.idCity == currHotel.city.idCity}">
                                        <option class="selectItem" value="${row.idCity}" selected="true"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option class="selectItem" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="idHotel" value="${currHotel.idHotel}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="hotelName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="nameHotel" value="${currHotel.name}"/>
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="starsHotel" value="${currHotel.stars}"/>        
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectHotelStars" bundle="${ rb }" />: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select name="citySelection" class="selectContainer" size="1" onclick="if(this.value)(selectStars(this.value))">      
                            <option class="selectItem" value=""> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach var="i" begin="1" end="5">
                                <c:choose>
                                    <c:when test="${i == currHotel.stars}">
                                        <option class="selectItem" value="${i}" selected="true">${i}*</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option class="selectItem" value="${i}">${i}*</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>        
            
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="hotelPicture" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="pictureHotel"   value="${currHotel.picture}"/>
                    </div>
                </div>      
            </div>
            
            <input type="hidden" name="idDescription" value="${currHotel.description.idDescription}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="hotelDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <textarea name="textDescription" class="inputMultilineineContainer">${currHotel.description.text}</textarea>
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

