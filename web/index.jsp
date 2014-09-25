<%-- 
    Document   : index
    Created on : 08.09.2014, 22:23:07
    Author     : User
--%>


<div id="main">
    URL: ${pageContext.request.requestURL}</br>
    Role: ${role}<br/>
    Balance: ${balance}</br>
    Language: ${language}</br>
    <input type="button" onclick="popup()" value="Click Me!"></br></br>

    <form action="controller" method="GET" name="indexForm">    
        <input type="hidden" name="command" value="getsession" />
        <input type="submit" value="GetSession">
    </form>
</div>
    