<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Contacts</title>
<link type="text/css" rel="stylesheet" href="Front_page_design.css">
</head>
<script type="text/javascript">
function inputFocus(i){
    if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
}
function inputBlur(i){
    if(i.value==""){ i.value=i.defaultValue; i.style.color="#888"; }
}
</script>
<style>
 .back
{
   background-image:url("images/square-background.jpg");
	background-position:center;
	background-attachment:fixed;
	background-size:cover;
	background-repeat:no-repeat;
}
</style>
<body class="back">
	<%
	String userName=(String)request.getAttribute("varName") ;
	request.setAttribute("varName", userName);
	%>

	<form action="SearchUser" method="post" name="SearchForm">
	<center><H1>ADD NEW FRIENDS!!</H1>
		<input type="text" name="Name" value="Enter the user to be searched" onfocus="inputFocus(this)" onblur="inputBlur(this)" size="50px"/>
		<br/>
		<input type="submit" name="okbutton" value="SEARCH">
		</center>
	</form>	
</body>
</html>