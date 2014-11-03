<%-- 
    Document   : direction.jsp
    Created on : 01.10.2014, 21:15:02
    Author     : User
--%>

<div id="main">
    <div class="inner">
        <div class="parameterRowD">
            <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${currDirection.picture}"/>
            <div class="padB">
                <ctg:StatusTag status="${currDirection.status}" href="controller?command=showDirection&selectId=${currDirection.idDirection}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                    ${currDirection.name}
                </ctg:StatusTag>
            </div>
            <h2 class="small lft labelH">${currDirection.text}</h2>
            <div class="lblH">
                tourType: ${currDirection.tourType.nameTourType}    transMode: ${currDirection.transMode.nameMode} 
            </div>
            <div>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCountryTags:</div>
                    <c:forEach items="${currDirection.countryCollection}" var="cntr">
                        <li><ctg:StatusTag status="${cntr.status}" ifInvalid="greyA">${cntr.name}</ctg:StatusTag></li>
                    </c:forEach>
                </ul>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCityTags:</div>
                    <c:forEach items="${currDirection.cityCollection}" var="ct">
                        <li><ctg:StatusTag status="${ct.status}" ifInvalid="greyA">${ct.name}</ctg:StatusTag></li>
                    </c:forEach>
                </ul>
                <ul class="containerLabelR">
                    <div class="lblH">DirectionStayHotels:</div>
                    <c:forEach items="${currDirection.stayCollection}" var="st">
                        <li><ctg:StatusTag status="${st.status}" ifInvalid="greyA">${st.hotel.name} ${st.hotel.stars}* (${st.hotel.city.name})</ctg:StatusTag></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="textRow">
                ${currDirection.description.text}
            </div>
        </div>
        
        <div class="parameterRowDR">
            <ctg:RoleTag>
                <input class="large green awesome" type="submit" value="<fmt:message key="newDirection" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewDirection'}, 'POST')"/>
                <c:choose>
                    <c:when test="${currDirection.status == 1}">
                        <input class="large orange awesome" type="submit" value="<fmt:message key="editDirection" bundle="${ rb }" />" onclick="post('controller', {command: 'goEditDirection'}, 'POST')"/>
                        <input class="large red awesome" type="submit" value="<fmt:message key="deleteDirection" bundle="${ rb }" />" onclick="post('controller', {command: 'DeleteDirection'}, 'POST')"/>
                    </c:when>
                    <c:when test="${currDirection.status == 0}">
                        <input class="large green awesome" type="submit" value="<fmt:message key="restoreDirection" bundle="${ rb }" />" onclick="post('controller', {command: 'RestoreDirection'}, 'POST')"/>
                    </c:when>
                </c:choose>    
            </ctg:RoleTag>
            
            <ctg:RoleTag>
                <dir>
                <input type="checkbox" id="validTourStatus" name="status" value="1" class="boxMar" onchange="postTourDir('controller', 'ShowDirection', 'POST')" />
                    <fmt:message key="showValid" bundle="${ rb }" /><br>
                <input type="checkbox" id="invalidTourStatus" name="status" value="0" class="boxMar" onchange="postTourDir('controller', 'ShowDirection', 'POST')" />
                    <fmt:message key="showInvalid" bundle="${ rb }" /><br>
                <input type="checkbox" id="validTourDate" name="status" value="1" class="boxMar" onchange="postTourDir('controller', 'ShowDirection', 'POST')" />
                    <fmt:message key="showValidDate" bundle="${ rb }" /><br>
                <input type="checkbox" id="invalidTourDate" name="status" value="0" class="boxMar" onchange="postTourDir('controller', 'ShowDirection', 'POST')" />
                    <fmt:message key="showInvalidDate" bundle="${ rb }" /><br>
                </dir>
            </ctg:RoleTag>
                        
            <script>
                boxChecking('validTourStatus', ${validTourStatus});
                boxChecking('invalidTourStatus', ${invalidTourStatus});
                boxChecking('validTourDate', ${validTourDate});
                boxChecking('invalidTourDate', ${invalidTourDate});
            </script>

            <select class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showTour'}, 'POST'))">          
                <c:forEach items="${currDirection.tourCollection}" var="row">
                    <ctg:OptionTag status="${row.status}" date="${row.departDate}" 
                               valClass="menuHref" invalClass="grey menuHref" 
                               invalDateClass="blue menuHref" value="${row.idTour}">
                        ${row.departDate}
                    </ctg:OptionTag>
                </c:forEach>
            </select>
                        
            <c:if test="${currDirection.status == 1}">
                <ctg:RoleTag>
                    <input class="large green awesome" type="submit" value="<fmt:message key="newTour" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewTour', currIdDirection: '${currDirection.idDirection}' }, 'POST')" />
                </ctg:RoleTag>
            </c:if>
        </div>
    </div>
</div>
