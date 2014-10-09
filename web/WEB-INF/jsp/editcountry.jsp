<%-- 
    Document   : newcountry
    Created on : 25.09.2014, 15:29:29
    Author     : User
--%>

<div id="main">
    
    <form id="updCountry" name="updateCountry" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactCountry" />
            
            <input type="hidden" name="idCountry" value="${currCountry.idCountry}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="countryName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="inputColumn innerColumn">
                    <input type="text" name="nameCountry" class="inputLineContainer" value="${currCountry.name}"/>
                </div>
            </div>
            
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="countryPicture" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn innerColumn">
                    <input type="text" name="pictureCountry" class="inputLineContainer" value="${currCountry.picture}"/>
                </div>      
            </div>
            
            <input type="hidden" name="idDescription" value="${currCountry.description.idDescription}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="countryDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="inputColumn innerColumn">
                    <textarea name="textDescription" class="inputMultilineineContainer">${currCountry.description.text}</textarea>
                </div>
            </div>
            
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAllCountry()"/>
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
            </div>
        </div>
    </form>
</div>
