<%-- 
    Document   : hotels
    Created on : 27.09.2014, 16:19:30
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    <div class="left inner column">
        <input type="checkbox" id="validHotelStatus" name="status" value="1" class="boxMar" onchange="postHotel('controller', 'goShowHotel', 'POST')" />
            <fmt:message key="showValid" bundle="${ rb }" /><br>
        <input type="checkbox" id="invalidHotelStatus" name="status" value="0" class="boxMar" onchange="postHotel('controller', 'goShowHotel', 'POST')" />
            <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        <select id="currHotel" class="container" size="15" 
                onchange="if(this.value)(postHotel('controller', 'showHotel', 'POST'))">               
            <c:forEach items="${hotelList}" var="row">
                <option class="menuHref" value="${row.idHotel}">${row.name}</option>
            </c:forEach>
        </select>
        <input class="large green awesome" type="submit" value="<fmt:message key="newHotel" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewHotel'}, 'POST')"/>
    </div>
    
    <div class="full center inner column">
        <div id="erNote">${errorGetListMessage}</div>
        <ctg:ImgTag classImg="currimg" idImg="images" nameImg="${currHotel.picture}"/>
        <div class="cueetext">${currHotel.description.text}</div>
        
        <c:choose>
            <c:when test="${currHotel.status == 1}">
                <input class="large orange awesome" type="submit" value="<fmt:message key="editHotel" bundle="${ rb }" />" onclick="postHotel('controller', 'goEditHotel', 'POST')"/>
                <input class="large red awesome" type="submit" value="<fmt:message key="deleteHotel" bundle="${ rb }" />" onclick="postHotel('controller', 'DeleteHotel', 'POST')"/>
            </c:when>
            <c:when test="${currHotel.status == 0}">
                <input class="large green awesome" type="submit" value="<fmt:message key="restoreHotel" bundle="${ rb }" />" onclick="postHotel('controller', 'RestoreHotel', 'POST')"/>
            </c:when>
        </c:choose>   
    </div>
    <script>
        boxChecking('validHotelStatus', ${validHotelStatus});
        boxChecking('invalidHotelStatus', ${invalidHotelStatus});
    </script>
    <script>
        select("currHotel", ${currIdHotel});
    </script>  
</div>
