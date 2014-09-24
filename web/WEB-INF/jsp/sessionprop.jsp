<%-- 
    Document   : sessionprop
    Created on : 08.09.2014, 18:32:15
    Author     : User
--%>


<body>

    </br>
    
    REFERER: ${referer}</br>
        Role: ${role}<br/>
        Balance: ${balance}</br>
        Language: ${language}</br>
        
    REFERER: ${referer}</br>
    Locale: ${locale}<br/>
    Lang: ${language}</br>
    USERTYPE: ${userType}<br/>
    Code: ${ pageContext.request.characterEncoding }<br/>
    Counter: ${counter}<br/>
    MaxInactiveInterval: ${pageContext.session.maxInactiveInterval}<br/>
    ID session: ${pageContext.session.id}<br/>
    <a href="index.jsp" class="menuHref">Back to index.jsp</a>
</body>

