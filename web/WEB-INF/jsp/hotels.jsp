<%-- 
    Document   : hotels
    Created on : 27.09.2014, 16:19:30
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    <div class="left inner column">
        <ctg:RoleTag>
            <input type="checkbox" id="validHotelStatus" name="status" value="1" class="boxMar" onchange="postHotel('controller', 'goShowHotel', 'POST')" />
                <fmt:message key="showValid" bundle="${ rb }" /><br>
            <input type="checkbox" id="invalidHotelStatus" name="status" value="0" class="boxMar" onchange="postHotel('controller', 'goShowHotel', 'POST')" />
                <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        </ctg:RoleTag>
        
        <select id="currHotel" class="container" size="15" 
                onchange="if(this.value)(postHotel('controller', 'showHotel', 'POST'))">               
            <c:forEach items="${hotelList}" var="row">
                <ctg:OptionTag status="${row.status}" valClass="menuHref" invalClass="grey menuHref" value="${row.idHotel}">
                    ${row.name}
                </ctg:OptionTag>
            </c:forEach>
        </select>
        <ctg:RoleTag>
            <input class="large green awesome" type="submit" value="<fmt:message key="newHotel" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewHotel'}, 'POST')"/>
        </ctg:RoleTag>
    </div>
    
    <script>
        select("currHotel", ${currIdHotel});
    </script>  
    
    <div class="full center inner column">
        <div id="erNote">${errorGetListMessage}</div>
        <ctg:ImgTag classImg="currimg" idImg="images" nameImg="${currHotel.picture}"/>
        <div class="cueetext">${currHotel.description.text}</div>
        
        <ctg:RoleTag>
            <c:choose>
                <c:when test="${currHotel.status == 1}">
                    <input class="large orange awesome" type="submit" value="<fmt:message key="editHotel" bundle="${ rb }" />" onclick="postHotel('controller', 'goEditHotel', 'POST')"/>
                    <input class="large red awesome" type="submit" value="<fmt:message key="deleteHotel" bundle="${ rb }" />" onclick="postHotel('controller', 'DeleteHotel', 'POST')"/>
                    <div id="erNote"><fmt:message key="${errorDelete}" bundle="${ rb }" /></div>
                    <div id="erNote"><fmt:message key="${errorDeleteReason}" bundle="${ rb }" /></div>
                </c:when>
                <c:when test="${currHotel.status == 0}">
                    <input class="large green awesome" type="submit" value="<fmt:message key="restoreHotel" bundle="${ rb }" />" onclick="postHotel('controller', 'RestoreHotel', 'POST')"/>
                    <div id="erNote"><fmt:message key="${errorRestore}" bundle="${ rb }" /></div>
                    <div id="erNote"><fmt:message key="${errorRestoreReason}" bundle="${ rb }" /></div>
                </c:when>
            </c:choose> 
        </ctg:RoleTag>
    </div>
    <script>
        boxChecking('validHotelStatus', ${validHotelStatus});
        boxChecking('invalidHotelStatus', ${invalidHotelStatus});
    </script>
   
</div>
