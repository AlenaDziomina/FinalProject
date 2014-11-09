<%-- 
    Document   : userorder
    Created on : 09.11.2014, 17:02:27
    Author     : User
--%>

<div id="main">
    <input type="checkbox" id="validOrderStatus" name="status" value="1" class="boxMar" onchange="postOrders('controller', 'goShowUserOrder', 'POST')" />
        <fmt:message key="showValid" bundle="${ rb }" />
    <input type="checkbox" id="invalidOrderStatus" name="status" value="0" class="boxMar" onchange="postOrders('controller', 'goShowUserOrder', 'POST')" />
        <fmt:message key="showInvalid" bundle="${ rb }" />
    <script>
        boxChecking('validOrderStatus', ${validOrderStatus});
        boxChecking('invalidOrderStatus', ${invalidOrderStatus});
    </script>
        
    <ctg:PageTableTag rows="${ pageList.size }" pageNo="${pageList.currPageNo}" pages="${pageList.pages}">
        <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${pageList.next.tour.direction.picture}"/>

        <div class="padB">
            <ctg:StatusTag status="${pageList.same.tour.direction.status}" href="controller?command=showOrder&selectId=${pageList.same.idOrder}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                ${pageList.same.tour.direction.name} ${pageList.same.tour.departDate}   (${pageList.same.tour.daysCount} <fmt:message key="days" bundle="${ rb }" />)
            </ctg:StatusTag>
        </div>
        <h2 class="small lft labelH">${pageList.same.tour.direction.text}</h2>

        <div>
            <ul class="containerLabel">
                <h2 class="blu small lft labelH"><fmt:message key="seatsCount" bundle="${ rb }" />:</h2>
                <h2 class="blu small lft labelH">${pageList.same.seats}</h2>
            </ul>
            <ul class="containerLabel">
                <h2 class="grnt small lft labelH"><fmt:message key="finalPrice" bundle="${ rb }" />:</h2>
                <h2 class="grnt small lft labelH">${pageList.same.finalPrice}<fmt:message key="$" bundle="${ rb }" /></h2>
            </ul>
        </div>
    </ctg:PageTableTag>
</div>