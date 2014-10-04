<%-- 
    Document   : cities
    Created on : 27.09.2014, 16:13:48
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    
    <div class="leftColumn">
        <div class="innerColumn">
            <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showCity'}, 'POST'))">               
                <c:forEach items="${cityList}" var="row">
                    <option class="menuHref" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                </c:forEach>
            </select>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goCreateNewCity" />
                <input type="submit" value="<fmt:message key="newCity" bundle="${ rb }" />"/>
            </form>
            
       </div>
    </div>
    
    <div class="centerColumn">
        <div class="innerColumn">
            <div id="erNote">${errorGetListMessage}</div>
            <c:if test="${currCity.picture != null}">
                <img class="currimg" id="images" src="<%=request.getContextPath()%>${currCity.picture}">
            </c:if>
            
            <div class="cueetext">
                 ${currCity.description.text}
            </div>

            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goEditCity" />
                <input type="submit" value="<fmt:message key="editCity" bundle="${ rb }" />"/>
            </form>
        </div>
    </div>
        
    <div class="rigthColumn">
        <div class="innerColumn">
            <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showHotel'}, 'POST'))">               
                <c:forEach items="${currCity.hotelCollection}" var="row">
                    <option class="menuHref" value="${row.idHotel}">${row.name}"</option>
                </c:forEach>
            </select>
       </div>
    </div>
        
    
    
    
    
</div>
