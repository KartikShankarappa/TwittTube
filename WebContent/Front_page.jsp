<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="Front_page_design.css">
<style type="text/css">

a.ex:hover,a.ex:active {font-size:110%;color:black; margin-top:100px;}
div.scroll{margin-top:"30px";}
iframe.margin{ margin-top:30px;}

</style>
<title>Insert title here</title>
</head>

<script>

function make_blank (oInput)
{
    if (!('placeholder' in oInput))
        oInput.placeholder = oInput.value;
    if (oInput.value != oInput.placeholder)
        oInput.value = '';
}

function readbox()
{
	var txtinput=document.getElementById('txtinput').value;
	if(txtinput=="Search from collection" || !txtinput.match(/\S/))
	alert("Text box is empty");
	else
	alert("you typed "+txtinput);	
	}
	
function popitup(url) {
	newwindow=window.open(url,'name','height=200,width=150');
	if (window.focus) {newwindow.focus()}
	return true;
	
}




function popup(pageURL, title, popupWidth, popupHeight) 
{
    var left = (screen.width / 2) - (popupWidth / 2);
    var top = (screen.height / 2) - (popupHeight / 2);
    var targetPop = window.open(pageURL, title, 'scrollbar=none, width=' + popupWidth + ',height=' + popupHeight + ', top=' + top + ', left=' + left);
}




</script>


<body id="background">
<form action="MyServlet.java">
<div class="wrapper">
	
		<div class="heading">Twitt and Talk<div class="tagline">new way to get connected with your love once.</div></div>
	
	<div id="divLine"></div>
	
	<div>
			<ul>
			
			<li>	
			</li>
			
			
					<li ><a class="ex" color="#932E2E;"
					href="javascript:void(0);" onclick="popup('Upload.jsp','Upload',450,200);" >
					
					
					<!-- href="javascript: void(0)" 
  					 onclick="window.open('Upload.jsp', 'windowname1', 'width=500, height=500'); 
   						return false;"-->
   				UPLOAD</a></li><li src=""></li>
				<li></li><li><a class="ex"> FAVOIRATE </a></li src=""><li></li>
				<li class="textbox"><input type="text" id="txtinput" value="Search from collection" onfocus="make_blank(this);" /><li></li>
				<input type="button" value="Search" style="background-color:#FF9999" onclick="readbox()"/>
				</li>
			</ul>
	</div>

   <div class="recentVideos"><p>Recent updates</p>
   <div class="scroll">
   		<iframe class="margin" width="300" height="300"
 		src="http://www.youtube.com/embed/XGSy3_Czz8k">
 			
		</iframe>
		<iframe class="margin" width="300" height="300"
 		src="http://www.youtube.com/embed/XGSy3_Czz8k">	
		</iframe>
		<iframe class="margin" width="300" height="300"
 		src="http://www.youtube.com/embed/XGSy3_Czz8k">	
		</iframe>
		<iframe class="margin" width="300" height="300"
 		src="http://www.youtube.com/embed/XGSy3_Czz8k">	
		</iframe>
		<iframe class="margin" width="300" height="300"
 		src="http://www.youtube.com/embed/XGSy3_Czz8k">	
		</iframe>
</div>
   </div>
	<div class="oldVideos"> <p> Old videos </p>
		<div class="scroll" >

			<iframe class="margin" width="300" height="300"
 			src="http://www.youtube.com/embed/XGSy3_Czz8k">	
			</iframe>
			<iframe class="margin" width="300" height="300"
 			src="http://www.youtube.com/embed/XGSy3_Czz8k">	
			</iframe>
			<iframe class="margin" width="300" height="300"
 			src="http://www.youtube.com/embed/XGSy3_Czz8k">	
			</iframe>
			<iframe class="margin" width="300" height="300"
 			src="http://www.youtube.com/embed/XGSy3_Czz8k">	
			</iframe>
			<iframe class="margin" width="300" height="300"
 			src="http://www.youtube.com/embed/XGSy3_Czz8k">	
			</iframe>

		</div>
	</div>
</div>
</form>   
</body>   
</html>   
   
   