<%-- 
    Document   : editcity
    Created on : 27.09.2014, 21:59:23
    Author     : User
--%>

<div id="main">
    
    <form id="updCity" name="updateCity" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactCity" />
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectCity" bundle="${ rb }" />:</h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select id="currCountry" class="selectContainer" size="1">      
                            <option class="selectItem" value=""> - <fmt:message key="select" bundle="${ rb }" /> - </option>
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
            
            <input type="hidden" name="idCity" value="${currCity.idCity}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="cityName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text" name="nameCity" class="inputLineContainer" value="${currCity.name}"/>
                    </div>
                </div>
            </div>
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="cityPicture" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text"  name="pictureCity" class="inputLineContainer" value="${currCity.picture}"/>
                    </div>
                </div>      
            </div>
            
            <input type="hidden" name="idDescription" value="${currCity.description.idDescription}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="cityDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <textarea name="textDescription" class="inputMultilineineContainer">${currCity.description.text}</textarea>
                    </div>
                </div>
            </div>
            
            <br/>
        
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAllCity()"/>
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
            </div>
            
        </div>
        
    </form>

</div>

