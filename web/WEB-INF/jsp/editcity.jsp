<%-- 
    Document   : editcity
    Created on : 27.09.2014, 21:59:23
    Author     : User
--%>

<div id="main">
    
    <form id="updCity" name="updateCity" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="saveRedactCity" />
            
            <input type="hidden" name="id_country" value=""/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Select country: </h1>
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                        <select class="selectContainer" size="1" onclick="if(this.value)(selectCountry(this.value))">      
                            <option class="selectItem" value=""> - Select - </option>
                            <c:forEach items="${countryList}" var="row">
                                <option class="selectItem" value="${row.idCountry}">${row.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            
            <input type="hidden" name="id_city" value="${currCity.idCity}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">City name: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="name_city" value="${currCity.name}"/>
                    </div>
                </div>
            </div>
            
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">City picture:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="picture_city"   value="${currCity.picture}"/>
                    </div>
                </div>      
            </div>
            
            <input type="hidden" name="id_description" value="${currCity.description.idDescription}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">City description:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <textarea name="text_description" class="inputMultilineineContainer">${currCity.description.text}</textarea>
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

