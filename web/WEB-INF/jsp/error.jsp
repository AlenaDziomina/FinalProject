<%-- 
    Document   : error
    Created on : 14.09.2014, 11:19:00
    Author     : User
--%>

    
    <h>Error Page</h>
        Request from ${pageContext.errorData.requestURI} is failed<br/>
        Servlet name or type: ${pageContext.errorData.servletName}<br/>
        Status code: ${pageContext.errorData.statusCode}<br/>
        Exception: ${pageContext.errorData.throwable}
    

