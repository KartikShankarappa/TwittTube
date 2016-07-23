<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="Front_page_design.css">
<script type="text/javascript">
		
		function validateGroupName(form)
		{
			var hasAtleastOneChecked=false;
			if(document.forms['user_list_form'].elements['groupName'].value=="")
			{
				alert("Please enter a group name");
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
	
	
	<form action="CreateGroup" method="post" name="user_list_form">
	Provide a name for the group: <input type="text" id="groupName" name="groupName" value=""/><br/>
	
	<%
	
	ResultSet res=(ResultSet)request.getAttribute("friendList");
	try
	{
		while(res.next())
    	{
			//System.out.println(res.getString("uname").toString());
			
	%>
			
			
			<input type="checkbox" name="searcheduser" value="<%=res.getString("friend")%>"/><%=res.getString("friend")%>
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
			<input type="button" name="submitbutton" value="Add Selected Contacts" onclick="validateGroupName(this);" />
			
			</form>
	
</body>
</html>