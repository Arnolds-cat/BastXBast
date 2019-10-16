<%-- <%@ page language="java" contentType="text/html; charset=US-ASCII" --%>
<%--          pageEncoding="US-ASCII" %> --%>
         <%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>BastXBast</title>
	<!-- link rel="Stylesheet" type="text/css" href="css/main.css"  /> -->
	<link rel="Stylesheet" type="text/css" href="css_old/jquery-ui.css"  />	
	<script src="js_old/jquery.js"></script>
	<script type="text/javascript" src="js_old/jquery-ui.js"></script>
	

	<style>
	body{
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

<div id="tabs">
	<ul>
		<li><a href="#home">Home</a></li>
	</ul>
	<div id="home">
	<h3>Login</h3>
		<s:form action="login">
                    <s:textfield  name="username" label="Username"/>
                    <s:password name="password" label="Password"/>         
                    <s:submit value="Submit"/>
                </s:form>
                <s:actionerror/>
                
                <h3>Zgjith skem&euml;n tende!</h3>
                <s:form action="create-skema">
					<s:textfield name="nEvents" label="Numri total i skedinave"/> 
                    <s:textfield  name="nExpected" label="Sa do kap"/> 
                    <s:textfield  name="initialCash" label="Investimi"/> 
                    <s:textfield  name="stringShare" label="Koeficienti per çdo skedin"/>                          
                    <s:submit value="Llogaritë fitimin"/>
                </s:form>
                <s:actionerror/>
                Fitimi: <s:property value="result"/>
                
	</div>	
</div>

<script>

$( "#tabs" ).tabs();


// Hover states on the static widgets
$( "#dialog-link, #icons li" ).hover(
	function() {
		$( this ).addClass( "ui-state-hover" );
	},
	function() {
		$( this ).removeClass( "ui-state-hover" );
	}
);
</script>


</body>
</html>