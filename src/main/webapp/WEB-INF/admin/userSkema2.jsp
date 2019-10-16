<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>BastXBast</title>
<!-- link rel="Stylesheet" type="text/css" href="css/main.css"  /> -->
<!--  link rel="stylesheet" type="text/css" href="css/mycss.css"/-->
<link rel="Stylesheet" type="text/css"
	href="/css/smoothness/jquery-ui-1.8.16.custom.css" />
<script type="text/javascript" src="/js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.8.16.custom.min.js"></script>

<script src="/js/grid.locale-en.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/jquery.jqGrid.min.js"></script>
<link rel="stylesheet" href="/css/ui-jqgrid.css" type="text/css" />
<script type="text/javascript" src="/js/login.js"></script>
<link rel="shortcut icon" type="images/x-icon"
	href="/css/smoothness/images/logo.ico" />

<script type="text/javascript">
var count1 = 1;
var rowsToColorSkema1 = [];
var rowsToColor1Skema1 = [];
function rowColorFormatterSkema1(cellValue, options, rowObject) {
    if (cellValue == "WIN")
        rowsToColorSkema1[rowsToColorSkema1.length] = options.rowId;
    else
    	rowsToColor1Skema1[rowsToColor1Skema1.length] = options.rowId;
    return cellValue;
}

function addInput1(){
	if(count1 < 10) {
		count1 = count1+1;
		$('#row'+count1).removeAttr("style");
		$('#ta'+count1).removeAttr("style");
		$('#s'+count1).removeAttr("style");
		$('#sh'+count1).removeAttr("style");
		$('#count').val(count1);
	}	
}

function removeInput1(){
	if(count1 > 1) {
		$('#row'+count1).attr("style", "display:none");
		$('#ta'+count1).attr("style", "display:none");
		$('#ta'+count1).val("");
		$('#s'+count1).attr("style", "display:none");
		$('#sh'+count1).attr("style", "display:none");
		$('#sh'+count1).val("1");
		count1 = count1-1;
		$('#count').val(count1);
	}	
}
	$(function() {

		$("input:submit", ".bottoni").button();
		$("#tabs").tabs();

		$("#list_skema1").jqGrid(
				{
					url : '/manager/json/get-bets2.action?SkemaID='+<s:property value="#session.skema2ID"/>,
					datatype : "json",
					colNames : [ 'Bet', "Played",
							"", "Cash". ""],
					colModel : [ {
						name : 'positionInSkema',
						index : 'positionInSkema',
						hidden : false,
						width : 50,
						editable : false
					}, {
						name : 'playedAmount',
						index : 'playedAmount',
						width : 50,
						editable : false
					}, {
						name : 'result',
						index : 'result',
						width : 50,
						hidden: true,
						editable : false,
						formatter : rowColorFormatterSkema1
					}, {
						name : 'skemaCurrentCashAfterResult',
						index : 'skemaCurrentCashAfterResult',
						width : 150,
						editable : false
					}, {
						name : 'IDbet',
						index : 'IDbet',
						width : 50,
						hidden: false,
						editable : false,
						formatter : link
					},],
					jsonReader : {
						root : "bets",
						repeatitems : false,
						cell : "",
						id : "0"
					},
					rowNum : 5,
					rowList : [ 5, 10, 15 ],
					pager : '#pager_skema1',
					sortname : 'positionInSkema',
					viewrecords : true,
					sortorder : "asc",
					caption : "Bets",
					loadComplete: function () {
						
		               for (var i = 0; i < rowsToColorSkema1.length; i++) {
		                    
		                        $("#" + rowsToColorSkema1[i]).find("td").css({'background':'green'});
		                    
		                }
		                for (var i = 0; i < rowsToColor1Skema1.length; i++) {
		                    
		                        $("#" + rowsToColor1Skema1[i]).find("td").css({'background':'red'});
		                    
		                }
		             }

				});
		
		                        function link(cellvalue, options, rowObject) {
		                			if (cellvalue != null)
		                				return "<a target='_blank' href='/admin/get-bet-page?IDbet="
		                						+ cellvalue + "'>Details</a>";
		                			else
		                				return '';
		                		}
		
	});
		
</script>



<style>
body {
	font-family: "Trebuchet MS", sans-serif;
}

#icons {
	margin: 0;
	padding: 0;
}

#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}

.ui-tabs {
	position: absolute;
	top: 0px;
	bottom: 0px;
	margin-top: 50px;
	margin-left: 5%;
	margin-right: 5%;
	height: 100%;
	width: 90%;
}

.ui-tabs-panel {
	height: 100%;
	overflow-y: auto;
}
</style>
</head>
<body>

	
	<s:if test="%{#session.skema1ID!=0}">
	<a href="/admin/userSkema1.action">Skema 1</a><br>
	</s:if>
	
	<s:if test="%{#session.skema3ID!=0}">
	<a href="/admin/userSkema3.action">Skema 3</a><br>
	</s:if>
	
	<s:if test="%{#session.newskema==true}">
	<a href="/admin/newSkema.action">Create new skema</a><br>
	</s:if>
