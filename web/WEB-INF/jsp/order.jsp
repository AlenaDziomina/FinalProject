<%-- 
    Document   : order
    Created on : 09.11.2014, 21:26:14
    Author     : User
--%>

<div id="main">
    <div class="inner">
        <div class="parameterRowB">
            <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${currOrder.tour.direction.picture}"/>
            <div class="padB">
                <ctg:StatusTag status="${currOrder.tour.direction.status}" href="controller?command=showDirection&selectId=${currOrder.tour.direction.idDirection}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                    ${currOrder.tour.direction.name} ${currOrder.tour.departDate}   (${currOrder.tour.daysCount} <fmt:message key="days" bundle="${ rb }" />)
                </ctg:StatusTag>
            </div>
            <ctg:StatusTag status="${currOrder.tour.direction.status}" ifValid="small lft labelH" ifInvalid="grey small lft labelH">
                <h4>${currOrder.tour.direction.text}</h4>
            </ctg:StatusTag>
            <div>
                <ul class="containerLabel">
                    <ctg:StatusTag status="${currOrder.status}" href="controller?command=showUser&selectId=${currOrder.user.idUser}" ifValid="grnt small lft labelH" ifInvalid="grey small lft labelH">
                        <h3><fmt:message key="user" bundle="${ rb }" />:</h3>
                        <h3>${currOrder.user.login}</h3>
                    </ctg:StatusTag>
                </ul>
                <ul class="containerLabel">
                    <ctg:StatusTag status="${currOrder.status}" ifValid="blu small lft labelH" ifInvalid="grey small lft labelH">
                        <h3><fmt:message key="orderDate" bundle="${ rb }" />:</h3>
                        <h3>${currOrder.orderDate}</h3>
                    </ctg:StatusTag>
                </ul>
            </div>
        </div>
            
        <div class="parameterRowB">
            <div class="mid input">
                <h2 class="blu small labelH"><fmt:message key="price" bundle="${ rb }" />: </h2>
            </div>
            <div class="input inner">
                <h2 class="blu small lft labelH">${currOrder.currentPrice}<fmt:message key="$" bundle="${ rb }" /></h2>
            </div>
            <div class="mid input">
                <h2 class="blu small labelH"><fmt:message key="discount" bundle="${ rb }" />: </h2>
            </div>
            <div class="input inner">
                <h2 class="blu small lft labelH">${currOrder.currentDiscount}%</h2>
            </div>
            <div class="mid input">
                <h2 class="blu small labelH"><fmt:message key="personalDiscount" bundle="${ rb }" />: </h2>
            </div>
            <div class="input inner">
                <h2 class="blu small lft labelH">${currOrder.currentUserDiscount}%</h2>
            </div>
            <div class="mid input">
                <h2 class="grnt small labelH"><fmt:message key="seatsCount" bundle="${ rb }" />: </h2>
            </div>
            <div class="input inner">
                <h2 class="grnt small lft labelH">${currOrder.seats}</h2>
            </div>
            <div class="mid input">
                <h2 class="grnt small labelH"><fmt:message key="finalPriceWithDiscount" bundle="${ rb }" />: </h2>
            </div>
            <div class="input inner">
                <h2 class="grnt small lft labelH">${currOrder.finalPrice}<fmt:message key="$" bundle="${ rb }" /></h2>
            </div>
        </div>    

        <div class="parameterRowB">
            <table class="ordertable" id="currTourists">
                <colgroup>
                    <col class="fio"/>
                    <col class="passport" />
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col"><fmt:message key="FIO" bundle="${ rb }" /></th>
                        <th scope="col"><fmt:message key="passport" bundle="${ rb }" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tourist" items="${currOrder.touristCollection}">
                        <tr>
                            <td class="border fio">${tourist.firstName} ${tourist.middleName} ${tourist.lastName}</td>
                            <td class="border passport">${tourist.passport}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div>
        <c:if test="${currOrder.status == 1}">
            <ctg:RoleTag>
                <input class="large orange awesome" type="submit" value="<fmt:message key="editOrder" bundle="${ rb }" />" onclick="postOrder('controller', 'goEditOrder', 'POST')"/>
            </ctg:RoleTag>
            <ctg:RoleUserTag>
                <input class="large red awesome" type="submit" value="<fmt:message key="deleteOrder" bundle="${ rb }" />" onclick="postOrder('controller', 'DeleteOrder', 'POST')"/>
                <div id="erNote"><fmt:message key="${errorDelete}" bundle="${ rb }" /></div>
                <div id="erNote"><fmt:message key="${errorDeleteReason}" bundle="${ rb }" /></div>
            </ctg:RoleUserTag>
        </c:if>
    </div>
</div>
