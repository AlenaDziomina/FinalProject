<%-- 
    Document   : directions
    Created on : 29.09.2014, 20:47:32
    Author     : User
--%>

<div id="main">
    
    <c:forEach items="${directionList}" var="row">
        <div class="parameterRowB">
            <img class="smallimg" id="images" src="<%=request.getContextPath()%>${row.picture}">
            <a href="controller?command=showDirection&selectId=${row.idDirection}">${row.name}</a>
            DirectionCountryTags: </br>
            <c:forEach items="${row.countryCollection}" var="cntr">
                ${cntr.name} ${cntr.idCountry} </br>
            </c:forEach>
            DirectionCityTags: </br>
            <c:forEach items="${row.cityCollection}" var="ct">
                ${ct.name} ${ct.idCity} </br>
            </c:forEach>
            DirectionStayHotels: </br>
            <c:forEach items="${row.stayCollection}" var="st">
                ${st.stayHotel.name}${st.stayHotel.idHotel} </br>
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
