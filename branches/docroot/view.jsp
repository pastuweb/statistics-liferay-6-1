<%
/**
 * Copyright (c) Pasturenzi Francesco
 * This is the VIEW of the Portlet.
 */
%>



<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="com.appuntivarinet.ChartPoints" %>
<%@ page import="com.appuntivarinet.ChartDatas" %>
<%@ page import="com.appuntivarinet.Utils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean id="typeStatistics" class="java.lang.String" scope="request" />
<jsp:useBean id="typeDataView" class="java.lang.String" scope="request" />

<jsp:useBean id="typeCharts" class="java.lang.String" scope="request" />
<jsp:useBean id="typeResults" class="java.lang.String" scope="request" />
<jsp:useBean id="typePartition" class="java.lang.String" scope="request" />
<jsp:useBean id="dateRangeFrom" class="java.lang.String" scope="request" />
<jsp:useBean id="dateRangeTo" class="java.lang.String" scope="request" />
<jsp:useBean id="chartStatistics" class="java.lang.String" scope="request" />
<jsp:useBean id="widthChart" class="java.lang.String" scope="request" />
<jsp:useBean id="heightChart" class="java.lang.String" scope="request" />

<jsp:useBean id="tableDatas" class="com.appuntivarinet.TableDatas" scope="request" />
<jsp:useBean id="chartDatas" class="com.appuntivarinet.ChartDatas" scope="request" />

<jsp:useBean id="notDefinedMessage" class="java.lang.String" scope="request" />

<portlet:defineObjects />

<div style="position:relative;margin:auto;width:100%;padding:5px;">
	
	<div style="text-align:center;font-size:20px;">
		<strong>Statistics</strong>
	</div>
	<br>
	<%if(notDefinedMessage.equals("")){ %>
		<div style="margin:auto;">
			<p>
			<strong>Type of Statistics :</strong> <span style="color:#000CFF;"><%=typeStatistics %></span><br>
			<strong>Type of Data View :</strong> <span style="color:#000CFF;"><%=typeDataView %></span>
			</p>
			<hr>
			<br>
			<%if(typeDataView.equals("TABLE")){ %>
				<%if(tableDatas.getCountUserCreate() != -1){ %>
					<strong>Number of User Creates :</strong> <span style="color:#000CFF;"><%=tableDatas.getCountUserCreate() %></span><br>
				<%} %>
				<%if(tableDatas.getCountRoles() != -1){ %>
					<strong>Number of Roles :</strong> <span style="color:#000CFF;"><%=tableDatas.getCountRoles() %></span><br>
				<%} %>
				<%if(tableDatas.getCountBlogsEntry() != -1){ %>
					<strong>Number of Blogs entry :</strong> <span style="color:#000CFF;"><%=tableDatas.getCountBlogsEntry() %></span><br>
				<%} %>
				<%if(tableDatas.getCountWikiesPage() != -1){ %>
					<strong>Number of Wiki Pages :</strong> <span style="color:#000CFF;"><%=tableDatas.getCountWikiesPage() %></span><br>
				<%} %>
				<%if(tableDatas.getCountGroups() != -1){ %>
					<strong>Number of Groups :</strong> <span style="color:#000CFF;"><%=tableDatas.getCountGroups() %></span><br>
				<%} %>
				<%if(tableDatas.getCountAssetsEntry() != -1){ %>
					<strong>Number of Asset Entry :</strong> <span style="color:#000CFF;"><%=tableDatas.getCountAssetsEntry() %></span><br>
				<%} %>
				<%if(!tableDatas.getLastLogin().equals("")){ %>
					<strong>Last Login :</strong> <span style="color:#000CFF;"><%=tableDatas.getLastLogin() %></span><br>
				<%} %>
				<%if(tableDatas.getCountRatingsEntry() != -1){ %>
					<strong>Number of Ratings Entry :</strong> <span style="color:#000CFF;"><%=tableDatas.getCountRatingsEntry() %></span><br>
				<%} %>
				<%if(tableDatas.getCountCalEvents() != -1){ %>
					<strong>Number of Calendar Events :</strong> <span style="color:#000CFF;"><%=tableDatas.getCountCalEvents() %></span><br>
				<%} %>
				<br>
			<%} %>
			
			<%if(typeDataView.equals("CHARTS")){ %>
				<p>
				<strong>Type of Chart :</strong> <span style="color:#000CFF;"><%=typeCharts %></span><br>
				<strong>Type of Results :</strong> <span style="color:#000CFF;"><%=typeResults %></span><br>
				<strong>Datas in the chart:</strong> <span style="color:#000CFF;"><%=chartStatistics %></span><br>
				<strong>Partition By:</strong> <span style="color:#000CFF;"><%=typePartition %></span><br>
				<strong>Date range:</strong> from <span style="color:#000CFF;"><%=dateRangeFrom %></span> to <span style="color:#000CFF;"><%=dateRangeTo %></span>
				</p>
				<div style="text-align:center;width:100%;margin:auto;">
					<div id="chart_div" style="width:<%=widthChart%>px;height:<%=heightChart %>;margin:auto;background-color:transparent;"></div>
				</div>
				<div id="messageNoDataChart" class="portlet-msg-alert" style="margin:auto;text-align:center;font-size:16px;color:#FF0000;display:none;">
					No Data in the Chart.
				</div>
				<br>
			<%} %>
		</div>
	<%}else{ %>
		<div class="portlet-msg-alert" style="margin:auto;text-align:center;font-size:16px;color:#FF0000;">
			<%=notDefinedMessage %>
		</div>
		<br>
	<%} %>


	<div style="text-align:left;">
		<a href="http://www.pastuweb.com" target="_blank">
		<img src="http://www.pastuweb.com/loghi_pw/icone/pastuweb.png" width="30" alt="Creato da pastuweb.com" title="Creato da pastuweb.com" />
		</a>
	</div>

