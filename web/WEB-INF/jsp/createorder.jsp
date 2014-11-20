<%-- 
    Document   : createorder
    Created on : 06.11.2014, 19:18:32
    Author     : Helena.Grouk
--%>

<div id="main">
    <form id="updOrder" method="POST" action="controller">
        <input type="hidden" name="command" value="PayForOrder"/>
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
                        <div class="lblH"><fmt:message key="tourType" bundle="${ rb }" />:</div>
                        <fmt:message key="${currOrder.tour.direction.tourType.nameTourType}" bundle="${ rb }" />
                    </ul>
                    <ul class="containerLabel">
                        <div class="lblH"><fmt:message key="transMode" bundle="${ rb }" />:</div>
                        <fmt:message key="${currOrder.tour.direction.transMode.nameMode}" bundle="${ rb }" /> 
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
                                <td class="border fio">${tourist.lastName} ${tourist.firstName} ${tourist.middleName} </td>
                                <td class="border passport">${tourist.passport}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="parameterRow centrale">
            <input class="large magenta awesome" type="submit" value="<fmt:message key="pay" bundle="${ rb }" />"/>
            <ctg:ErrorMsgTag classErr="erNote" msg="${errorSave}"><fmt:message key="${errorSave}" bundle="${ rb }" /></ctg:ErrorMsgTag>
            <ctg:ErrorMsgTag classErr="erNote" msg="${errorSaveReason}"><fmt:message key="${errorSaveReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
        </div>
    </form>
</div>