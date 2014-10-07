<%-- 
    Document   : direction.jsp
    Created on : 01.10.2014, 21:15:02
    Author     : User
--%>

<div id="main">
    
    <div class="innerColumn">
        <div class="parameterRowD">
            <img class="smallimg" id="images" src="<%=request.getContextPath()%>${currDirection.picture}">
            <div class="innerColumnD">
                <a class="labelHTD" href="controller?command=showDirection&selectId=${currDirection.idDirection}">${currDirection.name}</a>
            </div>
            <h2 class="labelHD">${currDirection.text}</h2>
            <div class="lblH">
                tourType: ${currDirection.tourType.nameTourType}    transMode: ${currDirection.transMode.nameMode} 
            </div>
            <div>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCountryTags:</div>
                    <c:forEach items="${currDirection.countryCollection}" var="cntr">
                        <li>${cntr.name}</li>
                    </c:forEach>
                </ul>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCityTags:</div>
                    <c:forEach items="${currDirection.cityCollection}" var="ct">
                        <li>${ct.name}</li>
                    </c:forEach>
                </ul>
                <ul class="containerLabelR">
                    <div class="lblH">DirectionStayHotels:</div>
                    <c:forEach items="${currDirection.stayCollection}" var="st">
                        <li>${st.hotel.name} ${st.hotel.stars}* (${st.hotel.city.name}) </li>
                    </c:forEach>
                </ul>
            </div>      
        </div>
    
        
    <div class="parameterRowDR">
        
            <select class="container" size="15" onclick="">               
                <c:forEach items="${currDirection.tourCollection}" var="row">
                    <option class="menuHref" value="${row.idTour}">${row.departDate}"</option>
                </c:forEach>
            </select>
        
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goEditDirection" />
            <input type="submit" value="<fmt:message key="editDirection" bundle="${ rb }" />"/>
        </form>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goCreateNewDirection" />
            <input type="submit" value="<fmt:message key="newDirection" bundle="${ rb }" />"/>
        </form>
    </div>
</div>
</div>