</div>

<%if(typeDataView.equals("CHARTS") &&  chartDatas.getPointList().size() > 0){ %>
<script type="text/javascript">
	$('#messageNoDataChart').hide();
	$('#chart_div').show();

	<%if(typeCharts.equals("PIE")){ %>
	function drawChart() {
	
	    var data = new google.visualization.DataTable();
	    data.addColumn('string', '<%=typePartition%>');
	    data.addColumn('number', '<%=typeResults%>');
	    data.addRows([
			<%
			for(ChartPoints point: chartDatas.getPointList()){
			%>
			 ["<%=point.getX()%>", <%=point.getY()%>],
			<%
			}
			%>
	    ]);
	
	    
	    var options = {title:'<%=typeResults%> <%=chartStatistics %>',
	    			   is3D: true,
	    			   pieHole: 0.4,
	    			   backgroundColor:'transparent',
	                   width:<%=widthChart%>,
	                   height:<%=heightChart %>,
	                   legend:{position: 'top', textStyle: {color: 'black', fontSize: 12}},
	                   chartArea:{top:40,width:"<%=widthChart%>",height:"<%=heightChart %>"}
	    };
	    
	
	    
	    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	    chart.draw(data, options);
	}
	<%} %>
	
	
	<%if(typeCharts.equals("COLUMN")){ %>
	function drawChart() {
	
	    var data = new google.visualization.DataTable();
	    data.addColumn('string', '<%=typePartition%>');
	    data.addColumn('number', '<%=typeResults%>');
	    data.addRows([
			<%
			for(ChartPoints point: chartDatas.getPointList()){
			%>
			 ["<%=point.getX()%>", <%=point.getY()%>],
			<%
			}
			%>
	    ]);
	
	    var newHeightChart = ""+parseInt("<%=heightChart%>")-70;
	    
	    var options = {title:'<%=typeResults%> <%=chartStatistics %>',
	    			   backgroundColor:'transparent',
	    			   hAxis: {title: 'Time', titleTextStyle: {color: 'red'}},
	    			   width:<%=widthChart%>,
	                   height:<%=heightChart %>,
	                   legend:{position: 'top', textStyle: {color: 'black', fontSize: 12}},
	                   chartArea:{top:40,left:40,width:"<%=widthChart%>",height:newHeightChart}
	    };
	
	    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	    chart.draw(data, options);
	}
	<%} %>
	
	<%if(typeCharts.equals("LINE")){ %>
	function drawChart() {
	
	    var data = new google.visualization.DataTable();
	    data.addColumn('string', '<%=typePartition%>');
	    data.addColumn('number', '<%=typeResults%>');
	    data.addRows([
			<%
			for(ChartPoints point: chartDatas.getPointList()){
			%>
			 ["<%=point.getX()%>", <%=point.getY()%>],
			<%
			}
			%>
	    ]);
		
	    var newHeightChart = ""+parseInt("<%=heightChart%>")-70;
	    
	    var options = {title:'<%=typeResults%> <%=chartStatistics %>',
	    			   backgroundColor:'transparent',
	    			   hAxis: {title: 'Time', titleTextStyle: {color: 'red'}},
	    			   pointSize: 3,
	    			   width:<%=widthChart%>,
	                   height:<%=heightChart %>,
	                   legend:{position: 'top', textStyle: {color: 'black', fontSize: 12}},
	                   chartArea:{top:40,left:40,width:"<%=widthChart%>",height:newHeightChart}
	               
	    };
	
	    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	    chart.draw(data, options);
	}
	<%} %>
	
	
	<%if(typeCharts.equals("BAR")){ %>
	function drawChart() {
	
	    var data = new google.visualization.DataTable();
	    data.addColumn('string', '<%=typePartition%>');
	    data.addColumn('number', '<%=typeResults%>');
	    data.addRows([
			<%
			for(ChartPoints point: chartDatas.getPointList()){
			%>
			 ["<%=point.getX()%>", <%=point.getY()%>],
			<%
			}
			%>
	    ]);
		
	    var newHeightChart = ""+parseInt("<%=heightChart%>")-70;
	    
	    var options = {title:'<%=typeResults%> <%=chartStatistics %>',
	    			   backgroundColor:'transparent',
	    			   vAxis: {title: 'Time',   titleTextStyle: {color: 'red'}},
	    			   width:<%=widthChart%>,
	                   height:<%=heightChart %>,
	                   legend:{position: 'top', textStyle: {color: 'black', fontSize: 12}},
	                   chartArea:{top:40,left:100,width:"<%=widthChart%>",height:newHeightChart}
	                   
	    };
	    
	    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
	    chart.draw(data, options);
	}
	<%} %>
	
	
	google.setOnLoadCallback(drawChart);
</script>
<%}else{%>
	<script type="text/javascript">
	$('#messageNoDataChart').show();
	$('#chart_div').hide();
	</script>
<%}%>
