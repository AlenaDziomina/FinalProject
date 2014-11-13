<%-- 
    Document   : login
    Created on : 13.09.2014, 13:44:31
    Author     : User
--%>

<div id="main">
    <form name="loginForm" method="POST" action="controller">
        <div class="inner">
            <input type="hidden" name="command" value="login" />
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="login" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="login" class="inputLineContainer" value="${currUser.login}"/>
                </div>
            </div>
                
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="password" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="password" class="inputLineContainer" value=""/>
                </div>
            </div>
    
            <div class="parameterRow centrale">
                <input class="large blue awesome" type="submit" value="<fmt:message key="log_in" bundle="${ rb }" />" onclick="saveLogin()"/>
                <ctg:ErrorMsgTag classErr="erNote" msg="${errorLoginPassMessage}"><fmt:message key="${errorLoginPassMessage}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                <div id="erNote">${wrongAction}</div>
            </div>
        </div>
    </form>
</div>
