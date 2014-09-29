<%-- 
    Document   : directions
    Created on : 29.09.2014, 20:47:32
    Author     : User
--%>

<div id="main">
    
    <c:forEach items="${directionList}" var="row">
        DirectionName: ${row.name}</br>
        
    </c:forEach>
        
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="goEditDirection" />
        <input type="submit" value="<fmt:message key="editDirection" bundle="${ rb }" />"/>
    </form>
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="goCreateNewDirection" />
        <input type="submit" value="<fmt:message key="newDirection" bundle="${ rb }" />"/>
    </form>

</div>
