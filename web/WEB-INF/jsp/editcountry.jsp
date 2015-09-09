<%-- 
    Document   : newcountry
    Created on : 25.09.2014, 15:29:29
    Author     : Helena.Grouk
--%>

<div id="main">
    
    <form id="updCountry" name="updateCountry" method="POST" action="controller" onsubmit="return validateCountryForm();">
        <div class="inner">
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="countryName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="nameCountry" name="nameCountry" class="inputLineContainer" value="${currCountry.name}"/>
                    <div id="erNote"><a id="nameErrMsg" hidden="true"><fmt:message key="message.errorName" bundle="${ rb }" /></a></div>
                </div>
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="countryPicture" bundle="${ rb }" />:</h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="pictureCountry" name="pictureCountry" class="inputLineContainer" value="${currCountry.picture}"/>
                    <div id="erNote"><a id="pictureErrMsg" hidden="true"><fmt:message key="message.errorPicture" bundle="${ rb }" /></a></div>
                </div>      
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="countryDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="input inner">
                    <textarea name="textDescription" class="inputMultilineineContainer">${currCountry.description.text}</textarea>
                </div>
            </div>
            
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAllCountry('saveRedactCountry')"/>
                <ctg:ErrorMsgTag classErr="erNote" msg="${errorSave}"><fmt:message key="${errorSave}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                <ctg:ErrorMsgTag classErr="erNote" msg="${errorSaveReason}"><fmt:message key="${errorSaveReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
            </div>
        </div>
    </form>
</div>
