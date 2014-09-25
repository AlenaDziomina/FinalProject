<%-- 
    Document   : countries
    Created on : 25.09.2014, 0:41:29
    Author     : User
--%>

<div id="main">
    URL: ${pageContext.request.requestURL}</br>
    Role: ${role}<br/>
    Balance: ${balance}</br>
    Language: ${language}</br>

    <div class="container">
        <select onclick="if(this.value)(fsrc(this.value))">
            <option value="<%=request.getContextPath()%>/images/italy.jpg">Italia</option>
            <option value="<%=request.getContextPath()%>/images/spain.jpg">Ispania</option>
            <option value="<%=request.getContextPath()%>/images/venecia.jpg">Venecia</option>
            <option value="<%=request.getContextPath()%>/images/piza.jpg">Piza</option>
        </select>

        
    </div>
    
    <img id="images" src="">
    
    
    
    <div id="textDiv"></div>
    
</div>
