<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
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
            $(function(){
                
                $( "input:submit", ".bottoni" ).button();
                $( "#tabs" ).tabs();
  
                
                $("#list_users").jqGrid(
                        {url:'/manager/json/get-users-from-manager.action', datatype: "json", colNames:['Id', 'Username', 'Password', "Name", "Family Name", "Email", "Phone", "Score", ""], 
                            colModel:[{name:'userID',index:'userID', hidden:false, width:50, editable:false}, 
                                {name:'username',index:'username', width:150, editable: true},
                                {name:'password',index:'password', width:150, hidden:true, editable:false},
                                {name:'userFirstName',index:'userFirstName', width:150, editable: true},
                                {name:'userFamilyName',index:'userFamilyName', width:150, editable: true},
                                {name:'userEmail',index:'userEmail', width:150, editable:true},
                                {name:'userPhone',index:'userPhone', width:150, editable:true},
                                {name:'score',index:'score', width:50, editable:false},
                                {name:'userID',index:'userID', width:150, hidden:false, formatter:open, editable:false}
                                ],
                            jsonReader : {
                                root:"users",
                                repeatitems: false,
                                cell: "",
                                id: "0"
                            },    
                            editurl:'/manager/json/edit-users-from-manager.action',
                            rowNum:5, rowList:[5,10,15], pager: '#pager_users', sortname: 'userID', 
                            viewrecords: true, sortorder: "asc", caption:"Users"
                            

                        });
                
                function open (cellvalue, options, rowObject) {
    				if(cellvalue != null)
    					return "<a target='_blank' href='/manager/get-user-skema?userID=" + cellvalue + "'>Skema</a>";
    				else
    					return '';			
    		}  
                
                $("#list_users").jqGrid('navGrid','#pager_users',       
                        {edit:true,add:true,del:true, search:false, refresh:false}, {afterSubmit: function(response, postdata) {  
                                var json = response.responseText;
                                var message = eval(json)
                                if (message == "success") {                        
                        
                                    return [true,''];
                                }
                                else if (message == "error") {
                                    return [false, 'Error: please do not insert users with the same username'];
                                }
                            }, closeAfterEdit:true}, {afterSubmit: function(response, postdata) {  
                                var json = response.responseText;
                                var message = eval(json)
                                if (message == "success") {                        
                        
                                    return [true,''];
                                }
                                else if (message == "error") {
                                    return [false, 'Error: please do not insert users with the same username'];
                                }
                            }, closeAfterAdd:true});
                
                
                
                
            });
           

        </script>
	
	

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
		<li><a href="#users">Users</a></li>
	</ul>
	
	<div id="users">
		<table id="list_users"></table> 
        <div id="pager_users"></div>
        
        Income: <s:property value="#session.income"/>
        <a href="/logout.action">Logout</a><br>
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