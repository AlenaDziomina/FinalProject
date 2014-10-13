<%-- 
    Document   : edittour
    Created on : 08.10.2014, 2:02:21
    Author     : User
--%>


<div id="main">
    <form id="updTour" name="updateTour" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactTour" />
            <input type="hidden" name="currIdDirection" value="${currIdDirection}"/>
            <input type="hidden" name="currIdTour" value="${currTour.idTour}"/>
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">DepartDate: </h1> 
                </div>
                <div class="inputColumn innerColumn">
                    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/redmond/jquery-ui.css">
                    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
                    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
                    <link rel="stylesheet" href="/resources/demos/style.css">
                                                
                    <script>
                        $(function() {
                            $( "#from" ).datepicker({ numberOfMonths: 3, showButtonPanel: true, firstDay: 1, minDate: 1, dateFormat: "yy-mm-dd",
                                onClose: function( selectedDate ) {$( "#to" ).datepicker( "option", "minDate", selectedDate );}});
                            $( "#to" ).datepicker({ numberOfMonths: 3, showButtonPanel: true, firstDay: 1, minDate: 1, dateFormat: "yy-mm-dd",
                                onClose: function( selectedDate ) {$( "#from" ).datepicker( "option", "maxDate", selectedDate );}});
                            $( "#from" ).datepicker( "setDate", "${departDate}");
                            $( "#to" ).datepicker( "setDate", "${arrivalDate}");   
                            $( "#from" ).datepicker( "option", "dayNamesMin", [ 
                                "<fmt:message key="daySu" bundle="${ rb }" />", 
                                "<fmt:message key="dayMo" bundle="${ rb }" />", 
                                "<fmt:message key="dayTu" bundle="${ rb }" />",
                                "<fmt:message key="dayWe" bundle="${ rb }" />", 
                                "<fmt:message key="dayTh" bundle="${ rb }" />", 
                                "<fmt:message key="dayFr" bundle="${ rb }" />", 
                                "<fmt:message key="daySa" bundle="${ rb }" />" ] ); 
                            $( "#from" ).datepicker( "option", "monthNames", [ 
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
                            $( "#to" ).datepicker( "option", "dayNamesMin", [ 
                                "<fmt:message key="daySu" bundle="${ rb }" />", 
                                "<fmt:message key="dayMo" bundle="${ rb }" />", 
                                "<fmt:message key="dayTu" bundle="${ rb }" />",
                                "<fmt:message key="dayWe" bundle="${ rb }" />", 
                                "<fmt:message key="dayTh" bundle="${ rb }" />", 
                                "<fmt:message key="dayFr" bundle="${ rb }" />", 
                                "<fmt:message key="daySa" bundle="${ rb }" />" ] ); 
                            $( "#to" ).datepicker( "option", "monthNames", [ 
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
                    <label class="labelHRm"><fmt:message key="from" bundle="${ rb }" /></label>
                    <input type="text" id="from" name="departDate" class="inputLineContainer" >
                    <label class="labelHRm"><fmt:message key="to" bundle="${ rb }" />:</label>
                    <input type="text" id="to" name="arrivalDate" class="inputLineContainer" >
                </div>
            </div>
                    
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
                    <input type="text" name="discount" class="inputLineContainer" value="${currTour.discount}"/>
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
        
            <div class="parameterRow centrale">
                <input class="large magenta awesome" type="submit" value="<fmt:message key="save" bundle="${ rb }" />" />
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
            </div>
        </div>
    </form>
</div>
