<%-- 
    Document   : newcountry
    Created on : 25.09.2014, 15:29:29
    Author     : User
--%>

<div id="main">
    
    <form name="updateCountry" method="POST" action="controller">
        <div class="innerColumn">
            <input type="hidden" name="command" value="redactCountry" />
            <input type="hidden" name="id_country" value="${currCountry.idCountry}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Country name: </h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="name_country" value="${currCountry.name}"/>
                    </div>
                </div>
            </div>
            
            
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Country picture:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <input type="text" class="inputLineContainer" name="picture_country"   value="${currCountry.picture}"/>
                    </div>
                </div>      
            </div>
            
            <input type="hidden" name="id_description" value="${currCountry.description.idDescription}"/>
            <div class="parameterRow">
                <div class="labelColumn">
                    <h1 class="labelH">Country description:</h1> 
                </div>
                <div class="inputColumn">
                    <div class="innerColumn">
                    <textarea name="text" class="inputMultilineineContainer">${currCountry.description.text}</textarea>
                    </div>
                </div>
            </div>
            
            <br/>
        
            <div class="parameterRow">
                
                    <input type="submit" value="Save"/>
                
            </div>
            
        </div>
        
    </form>

</div>
