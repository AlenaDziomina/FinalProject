<%-- 
    Document   : errorDao
    Created on : 19.09.2014, 23:50:20
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<html>
    <head>
        <title>Error Page DAO</title>
    </head>
    <body>
        Request from ${pageContext.errorData.requestURI} is failed<br/>
        Servlet name or type: ${pageContext.errorData.servletName}<br/>
        Status code: ${pageContext.errorData.statusCode}<br/>
        Exception: ${pageContext.errorData.throwable}
    </body>
</html>
