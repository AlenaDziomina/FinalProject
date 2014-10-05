<%-- 
    Document   : edithotel
    Created on : 27.09.2014, 22:04:21
    Author     : User
--%>

<div id="main">
    
    <form id="updHotel" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactHotel" />
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectCountry" bundle="${ rb }" />: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select id="currCountry" class="selectContainer" size="1" onchange="postHot('controller', 'ifCountrySelected', 'POST')">      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${countryList}" var="row">
                                <option class="selectItem" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
                            
            <script type="text/javascript">
                select("currCountry", ${currIdCountry});
            </script>
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectCity" bundle="${ rb }" />: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select id="currCity" name="citySelection" class="selectContainer" size="1">      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach items="${currCityList}" var="row">
                                <option class="selectItem" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
                            
            <script type="text/javascript">
                select("currCity", ${currIdCity});
            </script>
            
            <input type="hidden" name="idHotel" value="${currHotel.idHotel}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="hotelName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text" name="nameHotel" class="inputLineContainer"  value="${currHotel.name}"/>
                    </div>
                </div>
            </div>
            
                    
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectHotelStars" bundle="${ rb }" />: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select id="currStars" name="starsSelection" class="selectContainer" size="1" >      
                            <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                            <c:forEach var="i" begin="1" end="5">
                                <option class="selectItem" value="${i}">${i}*</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>   
                            
            <script type="text/javascript">
                select("currStars", ${currHotel.stars});
            </script>
            
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="hotelPicture" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text" name="pictureHotel" class="inputLineContainer" value="${currHotel.picture}"/>
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
                    <input type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAllHotel()" />
                    <div id="erNote">${errorSaveData}</div>
                    <div id="erNote">${errorReason}</div>
                    <div id="erAdminNote">${errorAdminMsg}</div>
                </div>
            </div>
            
        </div>
        
    </form>

</div>

