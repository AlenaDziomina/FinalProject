<%-- 
    Document   : login
    Created on : 13.09.2014, 13:44:31
    Author     : User
--%>

<body>
    REFERER: ${referer}</br>
        Role: ${role}<br/>
        Balance: ${balance}</br>
        Language: ${language}</br>
    
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login" />
        Login:<br/>
        <input type="text" name="login" value=""/><br/>
        Password:<br/>
        <input type="password" name="password" value=""/><br/>      
        <div id="erNote">${errorLoginPassMessage}</div>   
        <div id="erNote">${wrongAction}</div>
        <div id="erNote">${nullPage}</div>
        <input type="submit" value="Log in"/>
    </form>
        
</body>
