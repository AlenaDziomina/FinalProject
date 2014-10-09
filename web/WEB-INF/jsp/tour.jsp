<%-- 
    Document   : tour
    Created on : 09.10.2014, 0:37:32
    Author     : User
--%>

<div id="main">
    
    <div class="leftColumn innerColumn">
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
    
    <div class="centerColumn innerColumn">
        <div class="parameterRow">
            <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/redmond/jquery-ui.css">
            <script src="//code.jquery.com/jquery-1.10.2.js"></script>
            <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
            <link rel="stylesheet" href="/resources/demos/style.css">
            <script>
            $(function() {
                $( "#datepicker" ).datepicker({numberOfMonths: 3, showButtonPanel: true, 
                    firstDay: 1, minDate: "${departDate}", maxDate: "${arrivalDate}", dateFormat: "yy-mm-dd"});
                
                $( "#datepicker" ).datepicker( "option", "dayNamesMin", [ 
                    "<fmt:message key="daySu" bundle="${ rb }" />", 
                    "<fmt:message key="dayMo" bundle="${ rb }" />", 
                    "<fmt:message key="dayTu" bundle="${ rb }" />",
                    "<fmt:message key="dayWe" bundle="${ rb }" />", 
                    "<fmt:message key="dayTh" bundle="${ rb }" />", 
                    "<fmt:message key="dayFr" bundle="${ rb }" />", 
                    "<fmt:message key="daySa" bundle="${ rb }" />" ] ); 
                $( "#datepicker" ).datepicker( "option", "monthNames", [ 
                    "<fmt:message key="January" bundle="${ rb }" />", 
                    "<fmt:message key="February" bundle="${ rb }" />", 
                    "<fmt:message key="March" bundle="${ rb }" />",
                    "<fmt:message key="April" bundle="${ rb }" />",
                    "<fmt:message key="May" bundle="${ rb }" />",
                    "<fmt:message key="June" bundle="${ rb }" />",
                    "<fmt:message key="July" bundle="${ rb }" />",
                    "<fmt:message key="August" bundle="${ rb }" />",
                    "<fmt:message key="September" bundle="${ rb }" />",
                    "<fmt:message key="October" bundle="${ rb }" />",
                    "<fmt:message key="November" bundle="${ rb }" />",
                    "<fmt:message key="December" bundle="${ rb }" />" ] );
            });
            </script>
            <div class="labelColumn">
                <h1 class="labelH">Date:</h1> 
            </div>
            <div class="inputColumn innerColumn">
                <div class="innerColumn">
                    <label class="inputLineContainer">from ${departDate} to ${arrivalDate}</label>
                </div>
                <div id="datepicker"></div>
            </div>
        </div>
        
        <div class="parameterRow">
            <div class="labelColumn">
                <h1 class="labelH">Price:</h1> 
            </div>
            <div class="inputColumn innerColumn">
                <label class="inputLineContainer" name="price">${currTour.price}</label>
            </div>      
        </div>      
            
        <div class="parameterRow">
            <div class="labelColumn">
                <h1 class="labelH">Discount: </h1> 
            </div>
            <div class="inputColumn innerColumn">
                <label name="discount" class="inputLineContainer">${currTour.discount}</label>
            </div>      
        </div>      
            
        <div class="parameterRow">
            <div class="labelColumn">
                <h1 class="labelH">totalSeats: </h1> 
            </div>
            <div class="inputColumn innerColumn">
                <label name="totalSeats" class="inputLineContainer">${currTour.totalSeats}</label>
            </div>      
        </div>             
            
        <div class="parameterRow">
            <div class="labelColumn">
                <h1 class="labelH">freeSeats: </h1> 
            </div>
            <div class="inputColumn innerColumn">
                <label name="freeSeats" class="inputLineContainer">${currTour.freeSeats}</label>
            </div>      
        </div>   

        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goEditTour" />
            <input class="innerColumn centrale large orange awesome" type="submit" value="<fmt:message key="editTour" bundle="${ rb }" />"/>
        </form>
    </div>
</div>
