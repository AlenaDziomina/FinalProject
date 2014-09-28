<%-- 
    Document   : edithotel
    Created on : 27.09.2014, 22:04:21
    Author     : User
--%>

<div id="main">
    
    <form id="updHotel" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactHotel" />
            
            <input type="hidden" name="id_country" value="$(currCountry.idCountry)"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Select country: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select class="selectContainer" size="1" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showCitiesOfCountry'}, 'POST'))">      
                            <option class="selectItem" value="0"> - Select - </option>
                            <c:forEach items="${countryList}" var="row">
                                <c:choose>
                                    <c:when test="${row.idCountry == currCountry.idCountry}">
                                        <option class="selectItem" value="${row.idCountry}" selected="true">${row.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option class="selectItem" value="${row.idCountry}">${row.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="id_city" value=""/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Select city: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select name="citySelection" class="selectContainer" size="1" onclick="if(this.value)(selectCity(this.value))">      
                            <option class="selectItem" value=""> - Select - </option>
                            <c:forEach items="${currCityList}" var="row">
                                <option class="selectItem" value="${row.idCity}">${row.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="id_hotel" value="${currHotel.idHotel}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Hotel name: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="name_hotel" value="${currHotel.name}"/>
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="stars_hotel" value=""/>        
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Select hotel stars: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select name="citySelection" class="selectContainer" size="1" onclick="if(this.value)(selectStars(this.value))">      
                            <option class="selectItem" value=""> - Select - </option>
                            <option class="selectItem" value="1"> 1* </option>
                            <option class="selectItem" value="2"> 2* </option>
                            <option class="selectItem" value="3"> 3* </option>
                            <option class="selectItem" value="4"> 4* </option>
                            <option class="selectItem" value="5"> 5* </option>
                        </select>
                    </div>
                </div>
            </div>        
            
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Hotel picture:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="picture_hotel"   value="${currHotel.picture}"/>
                    </div>
                </div>      
            </div>
            
            <input type="hidden" name="id_description" value="${currHotel.description.idDescription}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Hotel description:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <textarea name="text_description" class="inputMultilineineContainer">${currHotel.description.text}</textarea>
                    </div>
                </div>
            </div>
            
            <br/>
        
            <div class="parameterRow">
                <div class="centrale">
                    <input type="submit" value="Save"/>
                    <div id="erNote">${errorSaveData}</div>
                </div>
            </div>
            
        </div>
        
    </form>

</div>

