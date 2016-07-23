<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
function valid()
{
var search = document.getElementsByName('searcheduser');
var ischecked_method = false;
for ( var i = 0; i < search.length; i++) {
    if(search[i].checked) 
    {
    	document.forms['user_list_form'].submit();
    	ischecked_method=true;
    }
}
if(!ischecked_method)   
{ //payment method button is not checked
    alert("Please choose a option");
}
else
	{
	document.forms['user_list_form'].submit();
	}
}
		function navigate(radioName)
		{
			document.forms['user_list_form'].elements['buttonclicked'].value=radioName;
			//document.forms['user_list_form'].submit();
		}
</script>

<title>Insert title here</title>
</head>
<body>
	<%
		String userName=(String) request.getAttribute("varName");
		request.setAttribute("varName", userName);
	%>
	<form action="AddUser" method="post" name="user_list_form">
	<%
	
	ResultSet res=(ResultSet)request.getAttribute("availableUsers");
	try
	{
		while(res.next())
    	{
			//System.out.println(res.getString("uname").toString());
			
	%>
			
			
			<input type="radio" name="searcheduser" value="<%=res.getString("uname")%>" onchange="navigate('<%=res.getString("uname")%>');"/><%=res.getString("uname")%>
			<br/>
			
    <%
    }
}
catch(Exception e)
{
	System.out.println(e);
}
%>
			<input type="hidden" name="buttonclicked" value="none" />
			<input type="button" name="submitbutton" value="Add Selected Contact" onclick="return valid()"/>
			</form>
	
</body>
</html>