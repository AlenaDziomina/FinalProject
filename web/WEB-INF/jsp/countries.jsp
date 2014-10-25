<%-- 
    Document   : countries
    Created on : 25.09.2014, 0:41:29
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    
    <div class="left inner column">
        <input type="checkbox" id="validCountryStatus" name="status" value="1" class="boxMar" onchange="postCountry('controller', 'goShowCountry', 'POST')" />
            <fmt:message key="showValid" bundle="${ rb }" /><br>
        <input type="checkbox" id="invalidCountryStatus" name="status" value="0" class="boxMar" onchange="postCountry('controller', 'goShowCountry', 'POST')" />
            <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        <select id="currCountry" class="container" size="15" 
                onclick="if(this.value)(postCountry('controller', 'ShowCountry', 'POST'))">               
            <c:forEach items="${countryList}" var="row">
                <option class="menuHref" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
            </c:forEach>
        </select>
        
        <input class="large green awesome" type="submit" value="<fmt:message key="newCountry" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewCountry'}, 'POST')"/>
    </div>
    
    <div class="center inner column">
        <div id="erNote">${errorGetListMessage}</div>
        <ctg:ImgTag classImg="currimg" idImg="images" nameImg="${currCountry.picture}"/>
        <div class="cueetext">
            ${currCountry.description.text}
        </div>
 
        
        <c:choose>
            <c:when test="${currCountry.status == 1}">
                <input class="large orange awesome" type="submit" value="<fmt:message key="editCountry" bundle="${ rb }" />" onclick="postCountry('controller', 'goEditCountry', 'POST')"/>
                <input class="large red awesome" type="submit" value="<fmt:message key="deleteCountry" bundle="${ rb }" />" onclick="postCountry('controller', 'DeleteCountry', 'POST')"/>
            </c:when>
            <c:when test="${currCountry.status == 0}">
                <input class="large green awesome" type="submit" value="<fmt:message key="restoreCountry" bundle="${ rb }" />" onclick="postCountry('controller', 'RestoreCountry', 'POST')"/>
            </c:when>
        </c:choose>        
    </div>
        
    <div class="rigth inner column">
        <input type="checkbox" id="validCityStatus" name="status" value="1" class="boxMar" onchange="postCountry('controller', 'goShowCountry', 'POST')" />
            <fmt:message key="showValid" bundle="${ rb }" /><br>
        <input type="checkbox" id="invalidCityStatus" name="status" value="0" class="boxMar" onchange="postCountry('controller', 'goShowCountry', 'POST')" />
            <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'goShowCity', validCityStatus: ${validCityStatus}, invalidCityStatus: ${invalidCityStatus}}, 'POST'))">               
            <c:forEach items="${currCountry.cityCollection}" var="row">
                <option class="menuHref" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
            </c:forEach>
        </select>
    </div>
    
    <script>
        boxChecking('validCountryStatus', ${validCountryStatus});
        boxChecking('invalidCountryStatus', ${invalidCountryStatus});
        boxChecking('validCityStatus', ${validCityStatus});
        boxChecking('invalidCityStatus', ${invalidCityStatus});
    </script>
    <script>
        select("currCountry", ${currIdCountry});
    </script>
    
</div>
