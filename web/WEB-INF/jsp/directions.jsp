<%-- 
    Document   : directions
    Created on : 29.09.2014, 20:47:32
    Author     : Helena.Grouk
--%>

<div id="main">
    
    <ctg:RoleTag>
        <input type="checkbox" id="validDirectionStatus" name="status" value="1" class="boxMar" onchange="postDirections('controller', 'goShowDirections', 'POST')" />
            <fmt:message key="showValid" bundle="${ rb }" />
        <input type="checkbox" id="invalidDirectionStatus" name="status" value="0" class="boxMar" onchange="postDirections('controller', 'goShowDirections', 'POST')" />
            <fmt:message key="showInvalid" bundle="${ rb }" />
    </ctg:RoleTag>
    
    <ctg:PageTableTag rows="${ pageList.size }" pageNo="${pageList.currPageNo}" pages="${pageList.pages}">
        <ctg:ImgTag classImg="smallimg" idImg="images" nameImg="${pageList.next.picture}"/>
        <div class="padB">
            <ctg:StatusTag status="${pageList.same.status}" href="controller?command=showDirection&selectId=${pageList.same.idDirection}" ifValid="nodec cntr labelH" ifInvalid="nodec cntr grey labelH">
                ${pageList.same.name}
            </ctg:StatusTag>
        </div>
        <h2 class="small lft labelH">${pageList.same.text}</h2>

        <div>
            <ul class="containerLabel">
                <div class="lblH"><fmt:message key="directionCountryTags" bundle="${ rb }" />:</div>
                <c:forEach items="${pageList.same.countryCollection}" var="cntr">
                    <li><ctg:StatusTag status="${cntr.status}" ifInvalid="greyA"><fmt:message key="${cntr.name}" bundle="${ rb }" /></ctg:StatusTag></li>
                </c:forEach>
            </ul>
            <ul class="containerLabel">
                <div class="lblH"><fmt:message key="directionCityTags" bundle="${ rb }" />:</div>
                <c:forEach items="${pageList.same.cityCollection}" var="ct">
                    <li><ctg:StatusTag status="${ct.status}" ifInvalid="greyA"><fmt:message key="${ct.name}" bundle="${ rb }" /></ctg:StatusTag></li>
                </c:forEach>
            </ul>
            <ul class="containerLabelR">
                <div class="lblH"><fmt:message key="directionStayHotels" bundle="${ rb }" />:</div>
                <c:forEach items="${pageList.same.stayCollection}" var="st">
                    <ctg:StatusTag status="${st.hotel.status}" ifInvalid="greyA" role="ADMIN">
                        <li> ${st.hotel.name} ${st.hotel.stars} * 
                        <ctg:StatusTag status="${st.hotel.city.status}" ifInvalid="greyA" role="ADMIN">
                            ( <fmt:message key="${st.hotel.city.name}" bundle="${ rb }" /> )
                        </ctg:StatusTag> </li>
                    </ctg:StatusTag>
                </c:forEach>
            </ul>
        </div>      
    </ctg:PageTableTag>
        
    <script>
        boxChecking('validDirectionStatus', ${validDirectionStatus});
        boxChecking('invalidDirectionStatus', ${invalidDirectionStatus});
    </script>
    <ctg:RoleTag>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goCreateNewDirection" />
            <input class="centrale large green awesome" type="submit" value="<fmt:message key="newDirection" bundle="${ rb }" />"/>
        </form>
    </ctg:RoleTag>    
</div>
