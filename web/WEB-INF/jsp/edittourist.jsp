<%-- 
    Document   : edittourist
    Created on : 10.11.2014, 17:39:42
    Author     : User
--%>

<div id="main">
    <form id="updOrder" method="POST" action="controller" onsubmit="return validateOrderForm();">
        <input type="hidden" name="command" value="SaveRedactOrder"/>
        <div class="inner">
            <div class="parameterRowB">
                <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${currOrder.tour.direction.picture}"/>
                <div class="padB">
                    <ctg:StatusTag status="${currOrder.tour.direction.status}" href="controller?command=showDirection&selectId=${currOrder.tour.direction.idDirection}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                        ${currOrder.tour.direction.name} ${currOrder.tour.departDate}   (${currOrder.tour.daysCount} <fmt:message key="days" bundle="${ rb }" />)
                    </ctg:StatusTag>
                </div>
                <h2 class="small lft labelH">${currOrder.tour.direction.text}</h2>
                <div>
                    <ul class="containerLabel">
                        <h2 class="small lft labelH"><fmt:message key="user" bundle="${ rb }" />:</h2>
                        <h2 class="small lft labelH">${currOrder.user.login}</h2>
                    </ul>
                    <ul class="containerLabel">
                        <h2 class="small lft labelH"><fmt:message key="orderDate" bundle="${ rb }" />:</h2>
                        <h2 class="small lft labelH">${currOrder.orderDate}</h2>
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
                <div class="parameterRow">
                    <table class="padB" id="currTourists">
                        <colgroup>
                            <col class="firstName"/>
                            <col class="middleName"/>
                            <col class="lastName" />
                            <col class="passport" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col"><fmt:message key="firstName" bundle="${ rb }" /></th>
                                <th scope="col"><fmt:message key="middleName" bundle="${ rb }" /></th>
                                <th scope="col"><fmt:message key="lastName" bundle="${ rb }" /></th>
                                <th scope="col"><fmt:message key="passport" bundle="${ rb }" /></th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <c:forEach var="tourist" items="${currOrder.touristCollection}">
                                <tr>
                                    <td><input name="firstName" class="firstName" type="text" value="${tourist.firstName}" /></td>
                                    <td><input name="middleName" class="middleName" type="text" value="${tourist.middleName}" /></td>
                                    <td><input name="lastName" class="lastName" type="text" value="${tourist.lastName}" /></td>
                                    <td><input name="passport" class="passport" type="text" value="${tourist.passport}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
            </div>
            
        </div>
        <div class="parameterRow centrale">
            <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />"/>
            <div id="erNote"><a id="touristListErrMsg" hidden="true"><fmt:message key="message.errorTableTourist" bundle="${ rb }" /></a></div>
            <ctg:ErrorMsgTag classErr="erNote" msg="${errorSave}"><fmt:message key="${errorSave}" bundle="${ rb }" /></ctg:ErrorMsgTag>
            <ctg:ErrorMsgTag classErr="erNote" msg="${errorSaveReason}"><fmt:message key="${errorSaveReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
        </div>
    </form>
</div>