<a href="/logout.action">Logout</a><br> 
<a	href="/admin/admin-home.action">Home</a><br>

	<div id="tabs">
		<ul>
			<s:if test="%{#session.skema2ID!=0}">
			<li><a href="#skema1">Skema 2</a></li>
			</s:if>
			
			
		</ul>

		<s:if test="%{#session.skema2ID!=0}">
		<div id="skema1">
		
			Number of Events: <s:property value="#session.skema2.NEvents"/><br>
			Number of Expected Events: <s:property value="#session.skema2.NExpected"/><br>
			Number of Played Events: <s:property value="#session.skema2.played"/><br>
			Number of Successfull Events: <s:property value="#session.skema2.won"/><br>
			Initial Cash : <s:property value="#session.skema2.initialCash"/><br>
			Final Cash : <s:property value="#session.skema2.roundedFinalCash"/><br>
			Share : <s:property value="#session.skema2.share"/><br>
			
			<table id="list_skema1"></table> 
			<div id="pager_skema1"></div>
			
			<s:if test="%{wait2}">
			This Skema is waiting for last bet's result.<br>
			</s:if>
			<s:else>
				
			Create new Bet
			<s:form action="admin-request-bet">
			<s:hidden name="description" value="Skema 2"/>
			<s:hidden name="initialAmount" value="%{nextBet2}"/>
			<s:textfield id="amount" name="playedAmount" label="The next bet amount is" readonly="true" value="%{nextBet2}"/>
			<s:textfield readonly="true" id="share" name="realShare" label="Actual share" onkeyup="document.getElementById('amount').value=%{nextBet1}*%{#session.skema1.share}/this.value"/>
		
			
			<table>
				<tr id="row1">	
				<td>Event 1	
						<td><s:textarea name="description1" />
			<td><s:select name="userGuess1" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" />
    		<s:textfield name="share1" id="sh1" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    		
    		<tr id="row2" style="display:none">
    		<td>Event 2
			<td><s:textarea id="ta2" name="description2" style="display:none"/>
			<td><s:select id="s2" name="userGuess2" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share2" id="sh2" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    			
    		<tr id="row3" style="display:none">
    		<td>Event 3
			<td><s:textarea id="ta3" name="description3" style="display:none"/>
			<td><s:select id="s3" name="userGuess3" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share3" id="sh3" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    		
    		<tr id="row4" style="display:none">	
    		<td>Event 4
    		<td><s:textarea id="ta4" name="description4" style="display:none"/>
			<td><s:select id="s4" name="userGuess4" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share4" id="sh4" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    		
    		<tr id="row5" style="display:none">
    		<td>Event 5
    		<td><s:textarea id="ta5" name="description5" style="display:none"/>
			<td><s:select id="s5" name="userGuess5" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share5" id="sh5" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    		
    		<tr id="row6" style="display:none">
    		<td>Event 6
    		<td><s:textarea id="ta6" name="description6" style="display:none"/>
			<td><s:select id="s6" name="userGuess6" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share6" id="sh6" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    			
    		<tr id="row7" style="display:none">
    		<td>Event 7
    		<td><s:textarea id="ta7" name="description7" style="display:none"/>
			<td><s:select id="s7" name="userGuess7" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share7" id="sh7" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    			
    		<tr id="row8" style="display:none">
    		<td>Event 8
    		<td><s:textarea id="ta8" name="description8" style="display:none"/>
			<td><s:select id="s8" name="userGuess8" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share8" id="sh8" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    			
    		<tr id="row9" style="display:none">
    		<td>Event 9
    		<td><s:textarea id="ta9" name="description9" style="display:none"/>
			<td><s:select id="s9" name="userGuess9" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share9" id="sh9" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    			
    		<tr id="row10" style="display:none">
    		<td>Event 10
    		<td><s:textarea id="ta10" name="description10" style="display:none"/>
			<td><s:select id="s10" name="userGuess10" list="#session.res" listValue="description" listKey="matchResultID"
    		emptyOption="false" style="display:none"/>
    		<s:textfield style="display:none" name="share10" id="sh10" value="1" onkeyup="document.getElementById('share').value=$('#sh1')[0].value*$('#sh2')[0].value*$('#sh3')[0].value*$('#sh4')[0].value*$('#sh5')[0].value*$('#sh6')[0].value*$('#sh7')[0].value*$('#sh8')[0].value*$('#sh9')[0].value*$('#sh10')[0].value"/>	    		
    		</tr>
    		
    		</table>
    			
    		<input type="button" onclick="addInput1()" value="Add Bet"/>
    		<input type="button" onclick="removeInput1()" value="Remove Bet"/>
    		<s:hidden id="count" name="count" value="1"/>
						<s:submit value="Send" />
					</s:form>
			
			<s:form action="delete-skema">
			<s:hidden name="skemaIDcl" value="%{#session.skema2ID}"/>
			<s:select name="reason" label="Why closing?" list="#{0:'WON', 1:'LEAVE'}"
    		emptyOption="false" />
			<s:submit value="Delete Skema"/>
			</s:form>
			
			</s:else>
			
			
			
			
		</div>
		</s:if>
	
	</div>

	<script>
		$("#tabs").tabs();

		// Hover states on the static widgets
		$("#dialog-link, #icons li").hover(function() {
			$(this).addClass("ui-state-hover");
		}, function() {
			$(this).removeClass("ui-state-hover");
		});
	</script>


</body>
</html>