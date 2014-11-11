<%-- 
    Document   : edituser
    Created on : 11.11.2014, 21:44:15
    Author     : User
--%>

<div id="main">
    <form name="updUser" id="editUser" method="POST" action="controller" onsubmit="return validateForm('editUser');">
        <div class="inner">
            <input type="hidden" name="command" value="saveRedactUser" />
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="login" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input readonly="true" type="text" name="login" class="inputLineContainer" value="${currUser.login}"/>
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
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />"/>
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
