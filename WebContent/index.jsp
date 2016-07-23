<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Dark Login Form</title>
  <link rel="stylesheet" href="style.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<script type="text/javascript" language="javascript">
function valid()
{
	var un=document.getElementById("uname").value;
	var pd=document.getElementById("pwd").value;
	if(un=="")
	{
		document.getElementById("uname").focus();
		document.getElementById("err").innerHTML="Please enter the username!";
		return false;
	}
	else if(pd=="")
	{
		document.getElementById("pwd").focus();
		document.getElementById("err").innerHTML="Please enter the password!";
		return false;
	}
	return true;
}
</script>

<body>

  <form   method="post" action="Login" class="login">
    <p>
      <label for="login">Email:</label>
      <input type="text" id="uname" name="Name" value="">
    </p>

    <p>
      <label for="password">Password:</label>
      <input type="password" id="pwd" name="Pass" value="">
    </p>

    <p class="login-submit">
      <button type="submit" class="login-button" onClick="return valid();"  />Login</button>
    </p>

    <p class="forgot-password"><a href="index.jsp">Forgot your password?</a></p>
    <p class="forgot-password"><a>Create account</a></p>
    
  </form>
  <font color="#FFF" >
    <p align="center" id="err">
    
    </p></font>
   
</body>
</html>
