<%-- 
    Document   : tour
    Created on : 09.10.2014, 0:37:32
    Author     : User
--%>

<div id="main">
    
    <div class="leftColumn innerColumn">
        <select id="currTour" class="container" size="15" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showTour'}, 'POST'))">               
            <c:forEach items="${tourList}" var="row">
                <option class="menuHref" value="${row.idTour}"><fmt:message key="${row.departDate}" bundle="${ rb }"/></option>
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
    
    <div class="centerColumn innerColumn">
        <div class="parameterRow">
            <div class="labelColumn">
                <h1 class="labelH">Price:</h1> 
            </div>
            <div class="inputColumn innerColumn">
                <input type="text" name="price" class="inputLineContainer" value="${currTour.price}"/>
            </div>      
        </div>      
            
        <div class="parameterRow">
            <div class="labelColumn">
                <h1 class="labelH">Discount: </h1> 
            </div>
            <div class="inputColumn innerColumn">
                <input type="text" name="discount" class="inputLineContainer" value="${currTour.discount}" disabled="true"/>
            </div>      
        </div>      
            
        <div class="parameterRow">
            <div class="labelColumn">
                <h1 class="labelH">totalSeats: </h1> 
            </div>
            <div class="inputColumn innerColumn">
                <input type="text" name="totalSeats" class="inputLineContainer" value="${currTour.totalSeats}"/>
            </div>      
        </div>             
            
        <div class="parameterRow">
            <div class="labelColumn">
                <h1 class="labelH">freeSeats: </h1> 
            </div>
            <div class="inputColumn innerColumn">
                <input type="text" name="freeSeats" class="inputLineContainer" value="${currTour.freeSeats}"/>
            </div>      
        </div>   

        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goEditTour" />
            <input class="innerColumn centrale large orange awesome" type="submit" value="<fmt:message key="editTour" bundle="${ rb }" />"/>
        </form>
    </div>
</div>
