<%-- 
    Document   : sessionprop
    Created on : 08.09.2014, 18:32:15
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <fmt:message key="welcome" bundle="${ rb }" /><br/>
        
        Locale: ${locale}<br/>
        Lang: ${language}</br>
        USERTYPE: ${userType}<br/>
        Кодировка запроса: ${ pageContext.request.characterEncoding }<br/>
        Role: ${role}<br/>
        Counter: ${counter}<br/>
        MaxInactiveInterval: ${pageContext.session.maxInactiveInterval}<br/>
        ID session: ${pageContext.session.id}<br/>
        
        Lifecycle: ${lifecycle}<br/>
        <a href="index.jsp">Back to index.jsp</a>
    </body>
</html>
