<%-- 
    Document   : edittour
    Created on : 08.10.2014, 2:02:21
    Author     : User
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
                    <input type="text" id="from" name="departDate" class="inputLineContainer" />
                    <label class="small padR labelH"><fmt:message key="to" bundle="${ rb }" />:</label>
                    <input type="text" id="to" name="arrivalDate" class="inputLineContainer" />
                </div>
            </div>
                    
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="price" bundle="${ rb }" />:</h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="price" name="price" class="inputLineContainer" value="${currTour.price}"/>
                    <div id="erNote"><a id="priceErrMsg" hidden="true"><fmt:message key="errorPrice" bundle="${ rb }" /></a></div>
                </div>      
            </div>      
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="discount" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="discount" name="discount" class="inputLineContainer" value="${currTour.discount}"/>
                    <div id="erNote"><a id="discountErrMsg" hidden="true"><fmt:message key="errorDiscount" bundle="${ rb }" /></a></div>
                </div>      
            </div>      
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="totalSeats" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="totalSeats" name="totalSeats" class="inputLineContainer" value="${currTour.totalSeats}"/>
                    <div id="erNote"><a id="totalSeatsErrMsg" hidden="true"><fmt:message key="errorTotalSeats" bundle="${ rb }" /></a></div>
                </div>      
            </div>             
            
            <div class="parameterRow">
                <div class="mid input">
                    <h1 class="labelH"><fmt:message key="freeSeats" bundle="${ rb }" />: </h1> 
                </div>
                <div class="input inner">
                    <input type="text" id="freeSeats" name="freeSeats" class="inputLineContainer" value="${currTour.freeSeats}"/>
                    <div id="erNote"><a id="freeSeatsErrMsg" hidden="true"><fmt:message key="errorFreeSeats" bundle="${ rb }" /></a></div>
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
