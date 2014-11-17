<%-- 
    Document   : orders
    Created on : 09.11.2014, 21:26:44
    Author     : User
--%>

<div id="main">
    <input type="checkbox" id="validOrderStatus" name="status" value="1" class="boxMar" onchange="postOrders('controller', 'goShowOrders', 'POST')" />
        <fmt:message key="showValid" bundle="${ rb }" />
    <input type="checkbox" id="invalidOrderStatus" name="status" value="0" class="boxMar" onchange="postOrders('controller', 'goShowOrders', 'POST')" />
        <fmt:message key="showInvalid" bundle="${ rb }" />
    <script>
        boxChecking('validOrderStatus', ${validOrderStatus});
        boxChecking('invalidOrderStatus', ${invalidOrderStatus});
    </script>
    <input type="hidden" id='currIdTour' value="${currIdTour}" />
    
    <ctg:PageTableTag rows="${ pageList.size }" pageNo="${pageList.currPageNo}" pages="${pageList.pages}">
        <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${pageList.next.tour.direction.picture}"/>

        <div class="padB">
            <ctg:StatusTag status="${pageList.same.tour.direction.status}" href="controller?command=showOrder&selectId=${pageList.same.idOrder}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                ${pageList.same.tour.direction.name} ${pageList.same.tour.departDate}   (${pageList.same.tour.daysCount} <fmt:message key="days" bundle="${ rb }" />)
            </ctg:StatusTag>
        </div>
        <ctg:StatusTag status="${pageList.same.tour.direction.status}" ifValid="small lft labelH" ifInvalid="grey small lft labelH">
            <h4>${pageList.same.tour.direction.text}</h4>
        </ctg:StatusTag>

        <div>
            <ul class="auto containerLabel">
                <ctg:StatusTag status="${pageList.same.status}" href="controller?command=showUser&selectId=${pageList.same.user.idUser}" ifValid="small lft labelH" ifInvalid="grey small lft labelH">
                    <h4><fmt:message key="user" bundle="${ rb }" />:</h4>
                    <h4>${pageList.same.user.login}</h4>
                </ctg:StatusTag>
            </ul>
            <ul class="auto containerLabel">
                <ctg:StatusTag status="${pageList.same.status}" ifValid="small lft labelH" ifInvalid="grey small lft labelH">
                    <h4><fmt:message key="orderDate" bundle="${ rb }" />:</h4>
                    <h4>${pageList.same.orderDate}</h4>
                </ctg:StatusTag>
            </ul>
            <ul class="auto containerLabel">
                <ctg:StatusTag status="${pageList.same.status}" ifValid="blu small lft labelH" ifInvalid="grey small lft labelH">
                    <h4><fmt:message key="seatsCount" bundle="${ rb }" />:</h4>
                    <h4>${pageList.same.seats}</h4>
                </ctg:StatusTag>
            </ul>
            <ul class="auto containerLabel">
                <ctg:StatusTag status="${pageList.same.status}" ifValid="grnt small lft labelH" ifInvalid="grey small lft labelH">
                    <h4><fmt:message key="finalPrice" bundle="${ rb }" />:</h4>
                    <h4>${pageList.same.finalPrice}<fmt:message key="$" bundle="${ rb }" /></h4>
                </ctg:StatusTag>
            </ul>
        </div>
    </ctg:PageTableTag>
</div>

 