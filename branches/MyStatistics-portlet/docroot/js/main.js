 google.load('visualization', '1.0', {'packages':['corechart']});
 
 $( "#from" ).datepicker({
     defaultDate: "+1w",
     dateFormat: 'dd-mm-yy',
     changeMonth: true,
     numberOfMonths: 3,
     onClose: function( selectedDate ) {
       $( "#to" ).datepicker( "option", "minDate", selectedDate );
     }
   });
   
   $( "#to" ).datepicker({
     defaultDate: "+1w",
     dateFormat: 'dd-mm-yy',
     changeMonth: true,
     numberOfMonths: 3,
     onClose: function( selectedDate ) {
       $( "#from" ).datepicker( "option", "maxDate", selectedDate );
     }
   });