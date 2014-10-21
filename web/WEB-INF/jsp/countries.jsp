<%-- 
    Document   : countries
    Created on : 25.09.2014, 0:41:29
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    
    <div class="left inner column">
        <select id="currCountry" class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showCountry'}, 'POST'))">               
            <c:forEach items="${countryList}" var="row">
                <option class="menuHref" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
            </c:forEach>
        </select>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goCreateNewCountry" />
            <input class="large green awesome" type="submit" value="<fmt:message key="newCountry" bundle="${ rb }" />"/>
        </form>
    </div>
    
    <div class="center inner column">
        <div id="erNote">${errorGetListMessage}</div>
        <ctg:ImgTag classImg="currimg" idImg="images" nameImg="${currCountry.picture}"/>
        <div class="cueetext">
            ${currCountry.description.text}
        </div>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goEditCountry" />
            <input class="centrale large orange awesome" type="submit" value="<fmt:message key="editCountry" bundle="${ rb }" />"/>
        </form>
    </div>
        
    <div class="rigth inner column">
        <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showCity'}, 'POST'))">               
            <c:forEach items="${currCountry.cityCollection}" var="row">
                <option class="menuHref" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
            </c:forEach>
        </select>
    </div>
    
    <script type="text/javascript">
        select("currCountry", ${currIdCountry});
    </script>
    
</div>
