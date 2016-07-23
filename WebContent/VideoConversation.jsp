<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type='text/javascript' src='https://d1hm7pwrzpq8ip.cloudfront.net/jwplayer.js'></script>
</head>
<body>
<a href="index.jsp" ><img src="images/signoutblue.jpg" alt="No image found!" name="Image1" style="float: right; margin-right: 10px" width="50" height="50" border="0" id="Image1" /></a>
	<h1>Conversations</h1>
	
	
<%
ResultSet rs =(ResultSet)request.getAttribute("objectres");

/*
for (Map.Entry<String, String> entry : map.entrySet()) {
    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	System.out.println("rtmp://s25e2m8xns0cmv.cloudfront.net/cfx/st/"+entry.getValue());
*/
%>
<input type="button" value="Reply" name="reply_button" onclick="document.location.href='UploadVideo.jsp'" />
<form action="UploadVideo" method="post" name="reply_post_form">
<input type="hidden" name="video_id" />
<%
try
{
	while(rs.next())
    {
		System.out.println(rs.getString(2).toString());
            

%>

<div id='<%=rs.getString(3)%>'></div>
<script type="text/javascript">
   jwplayer('<%=rs.getString(3)%>').setup({
	  primary: "flash",
      file: "rtmp://s32c0ajtpuz8nu.cloudfront.net/cfx/st/<%=rs.getString(2)%>",
      width: "350",
      height: "240"
   });
   
</script>

<div id="video_file_name">

<label>Video Name: </label><%=rs.getString(2)%>&nbsp;&nbsp;&nbsp;&nbsp;
<label>Uploaded By: </label><%=rs.getString(1)%>&nbsp;&nbsp;&nbsp;&nbsp;
<label>Uploaded On: </label><%=rs.getString(4)%>&nbsp;&nbsp;&nbsp;&nbsp;
<!--  <input type="submit" id="<%=rs.getString(3)%>" value="Post reply" onclick="{document.reply_post_form.video_id.value=this.id;}"></input> -->
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