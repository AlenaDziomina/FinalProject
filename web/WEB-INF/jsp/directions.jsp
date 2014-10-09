<%-- 
    Document   : directions
    Created on : 29.09.2014, 20:47:32
    Author     : User
--%>

<div id="main">
    
    <c:forEach items="${directionList}" var="row">
        <div class="parameterRowB">
            <img class="smallimg" id="images" src="<%=request.getContextPath()%>${row.picture}">
            <div class="innerColumnD">
                <a class="labelHTD" href="controller?command=showDirection&selectId=${row.idDirection}">${row.name}</a>
            </div>
            <h2 class="labelHD">${row.text}</h2>
            
            <div>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCountryTags:</div>
                    <c:forEach items="${row.countryCollection}" var="cntr">
                        <li>${cntr.name}</li>
                    </c:forEach>
                </ul>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCityTags:</div>
                    <c:forEach items="${row.cityCollection}" var="ct">
                        <li>${ct.name}</li>
                    </c:forEach>
                </ul>
                <ul class="containerLabelR">
                    <div class="lblH">DirectionStayHotels:</div>
                    <c:forEach items="${row.stayCollection}" var="st">
                        <li>${st.hotel.name} ${st.hotel.stars}* (${st.hotel.city.name}) </li>
                    </c:forEach>
                </ul>
            </div>                     
        </div>
    </c:forEach>
        
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="goCreateNewDirection" />
        <input class="centrale large green awesome" type="submit" value="<fmt:message key="newDirection" bundle="${ rb }" />"/>
    </form>

</div>
