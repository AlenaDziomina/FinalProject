<%-- 
    Document   : editdirection
    Created on : 29.09.2014, 20:50:44
    Author     : User
--%>

<div id="main">
    
    <form id="updDirection" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactDirection" />
            
            
            <input type="hidden" name="id_country" value="${currCountry.idCountry}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectCountries" bundle="${ rb }" />: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="id_city" value="${currHotel.idCity}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="selectCities" bundle="${ rb }" />: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="id_hotel" value="${currHotel.idHotel}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH"><fmt:message key="hotelName" bundle="${ rb }" />: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        
                    </div>
                </div>
            </div>
            
            
        
            <div class="parameterRow">
                <div class="centrale">
                    <input type="submit" value="<fmt:message key="save" bundle="${ rb }" />"/>
                    <div id="erNote">${errorSaveData}</div>
                </div>
            </div>
            
        </div>
        
    </form>

</div>
