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
            <div>
                <ul class="containerLabel">
                    <div class="lblH"><fmt:message key="tourType" bundle="${ rb }" />:</div>
                    <fmt:message key="${currDirection.tourType.nameTourType}" bundle="${ rb }" />
                </ul>
                <ul class="containerLabel">
                    <div class="lblH"><fmt:message key="transMode" bundle="${ rb }" />:</div>
                    <fmt:message key="${currDirection.transMode.nameMode}" bundle="${ rb }" />
                </ul>
                <ul class="containerLabel">
                    <div class="lblH"><fmt:message key="directionCountryTags" bundle="${ rb }" />:</div>
                    <c:forEach items="${currDirection.countryCollection}" var="cntr">
                        <li><ctg:StatusTag status="${cntr.status}" ifInvalid="greyA"><fmt:message key="${cntr.name}" bundle="${ rb }" /></ctg:StatusTag></li>
                    </c:forEach>
                </ul>
                <ul class="containerLabel">
                    <div class="lblH"><fmt:message key="directionCityTags" bundle="${ rb }" />:</div>
                    <c:forEach items="${currDirection.cityCollection}" var="ct">
                        <li><ctg:StatusTag status="${ct.status}" ifInvalid="greyA"><fmt:message key="${ct.name}" bundle="${ rb }" /></ctg:StatusTag></li>
                    </c:forEach>
                </ul>
                <ul class="containerLabelR">
                    <div class="lblH"><fmt:message key="directionStayHotels" bundle="${ rb }" />:</div>
                    <c:forEach items="${currDirection.stayCollection}" var="st">
                        <ctg:StatusTag status="${st.hotel.status}" ifInvalid="greyA" role="ADMIN">
                            <li> ${st.hotel.name} ${st.hotel.stars} * 
                            <ctg:StatusTag status="${st.hotel.city.status}" ifInvalid="greyA" role="ADMIN">
                                ( <fmt:message key="${st.hotel.city.name}" bundle="${ rb }" /> )
                            </ctg:StatusTag> </li>
                        </ctg:StatusTag>
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
                        <ctg:ErrorMsgTag classErr="erNote" msg="${errorDelete}"><fmt:message key="${errorDelete}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                        <ctg:ErrorMsgTag classErr="erNote" msg="${errorDeleteReason}"><fmt:message key="${errorDeleteReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                    </c:when>
                    <c:when test="${currDirection.status == 0}">
                        <input class="large green awesome" type="submit" value="<fmt:message key="restoreDirection" bundle="${ rb }" />" onclick="post('controller', {command: 'RestoreDirection'}, 'POST')"/>
                        <ctg:ErrorMsgTag classErr="erNote" msg="${errorRestore}"><fmt:message key="${errorRestore}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                        <ctg:ErrorMsgTag classErr="erNote" msg="${errorRestoreReason}"><fmt:message key="${errorrestoreReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
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
