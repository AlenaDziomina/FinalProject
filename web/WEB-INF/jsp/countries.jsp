<%-- 
    Document   : countries
    Created on : 25.09.2014, 0:41:29
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    <div class="left inner column">
        <ctg:RoleTag>
            <input type="checkbox" id="validCountryStatus" name="status" value="1" class="boxMar" onchange="postCountry('controller', 'goShowCountry', 'POST')" />
                <fmt:message key="showValid" bundle="${ rb }" /><br>
            <input type="checkbox" id="invalidCountryStatus" name="status" value="0" class="boxMar" onchange="postCountry('controller', 'goShowCountry', 'POST')" />
                <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        </ctg:RoleTag>
                
        <select id="currCountry" class="container" size="15" onclick="if(this.value)(postCountry('controller', 'ShowCountry', 'POST'))">               
            <c:forEach items="${countryList}" var="row">
                <ctg:OptionTag status="${row.status}" valClass="menuHref" invalClass="grey menuHref" value="${row.idCountry}">
                    <fmt:message key="${row.name}" bundle="${ rb }" />
                </ctg:OptionTag>
            </c:forEach>
        </select>
                
        <ctg:RoleTag>
            <input class="large green awesome" type="submit" value="<fmt:message key="newCountry" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewCountry'}, 'POST')"/>
        </ctg:RoleTag>
    </div>
    
    <script>
        select("currCountry", ${currIdCountry});
    </script>
    
    <div class="center inner column">
        <div id="erNote">${errorGetListMessage}</div>
        <ctg:ImgTag classImg="currimg" idImg="images" nameImg="${currCountry.picture}"/>
        <div class="cueetext">
            ${currCountry.description.text}
        </div>
        
        <ctg:RoleTag>
            <c:choose>
                <c:when test="${currCountry.status == 1}">
                    <input class="large orange awesome" type="submit" value="<fmt:message key="editCountry" bundle="${ rb }" />" onclick="postCountry('controller', 'goEditCountry', 'POST')"/>
                    <input class="large red awesome" type="submit" value="<fmt:message key="deleteCountry" bundle="${ rb }" />" onclick="postCountry('controller', 'DeleteCountry', 'POST')"/>
                    <div id="erNote"><fmt:message key="${errorDelete}" bundle="${ rb }" /></div>
                    <div id="erNote"><fmt:message key="${errorDeleteReason}" bundle="${ rb }" /></div>
                </c:when>
                <c:when test="${currCountry.status == 0}">
                    <input class="large green awesome" type="submit" value="<fmt:message key="restoreCountry" bundle="${ rb }" />" onclick="postCountry('controller', 'RestoreCountry', 'POST')"/>
                    <div id="erNote"><fmt:message key="${errorRestore}" bundle="${ rb }" /></div>
                    <div id="erNote"><fmt:message key="${errorRestoreReason}" bundle="${ rb }" /></div>
                </c:when>
            </c:choose>    
         </ctg:RoleTag>
    </div>
        
    <div class="rigth inner column">
        <ctg:RoleTag>
            <input type="checkbox" id="validCityStatus" name="status" value="1" class="boxMar" onchange="postCountry('controller', 'goShowCountry', 'POST')" />
                <fmt:message key="showValid" bundle="${ rb }" /><br>
            <input type="checkbox" id="invalidCityStatus" name="status" value="0" class="boxMar" onchange="postCountry('controller', 'goShowCountry', 'POST')" />
                <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        </ctg:RoleTag>
                
        <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'goShowCity'}, 'POST'))">               
            <c:forEach items="${currCountry.cityCollection}" var="row">
                <ctg:OptionTag status="${row.status}" valClass="menuHref" invalClass="grey menuHref" value="${row.idCity}">
                    <fmt:message key="${row.name}" bundle="${ rb }" />
                </ctg:OptionTag>
            </c:forEach>
        </select>
    </div>
        
    <script>
        boxChecking('validCountryStatus', ${validCountryStatus});
        boxChecking('invalidCountryStatus', ${invalidCountryStatus});
        boxChecking('validCityStatus', ${validCityStatus});
        boxChecking('invalidCityStatus', ${invalidCityStatus});
    </script>
    
    
</div>
