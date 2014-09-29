<%-- 
    Document   : hotels
    Created on : 27.09.2014, 16:19:30
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    
    <div class="leftColumn">
        <div class="innerColumn">
            <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showHotel'}, 'POST'))">               
                <c:forEach items="${hotelList}" var="row">
                    <option class="menuHref" value="${row.idHotel}">${row.name}</option>
                </c:forEach>
            </select>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goCreateNewHotel" />
                <input type="submit" value="<fmt:message key="newHotel" bundle="${ rb }" />"/>
            </form>
            
       </div>
    </div>
    
    <div class="centerColumn">
        <div class="innerColumn">
            <div id="erNote">${errorGetListMessage}</div>
            <img class="currimg" id="images" src="<%=request.getContextPath()%>${currHotel.picture}">
            <div class="cueetext">
                 ${currHotel.description.text}
            </div>

            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goEditHotel" />
                <input type="submit" value="<fmt:message key="editHotel" bundle="${ rb }" />"/>
            </form>
        </div>
    </div>
        
          
    
    
    
    
</div>
