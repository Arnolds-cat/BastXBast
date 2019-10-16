<%@ page contentType="text/html; charset=UTF-8"%>
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

		$("#list_skema1").jqGrid(
				{
					url : '/manager/json/get-subbets.action?IDbet='
							+ <s:property value="IDbet"/>,
					datatype : "json",
					colNames : [ 'Description', 'User Choice', "Share" ],
					colModel : [ {
						name : 'description',
						index : 'description',
						hidden : false,
						width : 200,
						editable : false
					}, {
						name : 'userGuess.description',
						index : 'userGuess.description',
						width : 150,
						editable : false
					}, {
						name : 'share',
						index : 'share',
						width : 50,
						hidden : false,
						editable : false
					}, ],
					jsonReader : {
						root : "subbets",
						repeatitems : false,
						cell : "",
						id : "0"
					},
					rowNum : 5,
					rowList : [ 5, 10, 15 ],
					pager : '#pager_skema1',
					sortname : 'description',
					viewrecords : true,
					sortorder : "asc",
					caption : "Bets",

				});

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
	<a href="/logout.action">Logout</a>
	<br>
	<a href="/manager/manager-home.action">Home</a>
	<br>
	<div id="tabs">
		<ul>
			<li><a href="#skema1">Bets</a></li>
		</ul>


		<div id="skema1">

			<table id="list_skema1"></table>
			<div id="pager_skema1"></div>

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