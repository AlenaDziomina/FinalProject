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
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goCreateNewTour" />
            <input type="hidden" name="currIdDirection" value="${currIdDirection}"/>
            <input class="large green awesome" type="submit" value="<fmt:message key="newTour" bundle="${ rb }" />"/>
        </form>
    </div>
            
    <script type="text/javascript">
        select("currTour", ${currIdTour});
    </script> 
    
    <div class="full center inner column">
        <div class="parameterRow">
            <jsp:include page="/WEB-INF/other/statcalend.jsp" />
            <div class="mid input">
                <h1 class="labelH">Date:</h1> 
            </div>
            <div class="input inner">
                <div class="inner">
                    <label class="inputLineContainer">from ${departDate} to ${arrivalDate}</label>
                </div>
            </div>
        </div>
        <div class="parameterRow">
            <div id="datepicker"></div>
        </div>
        
        <div class="parameterRow">
            <div class="mid input">
                <h1 class="labelH">Price:</h1> 
            </div>
            <div class="input inner">
                <label class="inputLineContainer" name="price">${currTour.price}</label>
            </div>      
        </div>      
            
        <div class="parameterRow">
            <div class="mid input">
                <h1 class="labelH">Discount: </h1> 
            </div>
            <div class="input inner">
                <label name="discount" class="inputLineContainer">${currTour.discount}</label>
            </div>      
        </div>      
            
        <div class="parameterRow">
            <div class="mid input">
                <h1 class="labelH">totalSeats: </h1> 
            </div>
            <div class="input inner">
                <label name="totalSeats" class="inputLineContainer">${currTour.totalSeats}</label>
            </div>      
        </div>             
            
        <div class="parameterRow">
            <div class="mid input">
                <h1 class="labelH">freeSeats: </h1> 
            </div>
            <div class="input inner">
                <label name="freeSeats" class="inputLineContainer">${currTour.freeSeats}</label>
            </div>      
        </div>   

        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goEditTour" />
            <input class="centrale large orange awesome" type="submit" value="<fmt:message key="editTour" bundle="${ rb }" />"/>
        </form>
    </div>
</div>
