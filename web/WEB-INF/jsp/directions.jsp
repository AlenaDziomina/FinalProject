<%-- 
    Document   : directions
    Created on : 29.09.2014, 20:47:32
    Author     : User
--%>

<div id="main">
    
    <ctg:PageTableTag rows="${ rw.size }" pageNo="${rw.currPageNo}" pages="${rw.pages}">
        <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${rw.next.picture}"/>
        <div class="padB">
            <a class="nodec cntr labelH" href="controller?command=showDirection&selectId=${rw.same.idDirection}">${rw.same.name}</a>
        </div>
        <h2 class="small lft labelH">${rw.same.text}</h2>

        <div>
            <ul class="containerLabel">
                <div class="lblH">DirectionCountryTags:</div>
                <c:forEach items="${rw.same.countryCollection}" var="cntr">
                    <li>${cntr.name}</li>
                </c:forEach>
            </ul>
            <ul class="containerLabel">
                <div class="lblH">DirectionCityTags:</div>
                <c:forEach items="${rw.same.cityCollection}" var="ct">
                    <li>${ct.name}</li>
                </c:forEach>
            </ul>
            <ul class="containerLabelR">
                <div class="lblH">DirectionStayHotels:</div>
                <c:forEach items="${rw.same.stayCollection}" var="st">
                    <li>${st.hotel.name} ${st.hotel.stars}* (${st.hotel.city.name}) </li>
                </c:forEach>
            </ul>
        </div>      
    </ctg:PageTableTag>
        
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="goCreateNewDirection" />
        <input class="centrale large green awesome" type="submit" value="<fmt:message key="newDirection" bundle="${ rb }" />"/>
    </form>

</div>
