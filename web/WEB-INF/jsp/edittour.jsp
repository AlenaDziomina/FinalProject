<%-- 
    Document   : edittour
    Created on : 08.10.2014, 2:02:21
    Author     : User
--%>


<div id="main">
    <form id="updTour" name="updateTour" method="POST" action="controller">
        <div class="inner">
            <input type="hidden" name="command" value="saveRedactTour" />
            <input type="hidden" name="currIdDirection" value="${currIdDirection}"/>
            <input type="hidden" name="currIdTour" value="${currTour.idTour}"/>
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH">DepartDate: </h1> 
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
                    <h1 class="labelH">Price:</h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="price" class="inputLineContainer" value="${currTour.price}"/>
                </div>      
            </div>      
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH">Discount: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="discount" class="inputLineContainer" value="${currTour.discount}"/>
                </div>      
            </div>      
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH">totalSeats: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" name="totalSeats" class="inputLineContainer" value="${currTour.totalSeats}"/>
                </div>      
            </div>             
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH">freeSeats: </h1> 
                </div>
                <div class="input inner">
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
