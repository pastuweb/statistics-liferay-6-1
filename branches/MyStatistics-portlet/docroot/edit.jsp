<%
/**
 * Copyright (c) Pasturenzi Francesco
 * This is the form that you can see click on button "Preferences" of the Portlet
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<jsp:useBean id="saveSettingsStatisticsURL" class="java.lang.String" scope="request" />

<portlet:defineObjects />


<form id="<portlet:namespace/>accountForm" action="<%=saveSettingsStatisticsURL%>" method="POST">
	
	<div class="portlet-msg-alert">
	The <span style="color:#FF0000;">procedure</span> for the definition of statistics to be displayed is <span style="color:#FF0000;">fully Guided</span>.<br>
	</div>
	<hr>
	<br>
	
	
	<strong style="color:#000000;">Type of Statistics (*):</strong><br>
	<input type="radio" name="inTypeStatistics" value="PORTAL"/> PORTAL
	<input type="radio" name="inTypeStatistics" value="LOGGED USER"/> LOGGED USER	
	
	<br><br>
	<div id="modTypeDataView">
		<strong style="color:#000000;">Type of Data View (*):</strong><br>
		<input type="radio"  name="inTypeDataView" value="TABLE"/> TABLE
		<input type="radio"  name="inTypeDataView" value="CHARTS"/> CHARTS	
	</div>
	<br>
	
	<!-- checkbox for Table -->
	
	
	<div id="modListStatisticsTable">
		<strong style="color:#000000;">Choose the Statistics (*):</strong><br>
		<input type="checkbox" class="statTable inUserCreate" name="inUserCreate" value="false"/> <span class="inUserCreate">Count Users Create<br></span>
		<input type="checkbox" class="statTable" name="inRoles" value="false"/> Count Roles<br>
		<input type="checkbox" class="statTable" name="inBlogEntry" value="false"/> Count Blog Entry<br>
		<input type="checkbox" class="statTable" name="inWikiPage" value="false"/> Count Wiki Page<br>
		<input type="checkbox" class="statTable" name="inGroups" value="false"/> Count Groups<br>
		<input type="checkbox" class="statTable" name="inAssetEntry" value="false"/> Count Asset Entry<br>
		<input type="checkbox" class="statTable" name="inUsersLastLogin" value="false"/> Last Login<br>
		<input type="checkbox" class="statTable" name="inRatingsEntry" value="false"/> Count Ratings Entry<br>
		<input type="checkbox" class="statTable" name="inCalEvents" value="false"/> Count Calendar Events<br>
		<br>
	</div>
	
	
	<!-- checkbox/radio for Charts -->
	
	<div id="modTypeCharts">
		<strong style="color:#000000;">Type of Charts (*):</strong><br>
		<input type="radio"  name="inTypeCharts" value="LINE"/> <img src="<%=request.getContextPath() %>/images/line.png" style="width:25px;" alt="Line Chart" title="Line Chart"/>
		<input type="radio"  name="inTypeCharts" value="PIE"/> <img src="<%=request.getContextPath() %>/images/pie.png" style="width:25px;" alt="Pie Chart" title="Pie Chart"/>
		<input type="radio"  name="inTypeCharts" value="COLUMN"/> <img src="<%=request.getContextPath() %>/images/column.png" style="width:25px;" alt="Column Chart" title="Column Chart"/>
		<input type="radio"  name="inTypeCharts" value="BAR"/> <img src="<%=request.getContextPath() %>/images/bar.png" style="width:25px;" alt="Bar Chart" title="Bar Chart"/>
		<br><br>
		Width Chart:
		<select  name="inWidthChart">
			<option value="200">200 px</option>
			<option value="300">300 px</option>
			<option value="400">400 px</option>
			<option value="500">500 px</option>
			<option value="600">600 px</option>
			<option value="700">700 px</option>
			<option value="800">800 px</option>
			<option value="900">900 px</option>
		</select>
		<br><br>
		Height Chart:
		<select  name="inHeightChart">
			<option value="200">200 px</option>
			<option value="300">300 px</option>
			<option value="400">400 px</option>
			<option value="500">500 px</option>
			<option value="600">600 px</option>
			<option value="700">700 px</option>
			<option value="800">800 px</option>
			<option value="900">900 px</option>
		</select>
		<br><br>
	</div>	
	
	
	<div id="modTypeResults">
		<strong style="color:#000000;">Type of Results (*):</strong><br>
		<input type="radio"  name="inTypeResults" value="TOT"/> TOT<br>
		<br>
	</div>
	
	
	
	<div id="modTypePartition">
		<strong style="color:#000000;">Type of Partition Date (*):</strong><br>
		<input type="radio"  name="inTypePartition" value="DAY"/> Day
		<input type="radio"  name="inTypePartition" value="MONTH"/> Month
		<input type="radio"  name="inTypePartition" value="YEAR"/> Year
		<br><br>
	</div>
		
	<div id="modRangeDate">
		<strong>Date From (*)</strong><br>
		<input type="text" id="from" name="from"><br>
		<strong>Date To (*)</strong><br>
		<input type="text" id="to" name="to"><br>
		<br>
	</div>
	
	<div id="modListStatisticsChart">
		<strong style="color:#000000;">Choose the Statistics (*):</strong><br>
		<input type="radio" name="inChartStatistics" value="Users Create"/> Users Create<br>
		<input type="radio" name="inChartStatistics" value="Blogs Entry"/> Blogs Entry<br>
		<input type="radio" name="inChartStatistics" value="Wiki Pages"/> Wiki Pages<br>
		<input type="radio" name="inChartStatistics" value="Assetts Entry"/> Assetts Entry<br>
		<input type="radio" name="inChartStatistics" value="Ratings Entry"/> Ratings Entry<br>
		<input type="radio" name="inChartStatistics" value="Calendar Events"/> Calendar Events<br>
		<br>
	</div>
	
	<p style="text-align:left;" id="buttSubmit">
		<input type="submit" id="inviaSettingsStatisticsForm" title="Save" value="Save">
	</p>
	
	<hr>
	<div class="portlet-msg-info">
	For any other IDEAS possible useful statistics or simple IMPROVEMENTS or bugs: <a href="http://www.appuntivari.net/chi-siamo" target="_blank">francesco.pasturenzi@gmail.com</a>
	or you can find the source code on My Public GitHub Repository: <a href="https://github.com/pastuweb/java-liferay.appuntivari.net/tree/master/MyStatistics-portlet" target="_blank">MyStatistics-portlet</a>
	</div>
	<br>
</form>

<script type="text/javascript">		
		
		resetAll();
		$('input[name=inTypeStatistics]:radio').attr("checked", false);
		$('input[name=inTypeCharts]:radio').attr("checked", false);
		$('input[name=inTypeResults]:radio').attr("checked", false);
		$('input[name=inTypePartition]:radio').attr("checked", false);
	
		$('.statTable').removeAttr('checked');
		$('.statTable').val("false");
		$('input[name=inChartStatistics]:radio').attr("checked", false);
		
     	$('input[name=inTypeStatistics]:radio').on('change', function (e) {
     		resetAll();
     		$('input[name=inTypeCharts]:radio').attr("checked", false);
    		$('input[name=inTypeResults]:radio').attr("checked", false);
    		$('input[name=inTypePartition]:radio').attr("checked", false);
    		$('.statTable').removeAttr('checked');
    		$('.statTable').val("false");
    		$('input[name=inChartStatistics]:radio').attr("checked", false);
     		$('#modTypeDataView').show();
     		$('input[name=inTypeDataView]:radio').attr("checked", false);
     		
     		
     		if($(this).val() == "LOGGED USER"){
     			$(".inUserCreate").hide();
     		}else{
     			$(".inUserCreate").show();
     		}
     		
     		
     	});
     	
     	$('input[name=inTypeDataView]:radio').on('change', function (e) {
     		var value = $(this).val();
     		if(value=="TABLE"){
     			resetAll();
         		$('#modTypeDataView').show();
     			$('input[name=inTypeCharts]:radio').attr("checked", false);
     			$('input[name=inTypeResults]:radio').attr("checked", false);
     			$('input[name=inTypePartition]:radio').attr("checked", false);
     			$('.statTable').removeAttr('checked');
     			$('.statTable').val("false");
     			$('#modListStatisticsTable').show();    			
     			$('#buttSubmit').show();
     		}else if(value=="CHARTS"){
     			resetAll();
     			$('#modTypeDataView').show();
     			$('input[name=inTypeCharts]:radio').attr("checked", false);
     			$('input[name=inTypeResults]:radio').attr("checked", false);
     			$('input[name=inTypePartition]:radio').attr("checked", false);
     			$('input[name=inChartStatistics]:radio').attr("checked", false);
     			$('#modTypeCharts').show();
     		}
     	});
     	
     	
    	$('input[name=inTypeCharts]:radio').on('change', function (e) {
    		$('input[name=inTypeResults]:radio').attr("checked", false);
    		$('#modTypeResults').show();    		
    	});
    	
    	$('input[name=inTypeResults]:radio').on('change', function (e) {
    		$('input[name=inTypePartition]:radio').attr("checked", false);
    		$('#modTypePartition').show();
    	});
    	
    	$('input[name=inTypePartition]:radio').on('change', function (e) {
    		//view DatePicker for Date range
    		$('#modRangeDate').show();
    		$('#modListStatisticsChart').show();
    		$('#buttSubmit').show();
    	});
		
    	
    	
    	
    	$('.statTable').on('change', function (e) {
    		
      		if($(this).is(':checked')){
      			$(this).val("true");
      			$(this).attr('checked','checked');
      		}else{
      			$(this).val("false");
      			$(this).removeAttr('checked');
      		}
      	});
    	

     	function resetAll(){
     		
     		$('#modTypeDataView').hide();
    		$('#modListStatisticsTable').hide();
    		$('#modTypeCharts').hide();
    		$('#modTypeResults').hide();
    		$('#modTypePartition').hide();
    		$('#modRangeDate').hide();
    		$('#modListStatisticsChart').hide();
			    		
    		$('#buttSubmit').hide();
     	}
     	
     	
     	
</script>


  