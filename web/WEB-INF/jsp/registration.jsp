<%-- 
    Document   : registration
    Created on : 20.09.2014, 2:34:31
    Author     : User
--%>

<div id="main">
    <form id="registration" name="registrationForm" method="POST" action="controller" onsubmit="return validateForm('registration');">
        <div class="inner">
            <input type="hidden" name="command" value="registration" />
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="login" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="login" class="inputLineContainer" value="${currUser.login}"/>
                    <div id="erNote"><a id="loginErrMsg" hidden="true"><fmt:message key="message.errorLogin" bundle="${ rb }" /></a></div>
                </div>
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="email" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="email" class="inputLineContainer" value="${currUser.email}"/>
                    <div id="erNote"><a id="emailErrMsg" hidden="true"><fmt:message key="message.errorEmail" bundle="${ rb }" /></a></div>
                </div>
            </div>
                
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="phoneNumber" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="phone" class="inputLineContainer" value="${currUser.phone}"/>
                    <div id="erNote"><a id="phoneErrMsg" hidden="true"><fmt:message key="message.errorPhone" bundle="${ rb }" /></a></div>
                </div>
            </div>
    
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="password" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="password" class="inputLineContainer" value=""/>
                    <div id="erNote"><a id="passwordErrMsg" hidden="true"><fmt:message key="message.errorPassword" bundle="${ rb }" /></a></div>
                </div>
            </div>
                
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="repeatPassword" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="repeatPassword" class="inputLineContainer" value=""/>
                    <div id="erNote"><a id="repeatPassErrMsg" hidden="true"><fmt:message key="message.errorRepeatPass" bundle="${ rb }" /></a></div>
                </div>
            </div>
                
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="registrate" bundle="${ rb }" />" onclick="saveAllUser()"/>
                <ctg:ErrorMsgTag classErr="erNote" msg="${errorSave}"><fmt:message key="${errorSave}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                <ctg:ErrorMsgTag classErr="erNote" msg="${errorSaveReason}"><fmt:message key="${errorSaveReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                <ctg:ErrorMsgTag classErr="erNote" msg="${errorLoginPassMessage}"><fmt:message key="${errorLoginPassMessage}" bundle="${ rb }" /></ctg:ErrorMsgTag>
            </div>
        </div>
    </form>
</div>
