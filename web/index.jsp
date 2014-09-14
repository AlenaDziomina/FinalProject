<%-- 
    Document   : index
    Created on : 08.09.2014, 22:23:07
    Author     : User
--%>

    <body>
        </br>
        </br>
        Locale: ${locale}</br>
        Lang: ${language}</br>
       
        <form action="controller" method="GET" name="indexForm">    
            <input type="hidden" name="command" value="session" />
            <input type="submit" value="GetSession">
        </form>
    </body>
