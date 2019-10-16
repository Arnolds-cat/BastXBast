<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	$(function() {

		$("input:submit", ".bottoni").button();
		$("#tabs").tabs();

		$("#list_managers").jqGrid(
				{
					url : '/admin/json/get-managers.action',
					datatype : "json",
					colNames : [ 'Id', 'Username', 'Password', "Name",
							"Family Name", "Email", "Phone", "Income",
							"Percent" ],
					colModel : [ {
						name : 'userID',
						index : 'userID',
						width : 50,
						hidden : false,
						editable : false
					}, {
						name : 'username',
						index : 'username',
						width : 150,
						editable : true
					}, {
						name : 'password',
						index : 'password',
						width : 150,
						editable : true
					}, {
						name : 'userFirstName',
						index : 'userFirstName',
						width : 150,
						editable : true
					}, {
						name : 'userFamilyName',
						index : 'userFamilyName',
						width : 150,
						editable : true
					}, {
						name : 'userEmail',
						index : 'userEmail',
						width : 150,
						editable : true
					}, {
						name : 'userPhone',
						index : 'userPhone',
						width : 150,
						editable : true
					}, {
						name : 'income',
						index : 'income',
						width : 150,
						editable : true
					}, {
						name : 'managerPercent',
						index : 'managerPercent',
						width : 150,
						editable : true
					} ],
					jsonReader : {
						root : "managers",
						repeatitems : false,
						cell : "",
						id : "0"
					},
					editurl : '/admin/json/edit-managers.action',
					rowNum : 5,
					rowList : [ 5, 10, 15 ],
					pager : '#pager_managers',
					sortname : 'userID',
					viewrecords : true,
					sortorder : "asc",
					caption : "Managers"

				});

		$("#list_managers")
				.jqGrid(
						'navGrid',
						'#pager_managers',
						{
							edit : true,
							add : true,
							del : true,
							search : false,
							refresh : false
						},
						{
							afterSubmit : function(response, postdata) {
								var json = response.responseText;
								var message = eval(json)
								if (message == "success") {

									return [ true, '' ];
								} else if (message == "error") {
									return [ false,
											'Error: please do not insert managers with the same username' ];
								}

							},
							closeAfterEdit : true
						},
						{
							afterSubmit : function(response, postdata) {
								var json = response.responseText;
								var message = eval(json)
								if (message == "success") {

									return [ true, '' ];
								} else if (message == "error") {
									return [ false,
											'Error: please do not insert managers with the same username' ];
								}

							},
							closeAfterAdd : true
						});

		$("#list_users").jqGrid(
				{
					url : '/admin/json/get-users.action',
					datatype : "json",
					colNames : [ 'Id', 'Username', 'Password', "Name",
							"Family Name", "Email", "Phone",
							"Manager username", "Score", "" ],
					colModel : [ {
						name : 'userID',
						index : 'userID',
						width : 50,
						hidden : false,
						editable : false
					}, {
						name : 'username',
						index : 'username',
						width : 150,
						editable : true
					}, {
						name : 'password',
						index : 'password',
						width : 150,
						editable : true
					}, {
						name : 'userFirstName',
						index : 'userFirstName',
						width : 150,
						editable : true
					}, {
						name : 'userFamilyName',
						index : 'userFamilyName',
						width : 150,
						editable : true
					}, {
						name : 'userEmail',
						index : 'userEmail',
						width : 150,
						editable : true
					}, {
						name : 'userPhone',
						index : 'userPhone',
						width : 150,
						editable : true
					}, {
						name : 'manager.username',
						index : 'manager.username',
						width : 150,
						editable : true
					}, {
						name : 'score',
						index : 'score',
						width : 50,
						editable : false
					}, {
						name : 'userID',
						index : 'userID',
						width : 150,
						hidden : false,
						formatter : open,
						editable : false
					} ],
					jsonReader : {
						root : "users",
						repeatitems : false,
						cell : "",
						id : "0"
					},
					editurl : '/admin/json/edit-users.action',
					rowNum : 5,
					rowList : [ 5, 10, 15 ],
					pager : '#pager_users',
					sortname : 'userID',
					viewrecords : true,
					sortorder : "asc",
					caption : "Users"

				});

		function open(cellvalue, options, rowObject) {
			if (cellvalue != null)
				return "<a target='_blank' href='/admin/get-user-skema?userID="
						+ cellvalue + "'>Skema</a>";
			else
				return '';
		}

		$("#list_users")
				.jqGrid(
						'navGrid',
						'#pager_users',
						{
							edit : true,
							add : true,
							del : true,
							search : false,
							refresh : false
						},
						{
							afterSubmit : function(response, postdata) {
								var json = response.responseText;
								var message = eval(json)
								if (message == "success") {

									return [ true, '' ];
								} else if (message == "error") {
									return [ false,
											'Error: please do not insert users with the same username' ];
								} else if (message == "error1") {
									return [ false,
											'This manager does not exist.' ];
								}
							},
							closeAfterEdit : true
						},
						{
							afterSubmit : function(response, postdata) {
								var json = response.responseText;
								var message = eval(json)
								if (message == "success") {

									return [ true, '' ];
								} else if (message == "error") {
									return [ false,
											'Error: please do not insert users with the same username' ];
								} else if (message == "error1") {
									return [ false,
											'This manager does not exist.' ];
								}
							},
							closeAfterAdd : true
						});

		$("#list_waitingforexecution").jqGrid(
				{
					url : '/admin/json/get-exbet.action',
					datatype : "json",
					colNames : [ 'Id', "User ID", 'Skema', 'Order', "Amount",
							"Skema Share", 
							"Type", "" ],
					colModel : [ {
						name : 'IDbet',
						index : 'IDbet',
						width : 50,
						hidden : false,
						editable : false
					}, {
						name : 'skema.user',
						index : 'skema.user',
						width : 50,
						editable : false
					}, {
						name : 'skema.description',
						index : 'skema.description',
						width : 50,
						editable : false
					}, {
						name : 'positionInSkema',
						index : 'positionInSkema',
						width : 50,
						editable : false
					}, {
						name : 'initialAmount',
						index : 'initialAmount',
						width : 50,
						editable : false
					}, {
						name : 'realQuote',
						index : 'realQuote',
						width : 50,
						editable : false
					}, {
						name : 'type',
						index : 'type',
						width : 50,
						editable : false
					}, {
						name : 'IDbet',
						index : 'IDbet',
						width : 100,
						editable : false, 
						formatter : link
					} ],
					jsonReader : {
						root : "bets",
						repeatitems : false,
						cell : "",
						id : "0"
					},
					rowNum : 5,
					rowList : [ 5, 10, 15 ],
					pager : '#pager_waitingforexecution',
					sortname : 'IDbet',
					viewrecords : true,
					sortorder : "asc",
					caption : "Waiting for Execution"

				});

		$("#list_waitingforresult").jqGrid(
				{
					url : '/admin/json/get-resbet.action',
					datatype : "json",
					colNames : [ 'Id', "User ID", 'Skema', 'Order', "Amount",
							"Actual Share", "Played Amount", 
							"Type", "" ],
					colModel : [ {
						name : 'IDbet',
						index : 'IDbet',
						width : 50,
						hidden : false,
						editable : false
					}, {
						name : 'skema.user',
						index : 'skema.user',
						width : 50,
						editable : false
					}, {
						name : 'skema.description',
						index : 'skema.description',
						width : 50,
						editable : false
					}, {
						name : 'positionInSkema',
						index : 'positionInSkema',
						width : 50,
						editable : false
					}, {
						name : 'initialAmount',
						index : 'initialAmount',
						width : 50,
						editable : false
					}, {
						name : 'realQuote',
						index : 'realQuote',
						width : 50,
						editable : false
					}, {
						name : 'playedAmount',
						index : 'playedAmount',
						width : 50,
						editable : false
					}, {
						name : 'type',
						index : 'type',
						width : 50,
						editable : false
					}, {
						name : 'IDbet',
						index : 'IDbet',
						width : 50,
						editable : false,
						formatter : link
					} ],
					jsonReader : {
						root : "bets",
						repeatitems : false,
						cell : "",
						id : "0"
					},
					rowNum : 5,
					rowList : [ 5, 10, 15 ],
					pager : '#pager_waitingforresult',
					sortname : 'IDbet',
					viewrecords : true,
					sortorder : "asc",
					caption : "Waiting for Result"

				});

		$("#list_deleteskema").jqGrid(
				{
					url : '/admin/json/get-delete-skema.action',
					datatype : "json",
					colNames : [ 'Id', 'N Events', 'N Successful', "Won",
							"Played", "Current Cash", "Share", "Final Cash",
							"User ID" ],
					colModel : [ {
						name : 'IDSkema',
						index : 'IDSkema',
						width : 50,
						hidden : false,
						editable : false
					}, {
						name : 'NEvents',
						index : 'NEvents',
						width : 50,
						editable : false
					}, {
						name : 'NExpected',
						index : 'NExpected',
						width : 50,
						editable : false
					}, {
						name : 'won',
						index : 'won',
						width : 150,
						editable : false
					}, {
						name : 'played',
						index : 'played',
						width : 50,
						editable : false
					}, {
						name : 'currentCash',
						index : 'currentCash',
						width : 50,
						editable : false
					}, {
						name : 'share',
						index : 'share',
						width : 50,
						editable : false
					}, {
						name : 'roundedFinalCash',
						index : 'roundedFinalCash',
						width : 50,
						editable : false
					}, {
						name : 'user',
						index : 'user',
						width : 50,
						editable : false
					} ],
					jsonReader : {
						root : "skema",
						repeatitems : false,
						cell : "",
						id : "0"
					},
					rowNum : 5,
					rowList : [ 5, 10, 15 ],
					pager : '#pager_deleteskema',
					sortname : 'IDSkema',
					viewrecords : true,
					sortorder : "asc",
					caption : "Closing skema"

				});
		 function link(cellvalue, options, rowObject) {
 			if (cellvalue != null)
 				return "<a href='/admin/get-bet-page?IDbet="
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

	Admin income
	<s:property value="#session.income" />
	<br>
	<a href="/logout.action">Logout</a>
	<br>

	<div id="tabs">
		<ul>
			<li><a href="#managers">Managers</a></li>
			<li><a href="#users">Users</a></li>
			<li><a href="#execution">Bets Waiting for Execution</a></li>
			<li><a href="#result">Bets Waiting For Result</a></li>
			<li><a href="#deleteskema">Closing Skema</a></li>
		</ul>


		<div id="managers">
			<table id="list_managers"></table>
			<div id="pager_managers"></div>
			<a href="/logout.action">Logout</a><br>
		</div>

		<div id="users">
			<table id="list_users"></table>
			<div id="pager_users"></div>

		</div>



		<div id="execution">
			<table id="list_waitingforexecution"></table>
			<div id="pager_waitingforexecution"></div>

			


		</div>

		<div id="result">
			<table id="list_waitingforresult"></table>
			<div id="pager_waitingforresult"></div>

			

		</div>

		<div id="deleteskema">
			<table id="list_deleteskema"></table>
			<div id="pager_deleteskema"></div>

			<s:form action="/admin/delete-skema">
				<s:textfield name="skemaIDcl" label="Bet ID"></s:textfield>
				<s:select name="reason" label="Why closing?"
					list="#{0:'WON', 1:'LEAVE'}" emptyOption="false" />
				<s:submit value="Submit" />
			</s:form>

		</div>



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