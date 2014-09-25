<%-- 
    Document   : countries
    Created on : 25.09.2014, 0:41:29
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
    
    <div class="leftColumn">
        <div class="innerColumn">
            <select class="container" size="4" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showCountry'}, 'POST'))">               
                <c:forEach items="${countryList}" var="row">
                    <option class="menuHref" value="${row.idCountry}">${row.name}</option>
                </c:forEach>
            </select>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goCreateNewCountry" />
                <input type="submit" value="New Country"/>
            </form>
            
       </div>
    </div>
    
    <div class="centerColumn">
        <div class="innerColumn">
             <div id="erNote">${errorGetListMessage}</div>
        <img class="currimg" id="images" src="<%=request.getContextPath()%>${currCountry.picture}">
        <div class="cueetext">
             ${currCountry.description.text}
        </div>
        
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goEditCountry" />
            <input type="submit" value="Edit Country"/>
        </form>
        
        <div id="textDiv"></div>
        <div id="textDiv2"></div>
        </div>
    </div>
        
    <div class="rigthColumn">
        <div class="innerColumn">
            <select class="container" size="4" onclick="if(this.value)(post('controller', {selectId: this.value, command: 'showCountry'}, 'POST'))">               
                <c:forEach items="${currCountry.cityCollection}" var="row">
                    <option class="menuHref" value="${row.idCity}">${row.name}</option>
                </c:forEach>
            </select>
       </div>
    </div>
        
    
    
    
    
</div>
