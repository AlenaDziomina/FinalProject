<%-- 
    Document   : tours
    Created on : 29.09.2014, 20:47:48
    Author     : User
--%>


    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/redmond/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script>
    $(function() {
        $( "#datepicker" ).datepicker({numberOfMonths: 3, showButtonPanel: true});
        $( "#datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
        $( "#datepicker" ).datepicker( "option", "dayNamesMin", [ 
            "<fmt:message key="daySu" bundle="${ rb }" />", 
            "<fmt:message key="dayMo" bundle="${ rb }" />", 
            "<fmt:message key="dayTu" bundle="${ rb }" />",
            "<fmt:message key="dayWe" bundle="${ rb }" />", 
            "<fmt:message key="dayTh" bundle="${ rb }" />", 
            "<fmt:message key="dayFr" bundle="${ rb }" />", 
            "<fmt:message key="daySa" bundle="${ rb }" />" ] ); 
        $( "#datepicker" ).datepicker( "option", "monthNames", [ 
            "<fmt:message key="January" bundle="${ rb }" />", 
            "<fmt:message key="February" bundle="${ rb }" />", 
            "<fmt:message key="March" bundle="${ rb }" />",
            "<fmt:message key="April" bundle="${ rb }" />",
            "<fmt:message key="May" bundle="${ rb }" />",
            "<fmt:message key="June" bundle="${ rb }" />",
            "<fmt:message key="July" bundle="${ rb }" />",
            "<fmt:message key="August" bundle="${ rb }" />",
            "<fmt:message key="September" bundle="${ rb }" />",
            "<fmt:message key="October" bundle="${ rb }" />",
            "<fmt:message key="November" bundle="${ rb }" />",
            "<fmt:message key="December" bundle="${ rb }" />" ] );
        $( "#datepicker" ).datepicker( "option", "firstDay", 1 );
        $( "#datepicker" ).datepicker( "option", "minDate", 1 );
        

    });
    </script>
    
   

    <p>Date: <input type="text" id="datepicker" size="30"></p>
    
 

 
<script>
  $(function() {
    $( "#from" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#to" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( "#to" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
  });
  </script>

<label for="from">From</label>
<input type="text" id="from" name="from">
<label for="to">to</label>
<input type="text" id="to" name="to">
 
</body>
</html>