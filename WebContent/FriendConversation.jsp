<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.net.URL" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.back

{
    background-image: url("images/stonewall.png");
	background-position:center;
	background-attachment:fixed;
	background-size:cover;
	background-repeat:no-repeat;
}

</style>
</head>

<body class="back">
	<center><h1> Friend's Conversation</h1></center>
	<%
	ResultSet rs =(ResultSet)request.getAttribute("objectres");
	%>
	<form action="Reply" method="post" name="object_list_form">
	<input type="hidden" name="video_id" />
	<%
	try
	{
		while(rs.next())
    	{
			System.out.println(rs.getString(3).toString());
	%>
    <label><%=rs.getString(1)%></label>
	<div id='<%=rs.getString(3)%>'></div>
	<script type="text/javascript">
   		jwplayer('<%=rs.getString(3)%>').setup({
      	file: "rtmp://s25e2m8xns0cmv.cloudfront.net/cfx/st/<%=rs.getString(2)%>",
      	width: "350",
      	height: "240"
   	});
   
	</script>

	<div id="video_file_name">
	<%=rs.getString("name")%>
	<input type="submit" id="<%=rs.getString("video_id")%>" value="Post reply" onclick="{document.reply_post_form.video_id.value=this.id;}"></input>
	</div>

	<br>
	<%
    	}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	%>
</body>
</html>