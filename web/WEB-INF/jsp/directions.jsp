<%-- 
    Document   : directions
    Created on : 29.09.2014, 20:47:32
    Author     : User
--%>

<div id="main">
    
    <c:forEach items="${directionList}" var="row">
        <div class="parameterRowB">
            <img class="smallimg" id="images" src="<%=request.getContextPath()%>${row.picture}">
            <a href="controller?command=showDirection&id_direction=${row.idDirection}">${row.name}</a>
            DirectionCountryTags: 
            <c:forEach items="${row.countryCollection}" var="cntr">
                ${cntr.name} ${cntr.idCountry} </br>
            </c:forEach>
                
                
                
                       
        </div>
        
        
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
