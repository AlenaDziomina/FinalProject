<%-- 
    Document   : registration
    Created on : 20.09.2014, 2:34:31
    Author     : User
--%>

<div id="main">
    <form id="registration" name="registrationForm" method="POST" action="controller" onsubmit="return validateForm();">
        <div class="inner">
            <input type="hidden" name="command" value="registration" />
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="login" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="login" class="inputLineContainer" value="${currUser.login}"/>
                    <div id="erNote"><a id="loginErrMsg" hidden="true"><fmt:message key="errorLogin" bundle="${ rb }" /></a></div>
                </div>
            </div>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="email" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="email" class="inputLineContainer" value="${currUser.email}"/>
                    <div id="erNote"><a id="emailErrMsg" hidden="true"><fmt:message key="errorEmail" bundle="${ rb }" /></a></div>
                </div>
            </div>
                
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="phoneNumber" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="phone" class="inputLineContainer" value="${currUser.phone}"/>
                    <div id="erNote"><a id="phoneErrMsg" hidden="true"><fmt:message key="errorPhone" bundle="${ rb }" /></a></div>
                </div>
            </div>
    
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="password" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="password" class="inputLineContainer" value=""/>
                    <div id="erNote"><a id="passwordErrMsg" hidden="true"><fmt:message key="errorPassword" bundle="${ rb }" /></a></div>
                </div>
            </div>
                
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="repeatPassword" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="repeatPassword" class="inputLineContainer" value=""/>
                    <div id="erNote"><a id="repeatPassErrMsg" hidden="true"><fmt:message key="errorRepeatPass" bundle="${ rb }" /></a></div>
                </div>
            </div>
                
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="registrate" bundle="${ rb }" />" onclick="saveAllUser()"/>
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
                <div id="erNote">${errorLoginPassMessage}</div>    
                <div id="erNote">${wrongAction}</div>
                <div id="erNote">${nullPage}</div>
            </div>
        </div>
    </form>
</div>
