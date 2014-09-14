<%-- 
    Document   : error
    Created on : 14.09.2014, 11:19:00
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<html>
    <head>
        <title>Error Page</title>
    </head>
    <body>
        Request from ${pageContext.errorData.requestURI} is failed<br/>
        Servlet name or type: ${pageContext.errorData.servletName}<br/>
        Status code: ${pageContext.errorData.statusCode}<br/>
        Exception: ${pageContext.errorData.throwable}
    </body>
</html>
