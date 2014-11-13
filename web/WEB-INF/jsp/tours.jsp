<%-- 
    Document   : tours
    Created on : 29.09.2014, 20:47:48
    Author     : User
--%>


<div id="main">
    
    
    <jsp:include page="/WEB-INF/other/searching.jsp" />
    <div class="parameterRow">
        <div id="erNote" class="centrale">${emptysearch}</div>
    </div>
    
    <ctg:PageTableTag rows="${ pageList.size }" pageNo="${pageList.currPageNo}" pages="${pageList.pages}">
        
        <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${pageList.next.direction.picture}"/>
        
        <div class="padB">
            <ctg:StatusTag status="${pageList.same.direction.status}" href="controller?command=showDirection&selectId=${pageList.same.direction.idDirection}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                ${pageList.same.direction.name} ${pageList.same.departDate}   (${pageList.same.daysCount} <fmt:message key="days" bundle="${ rb }" />)
            </ctg:StatusTag>
        </div>
        <h2 class="small lft labelH">${pageList.same.direction.text}</h2>

        <div>
            <ul class="containerLabel">
                <div class="lblH"><fmt:message key="tourType" bundle="${ rb }" />:</div>
                <fmt:message key="${pageList.same.direction.tourType.nameTourType}" bundle="${ rb }" />
            </ul>
            <ul class="containerLabel">
                <div class="lblH"><fmt:message key="transMode" bundle="${ rb }" />:</div>
                <fmt:message key="${pageList.same.direction.transMode.nameMode}" bundle="${ rb }" />
            </ul>
            <ul class="containerLabel">
                <div class="lblH"><fmt:message key="freeSeats" bundle="${ rb }" />:</div>
                ${pageList.same.freeSeats}
            </ul>
            <ul class="containerLabel">
                <h2 class="small lft labelH"><fmt:message key="price" bundle="${ rb }" />:</h2>
                <h2 class="small lft labelH">${pageList.same.price}<fmt:message key="$" bundle="${ rb }" /></h2>
            </ul>
            <ul class="containerLabel">
                <h2 class="blu small lft labelH"><fmt:message key="discount" bundle="${ rb }" />:</h2>
                <h2 class="blu small lft labelH">${pageList.same.discount}%</h2>
            </ul>
            <ul class="containerLabel">
                <h2 class="grnt small lft labelH"><fmt:message key="finalPrice" bundle="${ rb }" />:</h2>
                <h2 class="grnt small lft labelH">${pageList.same.price * (100 - pageList.same.discount) / 100 }<fmt:message key="$" bundle="${ rb }" /></h2>
            </ul>
        </div>
        <div>
            <ul class="containerLabel">
                <div class="lblH"><fmt:message key="directionCountryTags" bundle="${ rb }" />:</div>
                <c:forEach items="${pageList.same.direction.countryCollection}" var="cntr">
                    <li><ctg:StatusTag status="${cntr.status}" ifInvalid="greyA"><fmt:message key="${cntr.name}" bundle="${ rb }" /></ctg:StatusTag></li>
                </c:forEach>
            </ul>
            <ul class="containerLabel">
                <div class="lblH"><fmt:message key="directionCityTags" bundle="${ rb }" />:</div>
                <c:forEach items="${pageList.same.direction.cityCollection}" var="ct">
                    <li><ctg:StatusTag status="${ct.status}" ifInvalid="greyA"><fmt:message key="${ct.name}" bundle="${ rb }" /></ctg:StatusTag></li>
                </c:forEach>
            </ul>
            <ul class="containerLabelR">
                <div class="lblH"><fmt:message key="directionStayHotels" bundle="${ rb }" />:</div>
                <c:forEach items="${pageList.same.direction.stayCollection}" var="st">
                    <li><ctg:StatusTag status="${st.hotel.status}" ifInvalid="greyA">${st.hotel.name} ${st.hotel.stars}* (<fmt:message key="${st.hotel.city.name}" bundle="${ rb }" />)</ctg:StatusTag></li>
                </c:forEach>
            </ul>
        </div>   
       
        <c:if test="${pageList.same.status == 1}">
            <input class="large green awesome" type="submit" value="<fmt:message key="buy" bundle="${ rb }" />" onclick="post('controller', {command: 'goBuyTour', currIdTour: ${pageList.same.idTour}}, 'POST')"/>
        </c:if>
    </ctg:PageTableTag>
</div>