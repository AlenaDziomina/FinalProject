<%-- 
    Document   : main
    Created on : 14.09.2014, 11:00:40
    Author     : User
--%>

<body>
    REFERER: ${referer}</br>
        Role: ${role}<br/>
        Balance: ${balance}</br>
        Language: ${language}</br>
    
    <h3>Welcome</h3>
    <hr/>${user.login}, hello!<hr/>
    <a href="controller?command=logout">Logout</a>
    
    <form action="controller" method="GET">
        <input type="hidden" name="command" value="localization" />
        <input type="submit" value="EN" name="leng" />
        <input type="submit" value="RU" name="leng" />
    </form>

    <form action="controller" method="GET">
        <input type="hidden" name="command" value="gologin" />
        <input type="submit" value="Login"/>
    </form>
    <br/>
    
</body>
