<%-- 
    Document   : cities
    Created on : 27.09.2014, 16:13:48
    Author     : User
--%>



<div id="main">
    <div class="left inner column">
        <ctg:RoleTag>
            <input type="checkbox" id="validCityStatus" name="status" value="1" class="boxMar" onchange="postCity('controller', 'goShowCity', 'POST')" />
                <fmt:message key="showValid" bundle="${ rb }" /><br>
            <input type="checkbox" id="invalidCityStatus" name="status" value="0" class="boxMar" onchange="postCity('controller', 'goShowCity', 'POST')" />
                <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        </ctg:RoleTag>
        
        <select id="currCity" class="container" size="15" 
                onchange="if(this.value)(postCity('controller', 'showCity', 'POST'))">               
            <c:forEach items="${cityList}" var="row">
                <ctg:OptionTag status="${row.status}" valClass="menuHref" invalClass="grey menuHref" value="${row.idCity}">
                    <fmt:message key="${row.name}" bundle="${ rb }" />
                </ctg:OptionTag>
            </c:forEach>
        </select>
        <ctg:RoleTag>
            <input class="large green awesome" type="submit" value="<fmt:message key="newCity" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewCity'}, 'POST')"/>
        </ctg:RoleTag>
    </div>
    
    <script>
        select("currCity", ${currIdCity});
    </script>
    
    <div class="center inner column">
        <div id="erNote">${errorGetListMessage}</div>
        <ctg:ImgTag classImg="currimg" idImg="images" nameImg="${currCity.picture}"/>
        <div class="cueetext">
             ${currCity.description.text}
        </div>
        
        <ctg:RoleTag>
            <c:choose>
                <c:when test="${currCity.status == 1}">
                    <input class="large orange awesome" type="submit" value="<fmt:message key="editCity" bundle="${ rb }" />" onclick="postCity('controller', 'goEditCity', 'POST')"/>
                    <input class="large red awesome" type="submit" value="<fmt:message key="deleteCity" bundle="${ rb }" />" onclick="postCity('controller', 'DeleteCity', 'POST')"/>
                </c:when>
                <c:when test="${currCity.status == 0}">
                    <input class="large green awesome" type="submit" value="<fmt:message key="restoreCity" bundle="${ rb }" />" onclick="postCity('controller', 'RestoreCity', 'POST')"/>
                </c:when>
            </c:choose>   
        </ctg:RoleTag>
    </div>
        
    <div class="rigth inner column">
        <ctg:RoleTag>
            <input type="checkbox" id="validHotelStatus" name="status" value="1" class="boxMar" onchange="postCity('controller', 'goShowCity', 'POST')" />
                <fmt:message key="showValid" bundle="${ rb }" /><br>
            <input type="checkbox" id="invalidHotelStatus" name="status" value="0" class="boxMar" onchange="postCity('controller', 'goShowCity', 'POST')" />
                <fmt:message key="showInvalid" bundle="${ rb }" /><br>
        </ctg:RoleTag>
        
        <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'goShowHotel', validHotelStatus: ${validHotelStatus}, invalidHotelStatus: ${invalidHotelStatus}}, 'POST'))">           
            <c:forEach items="${currCity.hotelCollection}" var="row">
                <ctg:OptionTag status="${row.status}" valClass="menuHref" invalClass="grey menuHref" value="${row.idHotel}">
                    ${row.name}
                </ctg:OptionTag>
            </c:forEach>
        </select>
    </div>
        
    <script>
        boxChecking('validCityStatus', ${validCityStatus});
        boxChecking('invalidCityStatus', ${invalidCityStatus});
        boxChecking('validHotelStatus', ${validHotelStatus});
        boxChecking('invalidHotelStatus', ${invalidHotelStatus});
    </script>
    

</div>
