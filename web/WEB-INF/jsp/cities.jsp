<%-- 
    Document   : cities
    Created on : 27.09.2014, 16:13:48
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    
    <div class="left inner column">
        <input type="checkbox" id="validCityStatus" name="status" value="1" class="boxMar" onchange="postCity('controller', 'goShowCity', 'POST')" >
            <fmt:message key="showValid" bundle="${ rb }" /><br>
        <input type="checkbox" id="invalidCityStatus" name="status" value="0" class="boxMar" onchange="postCity('controller', 'goShowCity', 'POST')" >
            <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        <select id="currCity" class="container" size="15" onchange="if(this.value)(post('controller', {selectId: this.value, command: 'showCity'}, 'POST'))">               
            <c:forEach items="${cityList}" var="row">
                <option class="menuHref" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
            </c:forEach>
        </select>
        
        <input class="large green awesome" type="submit" value="<fmt:message key="newCity" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewCity'}, 'POST')"/>
    </div>
    
    <div class="center inner column">
        <div id="erNote">${errorGetListMessage}</div>
        <ctg:ImgTag classImg="currimg" idImg="images" nameImg="${currCity.picture}"/>
        <div class="cueetext">
             ${currCity.description.text}
        </div>
        
        <input class="large orange awesome" type="submit" value="<fmt:message key="editCity" bundle="${ rb }" />" onclick="postCity('controller', 'goEditCity', 'POST')"/>
        <c:choose>
            <c:when test="${currCity.status == 1}">
                <input class="large red awesome" type="submit" value="<fmt:message key="deleteCity" bundle="${ rb }" />" onclick="postCity('controller', 'DeleteCity', 'POST')"/>
            </c:when>
            <c:when test="${currCity.status == 0}">
                <input class="large green awesome" type="submit" value="<fmt:message key="restoreCity" bundle="${ rb }" />" onclick="postCity('controller', 'RestoreCity', 'POST')"/>
            </c:when>
        </c:choose>   
    </div>
        
    <div class="rigth inner column">
        <input type="checkbox" id="validHotelStatus" name="status" value="1" class="boxMar" onchange="postCity('controller', 'goShowCity', 'POST')" >
            <fmt:message key="showValid" bundle="${ rb }" /><br>
        <input type="checkbox" id="invalidHotelStatus" name="status" value="0" class="boxMar" onchange="postCity('controller', 'goShowCity', 'POST')" >
            <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showHotel'}}, 'POST'))">               
            <c:forEach items="${currCity.hotelCollection}" var="row">
                <option class="menuHref" value="${row.idHotel}">${row.name}"</option>
            </c:forEach>
        </select>
    </div>
        
    <script>
        boxChecking('validCityStatus', ${validCityStatus});
        boxChecking('invalidCityStatus', ${invalidCityStatus});
        boxChecking('validHotelStatus', ${validHotelStatus});
        boxChecking('invalidHotelStatus', ${invalidHotelStatus});
    </script>
    <script type="text/javascript">
        select("currCity", ${currIdCity});
    </script>
    
    
    
</div>
