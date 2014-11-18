<%-- 
    Document   : editorder
    Created on : 14.10.2014, 8:11:39
    Author     : Helena.Grouk
--%>

<div id="main">
    <form id="updOrder" method="POST" action="controller" onsubmit="return validateOrderForm();">
        <input type="hidden" name="command" value="goCreateNewOrder"/>
        <div class="inner">
            <div class="parameterRowB">
                <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${currTour.direction.picture}"/>
                <div class="padB">
                    <ctg:StatusTag status="${currTour.direction.status}" href="controller?command=showDirection&selectId=${currTour.direction.idDirection}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                        ${currTour.direction.name} ${currTour.departDate}   (${currTour.daysCount} <fmt:message key="days" bundle="${ rb }" />)
                    </ctg:StatusTag>
                </div>
                <h2 class="small lft labelH">${currTour.direction.text}</h2>
                <div>
                    <ul class="containerLabel">
                        <div class="lblH"><fmt:message key="tourType" bundle="${ rb }" />:</div>
                        <fmt:message key="${currTour.direction.tourType.nameTourType}" bundle="${ rb }" />
                    </ul>
                    <ul class="containerLabel">
                        <div class="lblH"><fmt:message key="transMode" bundle="${ rb }" />:</div>
                        <fmt:message key="${currTour.direction.transMode.nameMode}" bundle="${ rb }" />
                    </ul>
                    <ul class="containerLabel">
                        <div class="lblH"><fmt:message key="freeSeats" bundle="${ rb }" />:</div>
                        ${currTour.freeSeats}
                    </ul>
                    <ul class="containerLabel">
                        <h2 class="small lft labelH"><fmt:message key="price" bundle="${ rb }" />:</h2>
                        <h2 class="small lft labelH">${currTour.price}<fmt:message key="$" bundle="${ rb }" /></h2>
                    </ul>
                    <ul class="containerLabel">
                        <h2 class="blu small lft labelH"><fmt:message key="discount" bundle="${ rb }" />:</h2>
                        <h2 class="blu small lft labelH">${currTour.discount}%</h2>
                    </ul>
                    <ul class="containerLabel">
                        <h2 class="grnt small lft labelH"><fmt:message key="finalPrice" bundle="${ rb }" />:</h2>
                        <h2 class="grnt small lft labelH"><ctg:tourTag price="${currTour.price}" discount="${currTour.discount}"><fmt:message key="$" bundle="${ rb }" /></ctg:tourTag></h2>
                    </ul>
                </div>
            </div>
            <div class="parameterRowB">
                <div class="mid input">
                    <h2 class="blu small labelH"><fmt:message key="personalDiscount" bundle="${ rb }" />: </h2>
                </div>
                <div class="input inner">
                    <h2 class="blu small lft labelH">${user.discount}%</h2>
                </div>
                <div class="mid input">
                    <h2 class="grnt small labelH"><fmt:message key="priceWithPersonalDiscount" bundle="${ rb }" />: </h2>
                </div>
                <div class="input inner">
                    <h2 class="grnt small lft labelH"><ctg:tourTag price="${currTour.price}" discount="${currTour.discount}" userDiscount="${user.discount}"><fmt:message key="$" bundle="${ rb }" /></ctg:tourTag></h2>
                </div>
            </div>    
           
            <div class="parameterRowB">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="selectSeatsCount" bundle="${ rb }" />: </h1>
                </div>
                <div class="input inner">
                    <select id="currSeats" name="currSeats" class="selectContainer" size="1" onchange="funcAddTourist(this.value)">      
                        <c:forEach var="i" begin="1" end="${currTour.freeSeats}">
                            <option class="selectItem" value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    <script>select("currSeats", ${currOrder.seats});</script>
                </div>
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
            <input class="large blue awesome" type="submit" value="<fmt:message key="book" bundle="${ rb }" />"/>
            <div id="erNote"><a id="touristListErrMsg" hidden="true"><fmt:message key="message.errorTableTourist" bundle="${ rb }" /></a></div>
            <ctg:ErrorMsgTag classErr="erNote" msg="${errorSave}"><fmt:message key="${errorSave}" bundle="${ rb }" /></ctg:ErrorMsgTag>
            <ctg:ErrorMsgTag classErr="erNote" msg="${errorSaveReason}"><fmt:message key="${errorSaveReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
        </div>
    </form>
</div>