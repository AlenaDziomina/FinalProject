<%-- 
    Document   : tourists
    Created on : 17.11.2014, 15:42:06
    Author     : Helena.Grouk
--%>

<div id="main">
    <input type="checkbox" id="validOrderStatus" name="status" value="1" class="boxMar" onchange="postOrders('controller', 'ShowTourTourists', 'POST')" />
        <fmt:message key="showValid" bundle="${ rb }" />
    <input type="checkbox" id="invalidOrderStatus" name="status" value="0" class="boxMar" onchange="postOrders('controller', 'ShowTourTourists', 'POST')" />
        <fmt:message key="showInvalid" bundle="${ rb }" />
    <script>
        boxChecking('validOrderStatus', ${validOrderStatus});
        boxChecking('invalidOrderStatus', ${invalidOrderStatus});
    </script>
    
    <div class="inner">
        <div class="parameterRow">
            <div class="padB">
                <ctg:StatusTag status="${currTour.direction.status}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                    ${currTour.direction.name} ${currTour.departDate}   (${currTour.daysCount} <fmt:message key="days" bundle="${ rb }" />)
                </ctg:StatusTag>
            </div>
            <ctg:StatusTag status="${currTour.direction.status}" ifValid="small lft labelH" ifInvalid="grey small lft labelH">
                <h4>${currTour.direction.text}</h4>
            </ctg:StatusTag>
            <input type="hidden" id='currIdTour' value="${currIdTour}" />
        </div>
            
        <div class="parameterRow">
            <table class="border padB" id="currTourists">
                <colgroup>
                    <col class="border fio"/>
                    <col class="border passport" />
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col"><fmt:message key="FIO" bundle="${ rb }" /></th>
                        <th scope="col"><fmt:message key="passport" bundle="${ rb }" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tourist" items="${touristList}">
                        <c:choose>
                            <c:when test="${tourist.status == 1}">
                                <tr>
                                    <td class="border fio">${tourist.firstName} ${tourist.middleName} ${tourist.lastName}</td>
                                    <td class="border passport">${tourist.passport}</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr class="greyBG">
                                    <td class="border fio">${tourist.firstName} ${tourist.middleName} ${tourist.lastName}</td>
                                    <td class="border passport">${tourist.passport}</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
