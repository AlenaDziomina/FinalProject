<%-- 
    Document   : index
    Created on : 08.09.2014, 22:23:07
    Author     : User
--%>

    <body>
        REFERER: ${referer}</br>
        Role: ${role}<br/>
        Balance: ${balance}</br>
        Language: ${language}</br>
       
        <form action="controller" method="GET" name="indexForm">    
            <input type="hidden" name="command" value="getsession" />
            <input type="submit" value="GetSession">
        </form>
    </body>
