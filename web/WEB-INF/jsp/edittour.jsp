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
                <div class="inputColumn">
                    <div class="innerColumn">
                        
                        <input type="text" name="departDate" class="inputLineContainer" value="${currTour.departDate}"/>
                        <input type="date" name="depDate" class="inputLineContainer" value="${currTour.departDate}"/>
 
                    </div>
                </div>
            </div>
            
            
                        
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">DaysCount:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text" name="daysCount" class="inputLineContainer" value="${currTour.daysCount}"/>
                    </div>
                </div>      
            </div>
                    
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Price:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text" name="price" class="inputLineContainer" value="${currTour.price}"/>
                    </div>
                </div>      
            </div>      
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Discount: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text" name="discount" class="inputLineContainer" value="${currTour.discount}"/>
                    </div>
                </div>      
            </div>      
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">totalSeats: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text" name="totalSeats" class="inputLineContainer" value="${currTour.totalSeats}"/>
                    </div>
                </div>      
            </div>             
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">freeSeats: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <input type="text" name="freeSeats" class="inputLineContainer" value="${currTour.freeSeats}"/>
                    </div>
                </div>      
            </div>   
            
        
            <div class="parameterRow">
                <div class="centrale">
                    <input type="submit" value="<fmt:message key="save" bundle="${ rb }" />" />
                    <div id="erNote">${errorSaveData}</div>
                    <div id="erNote">${errorReason}</div>
                    <div id="erAdminNote">${errorAdminMsg}</div>
                </div>
            </div>
            
        </div>
        
    </form>

</div>
