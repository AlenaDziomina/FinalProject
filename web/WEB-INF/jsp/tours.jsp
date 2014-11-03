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
                ${pageList.same.direction.name} ${pageList.same.departDate}   (${pageList.same.daysCount} days)
            </ctg:StatusTag>
        </div>
        <h2 class="small lft labelH">${pageList.same.direction.text}</h2>

        <div>
            <ul class="containerLabel">
                <div class="lblH">tourType:</div>
                ${pageList.same.direction.tourType.nameTourType}
            </ul>
            <ul class="containerLabel">
                <div class="lblH">transMode:</div>
                ${pageList.same.direction.transMode.nameMode} 
            </ul>
            <ul class="containerLabel">
                <div class="lblH">FreeSeats:</div>
                ${pageList.same.freeSeats}
            </ul>
            <ul class="containerLabel">
                <h2 class="small lft labelH">Price:</h2>
                <h2 class="small lft labelH">${pageList.same.price}</h2>
            </ul>
            <ul class="containerLabel">
                <h2 class="blu small lft labelH">Discount:</h2>
                <h2 class="blu small lft labelH">${pageList.same.discount}</h2>
            </ul>
            <ul class="containerLabel">
                <h2 class="grnt small lft labelH">FinalPrice:</h2>
                <h2 class="grnt small lft labelH">${pageList.same.price * (100 - pageList.same.discount) / 100 }</h2>
            </ul>
        </div>
        <div>
            <ul class="containerLabel">
                <div class="lblH">DirectionCountryTags:</div>
                <c:forEach items="${pageList.same.direction.countryCollection}" var="cntr">
                    <li><ctg:StatusTag status="${cntr.status}" ifInvalid="greyA">${cntr.name}</ctg:StatusTag></li>
                </c:forEach>
            </ul>
            <ul class="containerLabel">
                <div class="lblH">DirectionCityTags:</div>
                <c:forEach items="${pageList.same.direction.cityCollection}" var="ct">
                    <li><ctg:StatusTag status="${ct.status}" ifInvalid="greyA">${ct.name}</ctg:StatusTag></li>
                </c:forEach>
            </ul>
            <ul class="containerLabelR">
                <div class="lblH">DirectionStayHotels:</div>
                <c:forEach items="${pageList.same.direction.stayCollection}" var="st">
                    <li><ctg:StatusTag status="${st.status}" ifInvalid="greyA">${st.hotel.name} ${st.hotel.stars}* (${st.hotel.city.name})</ctg:StatusTag></li>
                </c:forEach>
            </ul>
        </div>   
       
        <c:if test="${pageList.same.status == 1}">
            <input class="large green awesome" type="submit" value="<fmt:message key="buy" bundle="${ rb }" />" onclick="post('controller', {command: 'goBuyTour', currIdTour: ${pageList.same.idTour}}, 'POST')"/>
        </c:if>
    </ctg:PageTableTag>
</div>