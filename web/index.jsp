<%-- 
    Document   : index
    Created on : 08.09.2014, 22:23:07
    Author     : User
--%>


<div id="main">
    
    <div class="inner">
        <p class="banner"><ctg:ImgTag classImg="gifBanner" idImg="banner" nameImg="/gif/animationFinalProject.gif"/></p>
        <h2 class="blu small cntr labelH"><fmt:message key="welcomeInfo1" bundle="${ rb }"/></h2>
        <h2 class="blu small cntr labelH"><fmt:message key="welcomeInfo2" bundle="${ rb }"/></h2>
        <h2 class="blu small cntr labelH"><fmt:message key="welcomeInfo3" bundle="${ rb }"/></h2>
        <h1 class="cntr labelH"><fmt:message key="welcomeTitle" bundle="${ rb }"/></h1>
    
    <form action="controller" method="GET" name="indexForm">    
        <input type="hidden" name="command" value="getsession" />
        <input class="large blue awesome" type="submit" value="<fmt:message key="getSession" bundle="${ rb }" />">
    </form>
    </div>
</div>
    