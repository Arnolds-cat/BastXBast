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
	<a href="/manager/userSkema1.action">Skema 1</a><br>
	</s:if>
	
	<s:if test="%{#session.skema2ID!=0}">
	<a href="/manager/userSkema2.action">Skema 2</a><br>
	</s:if>
	
	<s:if test="%{#session.skema3ID!=0}">
	<a href="/manager/userSkema3.action">Skema 3</a><br>
	</s:if>
	
	<a href="/logout.action">Logout</a>
	<br>
	<a href="/manager/manager-home.action">Home</a>
	<br>
	<div id="tabs">
		<ul>	
				<li><a href="#new">Create new skema</a></li>
		</ul>

		

		
		<s:if test="%{#session.newskema==true}">
			<div id="new">
				<h3>Zgjith skem&euml;n tende!</h3>
				<s:form action="/manager/add-skema.action">
					<s:textfield name="nEvents" label="Numri total i skedinave" />
					<s:textfield name="nExpected" label="Sa do kap" />
					<s:textfield name="initialCash" label="Investimi" />
					<s:textfield name="share" label="Koeficienti per çdo skedin" />
					<s:submit value="Add skema" />
				</s:form>
				<s:actionerror />


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