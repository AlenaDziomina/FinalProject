<%-- 
    Document   : hotels
    Created on : 27.09.2014, 16:19:30
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    <div class="leftColumn innerColumn">
        <select id="currHotel" class="container" size="15" onchange="if(this.value)(post('controller', {selectId: this.value, command: 'showHotel'}, 'POST'))">               
            <c:forEach items="${hotelList}" var="row">
                <option class="menuHref" value="${row.idHotel}">${row.name}</option>
            </c:forEach>
        </select>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goCreateNewHotel" />
            <input class="large green awesome" type="submit" value="<fmt:message key="newHotel" bundle="${ rb }" />"/>
        </form>
    </div>
        
    <script type="text/javascript">
        select("currHotel", ${currIdHotel});
    </script>      
    
    <div class="centerColumn innerColumn">
        <div id="erNote">${errorGetListMessage}</div>
        <c:if test="${currHotel.picture != null}">
            <img class="currimg" id="images" src="<%=request.getContextPath()%>${currHotel.picture}">
        </c:if>
        <div class="cueetext">
             ${currHotel.description.text}
        </div>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goEditHotel" />
            <input class="centrale large orange awesome" type="submit" value="<fmt:message key="editHotel" bundle="${ rb }" />"/>
        </form>
    </div>
</div>
