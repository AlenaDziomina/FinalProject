<%-- 
    Document   : edittour
    Created on : 08.10.2014, 2:02:21
    Author     : Helena.Grouk
--%>


<div id="main">
    <form id="updTour" name="updateTour" method="POST" action="controller" onsubmit="return validateTourForm();">
        <div class="inner">
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="departDate" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <jsp:include page="/WEB-INF/other/selctcalend.jsp" />
                    <label class="small padR labelH"><fmt:message key="from" bundle="${ rb }" /></label>
                    <input type="text" id="from" name="departDate" class="inputLineContainer" >
                    <label class="small padR labelH"><fmt:message key="to" bundle="${ rb }" />:</label>
                    <input type="text" id="to" name="arrivalDate" class="inputLineContainer" >
                </div>
            </div>
                    
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="price" bundle="${ rb }" />:</h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="price" class="inputLineContainer" value="${currTour.price}"/>
                    <div id="erNote"><a id="priceErrMsg" hidden="true"><fmt:message key="errorPrice" bundle="${ rb }" /></a></div>
                </div>      
            </div>      
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="discount" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="discount" class="inputLineContainer" value="${currTour.discount}"/>
                    <div id="erNote"><a id="discountErrMsg" hidden="true"><fmt:message key="errorDiscount" bundle="${ rb }" /></a></div>
                </div>      
            </div>      
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="totalSeats" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="totalSeats" class="inputLineContainer" value="${currTour.totalSeats}"/>
                </div>      
            </div>             
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="freeSeats" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="freeSeats" class="inputLineContainer" value="${currTour.freeSeats}"/>
                </div>      
            </div>   
        
                
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" onclick="saveAllTour('saveRedactTour')" />
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
            </div>
        </div>
    </form>
</div>
            
            
<c:forEach items="${tourList}" var="row">
        <div class="parameterRowB">
            
            <img class="smallimg" id="images" src="<%=request.getContextPath()%>${row.direction.picture}">
            <div class="padB">
                <a class="nodec cntr labelH" href="controller?command=showDirection&selectId=${row.direction.idDirection}">${row.direction.name}   ${row.departDate}   (${row.daysCount} days)</a>
            </div>
            <h2 class="small lft labelH">${row.direction.text}</h2>
            <div>
                <ul class="containerLabel">
                    <div class="lblH">tourType:</div>
                    ${row.direction.tourType.nameTourType}
                </ul>
                <ul class="containerLabel">
                    <div class="lblH">transMode:</div>
                    ${row.direction.transMode.nameMode} 
                </ul>
                <ul class="containerLabel">
                    <div class="lblH">FreeSeats:</div>
                    ${row.freeSeats}
                </ul>
                <ul class="containerLabel">
                    <h2 class="small lft labelH">Price:</h2>
                    <h2 class="small lft labelH">${row.price}</h2>
                </ul>
                <ul class="containerLabel">
                    <h2 class="blu small lft labelH">Discount:</h2>
                    <h2 class="blu small lft labelH">${row.discount}</h2>
                </ul>
                <ul class="containerLabel">
                    <h2 class="grnt small lft labelH">FinalPrice:</h2>
                    <h2 class="grnt small lft labelH">${row.price * (100 - row.discount) / 100 }</h2>
                </ul>
                
            </div>
            
            <div>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCountryTags:</div>
                    <c:forEach items="${row.direction.countryCollection}" var="cntr">
                        <li>${cntr.name}</li>
                    </c:forEach>
                </ul>
                <ul class="containerLabel">
                    <div class="lblH">DirectionCityTags:</div>
                    <c:forEach items="${row.direction.cityCollection}" var="ct">
                        <li>${ct.name}</li>
                    </c:forEach>
                </ul>
                <ul class="containerLabelR">
                    <div class="lblH">DirectionStayHotels:</div>
                    <c:forEach items="${row.direction.stayCollection}" var="st">
                        <li>${st.hotel.name} ${st.hotel.stars}* (${st.hotel.city.name}) </li>
                    </c:forEach>
                </ul>
            </div>     
                
            <div class="parameterRow centrale">
                <input type="hidden" id="currIdTour" value="${row.idTour}"/>
                <input class="large green awesome" type="submit" value="<fmt:message key="buy" bundle="${ rb }" />" onclick="buyTour('controller', 'buyTour', 'POST')"/>
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
            </div>  
        </div>
    </c:forEach>
