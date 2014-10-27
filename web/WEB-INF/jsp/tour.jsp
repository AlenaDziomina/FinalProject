<%-- 
    Document   : tour
    Created on : 09.10.2014, 0:37:32
    Author     : User
--%>

<div id="main">
    
    <div class="left inner column">
        <select id="currTour" class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showTour'}, 'POST'))">               
            <c:forEach items="${tourList}" var="row">
                <option class="menuHref" value="${row.idTour}">${row.departDate}</option>
            </c:forEach>
        </select>
        <c:if test="${currDirection.status == 1}">
            <ctg:RoleTag>
                <input class="large green awesome" type="submit" value="<fmt:message key="newTour" bundle="${ rb }" />" onclick="post('controller', {command: 'goCreateNewTour', currIdDirection: '${currDirection.idDirection}' }, 'POST')" />
            </ctg:RoleTag>
        </c:if>
    </div>
            
    <script type="text/javascript">
        select("currTour", ${currIdTour});
    </script> 
    
    <div class="full center inner column">
        <div class="parameterRow">
            <jsp:include page="/WEB-INF/other/statcalend.jsp" />
            <div class="mid input">
                <h1 class="labelH"><fmt:message key="date" bundle="${ rb }" />:</h1> 
            </div>
            <div class="input inner">
                <div class="inner">
                    <label class="inputLineContainer"><fmt:message key="from" bundle="${ rb }" /> ${departDate} <fmt:message key="to" bundle="${ rb }" /> ${arrivalDate}</label>
                </div>
            </div>
        </div>
        <div class="parameterRow">
            <div id="datepicker"></div>
        </div>
        
        <div class="parameterRow">
            <div class="mid input">
                <h1 class="labelH"><fmt:message key="price" bundle="${ rb }" />:</h1> 
            </div>
            <div class="input inner">
                <label class="inputLineContainer" name="price">${currTour.price}</label>
            </div>      
        </div>      
            
        <div class="parameterRow">
            <div class="mid input">
                <h1 class="labelH"><fmt:message key="discount" bundle="${ rb }" />: </h1> 
            </div>
            <div class="input inner">
                <label name="discount" class="inputLineContainer">${currTour.discount}</label>
            </div>      
        </div>      
            
        <div class="parameterRow">
            <div class="mid input">
                <h1 class="labelH"><fmt:message key="totalSeats" bundle="${ rb }" />: </h1> 
            </div>
            <div class="input inner">
                <label name="totalSeats" class="inputLineContainer">${currTour.totalSeats}</label>
            </div>      
        </div>             
            
        <div class="parameterRow">
            <div class="mid input">
                <h1 class="labelH"><fmt:message key="freeSeats" bundle="${ rb }" />: </h1> 
            </div>
            <div class="input inner">
                <label name="freeSeats" class="inputLineContainer">${currTour.freeSeats}</label>
            </div>      
        </div>   

        <c:if test="${currDirection.status == 1}">
            <ctg:RoleTag>
                <input class="large orange awesome" type="submit" value="<fmt:message key="editTour" bundle="${ rb }" />" onclick="post('controller', {command: 'goEditTour'}, 'POST')" />
                <input class="large red awesome" type="submit" value="<fmt:message key="deleteTour" bundle="${ rb }" />" onclick="post('controller', {command: 'DeleteTour'}, 'POST')"/>
            </ctg:RoleTag>
        </c:if>
    </div>
</div>
