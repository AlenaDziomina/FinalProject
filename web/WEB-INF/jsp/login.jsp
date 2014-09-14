<%-- 
    Document   : login
    Created on : 13.09.2014, 13:44:31
    Author     : User
--%>

<body>
    </br>
    </br>
    Locale: ${locale}<br/>
    Lang: ${language}</br>
    
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login" />
        Login:<br/>
        <input type="text" name="login" value=""/><br/>
        Password:<br/>
        <input type="password" name="password" value=""/><br/>      
        ${errorLoginPassMessage}<br/>    
        ${wrongAction}<br/>
        ${nullPage}<br/>
        <input type="submit" value="Log in"/>
    </form>
        
</body>
