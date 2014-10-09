<%-- 
    Document   : registration
    Created on : 20.09.2014, 2:34:31
    Author     : User
--%>

<body>
    REFERER: ${referer}</br>
        Role: ${role}<br/>
        Balance: ${balance}</br>
        Language: ${language}</br>
    
    <form name="registrationForm" method="POST" action="controller">
        <input type="hidden" name="command" value="registration" />
        <br/>
        Login:
        <input type="text" name="login" value=""/><br/>
        Email:
        <input type="text" name="email" value=""/><br/>
        Phone number:
        <input type="text" name="phone" value=""/><br/>
        Password:
        <input type="password" name="password" value=""/><br/><br/>    
        
        <div id="erNote">${errorLoginPassMessage}</div>    
        <div id="erNote">${wrongAction}</div>
        <div id="erNote">${nullPage}</div>
        <input class="large blue awesome" type="submit" value="Registrate"/>
    </form>
        
</body>
