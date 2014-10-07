<%-- 
    Document   : direction.jsp
    Created on : 01.10.2014, 21:15:02
    Author     : User
--%>

<div id="main">
    
    
        <div class="parameterRowB">
            <img class="smallimg" id="images" src="<%=request.getContextPath()%>${currDirection.picture}">
            DirectionID: </br>
            ${currDirection.idDirection}
            Direction Name:
            ${currDirection.name} </br>
            Direction text:
            ${currDirection.text}
            DirectionCountryTags: </br>
            <c:forEach items="${currDirection.countryCollection}" var="cntr">
                ${cntr.name} ${cntr.idCountry} </br>
            </c:forEach>
            DirectionCityTags: </br>
            <c:forEach items="${currDirection.cityCollection}" var="ct">
                ${ct.name} ${ct.idCity} </br>
            </c:forEach>
            DirectionStayHotels: </br>
            <c:forEach items="${currDirection.stayCollection}" var="st">
                ${st.hotel.name}${st.hotel.idHotel} </br>
            </c:forEach>
            Description:
            ${currDirection.description.text}
            
                
                
                
                       
        </div>
        
        
   
        
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="goEditDirection" />
        <input type="submit" value="<fmt:message key="editDirection" bundle="${ rb }" />"/>
    </form>
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="goCreateNewDirection" />
        <input type="submit" value="<fmt:message key="newDirection" bundle="${ rb }" />"/>
    </form>

</div>
