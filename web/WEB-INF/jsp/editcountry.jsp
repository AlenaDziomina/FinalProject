<%-- 
    Document   : newcountry
    Created on : 25.09.2014, 15:29:29
    Author     : User
--%>

<div id="main">
    
    <form name="updateCountry" method="POST" action="controller">
        <input type="hidden" name="command" value="redactCountry" />
        <input type="hidden" name="id_country" value="${currCountry.idCountry}"/>
        Country name: <input type="text" name="name_country" value="">${currCountry.name}</form>
        <br/>
        Country picture: <input type="text" name="picture_country" value="">${currCountry.picture}</form>
        <br/>
        <input type="hidden" name="id_description" value="${currCountry.description.idDescription}"/>
        Country description: <input type="multitext" name="text" value="">${currCountry.description.text}</form>
        <br/>
        
        <input type="submit" value="Save"/>
        
    </form>

</div>
