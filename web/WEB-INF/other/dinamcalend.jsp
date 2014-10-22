<%-- 
    Document   : newjsp
    Created on : 22.10.2014, 9:40:52
    Author     : User
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resource.pagecontent" var="rb" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/redmond/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
    $(function() {
        $( "#dateFrom" ).datepicker({ numberOfMonths: 3, showButtonPanel: true, firstDay: 1, minDate: 1, dateFormat: "yy-mm-dd",
            onClose: function( selectedDate ) {$( "#dateTo" ).datepicker( "option", "minDate", selectedDate );}});
        $( "#dateTo" ).datepicker({ numberOfMonths: 3, showButtonPanel: true, firstDay: 1, minDate: 1, dateFormat: "yy-mm-dd",
            onClose: function( selectedDate ) {$( "#dateFrom" ).datepicker( "option", "maxDate", selectedDate );}});
        $( "#dateFrom" ).datepicker( "setDate", "${currDepartDateFrom}");
        $( "#dateTo" ).datepicker( "setDate", "${currDepartDateTo}");   
        $( "#dateFrom" ).datepicker( "option", "dayNamesMin", [ 
            "<fmt:message key="daySu" bundle="${ rb }" />", 
            "<fmt:message key="dayMo" bundle="${ rb }" />", 
            "<fmt:message key="dayTu" bundle="${ rb }" />",
            "<fmt:message key="dayWe" bundle="${ rb }" />", 
            "<fmt:message key="dayTh" bundle="${ rb }" />", 
            "<fmt:message key="dayFr" bundle="${ rb }" />", 
            "<fmt:message key="daySa" bundle="${ rb }" />" ] ); 
        $( "#dateFrom" ).datepicker( "option", "monthNames", [ 
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
        $( "#dateTo" ).datepicker( "option", "dayNamesMin", [ 
            "<fmt:message key="daySu" bundle="${ rb }" />", 
            "<fmt:message key="dayMo" bundle="${ rb }" />", 
            "<fmt:message key="dayTu" bundle="${ rb }" />",
            "<fmt:message key="dayWe" bundle="${ rb }" />", 
            "<fmt:message key="dayTh" bundle="${ rb }" />", 
            "<fmt:message key="dayFr" bundle="${ rb }" />", 
            "<fmt:message key="daySa" bundle="${ rb }" />" ] ); 
        $( "#dateTo" ).datepicker( "option", "monthNames", [ 
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
    });
</script>
