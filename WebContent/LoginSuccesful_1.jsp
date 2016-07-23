<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript">
		
		function navigate(conv_id)
		{
			
			document.forms['reply_post_form'].elements['buttonclicked'].value=conv_id;
			document.forms['reply_post_form'].submit();
		}
		function popup(pageURL, title, popupWidth, popupHeight) 
		{
		    var left = (screen.width / 2) - (popupWidth / 2);
		    var top = (screen.height / 2) - (popupHeight / 2);
		    var targetPop = window.open(pageURL, title, 'scrollbar=none, width=' + popupWidth + ',height=' + popupHeight + ', top=' + top + ', left=' + left);
		}
</script>
<style type="text/css">
div.transbox
  {
  width:1250px;
  height:500px;
  margin:50px 50px 50px 50px;
  background-color:white;
  border:1px solid black;
  border-radius:15px;
  opacity:0.8;
  filter:alpha(opacity=40); /* For IE8 and earlier */
  box-shadow: 10px 10px 5px #888888;
  overflow: auto;
  }
  .back
{
   background-image:url("images/square-background.jpg");
	background-position:center;
	background-attachment:fixed;
	background-size:cover;
	background-repeat:no-repeat;
}
.signout
{
	position:relative;
	top:-100px;
	float:right;
	margin-right:25px;
}

.heading
	{
	position:relative;
	top:-100px;
 	font-weight: normal;
    font-size: 50px;
    font-variant: small-caps;
    text-align:left;
    text-indent:60px;
    color:#932E2E;
    margin-right: 40px;
	}

</style>

<title>Insert title here</title>
</head>
<body class="back">
<div class="transbox">
<p style="font-family:Georgia; color:#00C; font-size:36px" align="center">   TWIT TALK </p>

<a href="index.jsp" ><img src="images/signoutblue.jpg" alt="No image found!" name="Image1" class="signout" width="50" height="50" border="0" id="Image1" /></a>
	<span class="heading" >Welcome <%=request.getAttribute("varName")%> !!!</span>
	<div class="output">
	<a ><img src="images/add-user-icon.jpg" style="float: right;margin-right: 80px"  alt="No image found!" name="Image2" width="150" height="150" border="5px" id="Image2" onclick="document.location.href ='AddContacts.jsp'"/></a>
	<form >
	<input type = "button" name="submitgroup" value ="Add New Person" style="float: right;margin-right: 80px ;position: relative;top: 180px; left: 200px;" onclick='AddContacts.jsp'/>
	</form>
	<form method="post" action="AddGroup">
		<a ><img src="images/add_group.png" style="float: right;margin-right: 40px" alt="No image found!" name="Image3" width="150" height="150" border="5px" id="Image3" /></a>
		<input type = "submit" name="submitgroup" value ="Add New Group" style="float: right;margin-right: 40px ;position: relative;top: 180px; left: 180px;"/>
	</form>	
	
	
	<%
		String userName =(String)request.getAttribute("varName");
		request.setAttribute("varName", userName);	
		ResultSet res=(ResultSet)request.getAttribute("conv_list");
	    System.out.println("User:" + userName +"has logged in");
	%>
	
	<h1>CONVERSATION LIST</h1>
	
		
	<form action="FriendConversation" method="post" name="reply_post_form">
	<%
	try
	{
		String conv_name = "";
		while(res.next())
    	{
			//System.out.println(res.getString("uname").toString());
			
	%>
			
			<table border="0">
			<tr>
			<td><img src="images/contact_list.jpg" width="60" height="60" onclick="navigate(<%=res.getInt("conv_id")%>)"/></td>
			<td><h3><%= res.getString("conv_name") %></h3></td>
			</tr>
			</table>
 <%
    }
}

catch(Exception e)
{
	System.out.println(e);
}
%>
			<input type="hidden" name="buttonclicked" value="none" />
			</form>
</div>
</div>
</body>
	
</html>