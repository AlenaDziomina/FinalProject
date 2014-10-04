<%-- 
    Document   : errorDao
    Created on : 19.09.2014, 23:50:20
    Author     : User
--%>


    <h>Error Page DAO</h>
        Request from ${pageContext.errorData.requestURI} is failed<br/>
        Servlet name or type: ${pageContext.errorData.servletName}<br/>
        Status code: ${pageContext.errorData.statusCode}<br/>
        Exception: ${pageContext.errorData.throwable}
    </body>

