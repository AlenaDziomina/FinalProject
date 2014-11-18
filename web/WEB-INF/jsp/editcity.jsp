<%-- 
    Document   : editcity
    Created on : 27.09.2014, 21:59:23
    Author     : Helena.Grouk
--%>

<div id="main">
    <form id="updCity" name="updateCity" method="POST" action="controller" onsubmit="return validateCityForm();">
        <div class="inner">
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="selectCity" bundle="${ rb }" />:</h1>
                </div>
                <div class="input inner">
                    <select id="currCountry" class="selectContainer" size="1">      
                        <option class="selectItem" value=""> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach items="${countryList}" var="row">
                            <option class="selectItem" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                        </c:forEach>
                    </select>
                    <div id="erNote"><a id="selectCountryErrMsg" hidden="true"><fmt:message key="message.errorSelectCountry" bundle="${ rb }" /></a></div>    
                </div>
            </div>
                            
            <script type="text/javascript">
                select("currCountry", ${currIdCountry});
            </script>                
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="cityName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="nameCity" name="nameCity" class="inputLineContainer" value="${currCity.name}"/>
                    <div id="erNote"><a id="nameErrMsg" hidden="true"><fmt:message key="message.errorName" bundle="${ rb }" /></a></div>
                </div>
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="cityPicture" bundle="${ rb }" />:</h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="pictureCity" name="pictureCity" class="inputLineContainer" value="${currCity.picture}"/>
                    <div id="erNote"><a id="pictureErrMsg" hidden="true"><fmt:message key="message.errorPicture" bundle="${ rb }" /></a></div>
                </div>      
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="cityDescription" bundle="${ rb }" />:</h1> 
                </div>
                <div class="input inner">
                    <textarea name="textDescription" class="inputMultilineineContainer">${currCity.description.text}</textarea>
                </div>
            </div>
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAllCity('saveRedactCity')"/>
                <ctg:ErrorMsgTag classErr="erNote" msg="${errorSave}"><fmt:message key="${errorSave}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                <ctg:ErrorMsgTag classErr="erNote" msg="${errorSaveReason}"><fmt:message key="${errorSaveReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
            </div>
        </div>
    </form>
</div>

