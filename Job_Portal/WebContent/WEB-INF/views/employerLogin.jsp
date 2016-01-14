<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Your Jobs</title>
		<!-- html 5 jquery -->
		<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
		<script>var jQ = jQuery.noConflict();</script>
		<link href="<c:url value="/resources/multibox/multibox.css" />" rel="stylesheet" type="text/css" />
		<!--[if lte IE 6]><link rel="stylesheet" href="<c:url value="/resources/multibox/ie6.css" />" type="text/css" media="all" /><![endif]-->
		<script type="text/javascript" src="<c:url value="/resources/multibox/_common/js/mootools.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/multibox/overlay.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/multibox/multibox.js" />"></script>
		<script type="text/javascript">
			var box = {};
			window.addEvent('domready', function(){
				box = new MultiBox('mb', {descClassName: 'multiBoxDesc', useOverlay: true});
			});	
		
 			document.createElement('header');
 			document.createElement('hgroup');
 			document.createElement('nav');
 			document.createElement('menu');
 			document.createElement('section');
 			document.createElement('article');
 			document.createElement('aside');
 			document.createElement('footer');		

 			/* Message functions */
 			var messages = new Array();
 			messages[0] = "Registration completed Sucessfully. Please login.";
 			messages[1] = "Please enter a valid email!!!";
 			messages[2] = "Please enter password!!!";
 			messages[3] = "Login Incorrect. Please try with correct credentials!!!";

 			function hideMessage(){
 				setTimeout(function(){document.getElementById("tabMessage").className="trans75"}, 100);
 				setTimeout(function(){document.getElementById("tabMessage").className="trans50"}, 150);
 				setTimeout(function(){document.getElementById("tabMessage").className="trans25"}, 200);
 				setTimeout(function(){document.getElementById("divMessage").style.display = "none"}, 250);
 			}

 			function viewMessage(messageID){
 				document.getElementById("tabMessage").className="trans100";
 				document.getElementById("divMessage").style.display = "block"
 				document.getElementById("spanMessage").innerHTML = messages[messageID];	
 			}
 			/* end of Message functions */		
 			
 			function validateEmail(sEmail) {
				var filter = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
				if (filter.test(sEmail)) {
					return true;
				}
				else {
					return false;
				}
			}

 			function doLogin() {	 			
				if( !validateEmail(jQ('#loginEmail').val())){
					viewMessage(1);
					jQ('#loginEmail').focus();
				}else if( jQ('#loginPassword').val() == "" ) {
					viewMessage(2);
					jQ('#loginPassword').focus();
				}else{
					validateCredentialsAndLogin();
				}
 			}	

 			function validateCredentialsAndLogin(){ 				
 				jQ.ajax({
  		           url : 'employerLoginChk',
  		           data : "email=" + jQ("#loginEmail").val() + "&password=" + jQ("#loginPassword").val(),
  		            success : function(data) {
  		            	if( data == "0") {
  	 		            	viewMessage(3);
  	 		            	jQ('#divMessage').focus();
  		            	}else{
  		            		hideMessage();
	 						jQ('#employerLogin').submit();  		            		
  		            	}		               
  		            }
  		        });
 			}
			</script>
		<!--[if gte IE 9]>
  			<style type="text/css">
    			.gradient {
       			filter: none;
    			}
 	 		</style>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css" />" />
	</head>
	<body>	
	<%@include file="employerHeader.jsp" %>
	<div class="content-warp">
		<div class="container">
			<table width="100%" cellspacing="0" cellpading="0">
				<tr height="30">
					<td class="heading-top"><h1>Login</h1></td>
					<td>
			    		<div id="divMessage" style="padding-top:2px;padding-bottom:2px;display:none;">
							<table border="0" cellpadding="0" cellspacing="0" id="tabMessage"  style="border:1px solid #999999">
								<tr>
									<td width="500" height="20"><span id="spanMessage" class="message_rosso_bold" style="padding-left:5px">Please enter Password!!!</span></td>
									<td width="20"><a href="javascript:hideMessage()"><img src="<c:url value="/resources/images/close.jpg" />" border="0"></a></td>
								</tr>
							</table>
						</div> 
					</td>
				</tr>
			</table>  
			<div class="main_page">
				<section class="block-left">
					<div class="seeker_login">
						<h2>Employer Login</h2>
						<form:form method="POST" action="/Job_Portal/showEmployerProfile" commandName="employerLogin" name="employerLogin" >
							<div>
								<div class="label">
									<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Email Address</label>
								</div>
								<div class="input">
									<input class="" type="text" name="loginEmail" id="loginEmail" value="" />
								</div>
							</div>
							<div class="clear"></div>
							<div>
								<div class="label">
									<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Password</label>
								</div>
								<div class="input">
									<input class="login_input" type="password" name="loginPassword" id="loginPassword" value="" />
								</div>
							</div>
							<div class="clear"></div>
							<div class="b">
								<div class="label hidden">
									<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Password</label>
								</div>								
							</div>
							<div class="clear"></div>
							<input class="log_for_input" type="button" value="Login" name="" onClick="javascript:doLogin();"/>
						</form:form>
						<div>
							<p><a href="multiboxRedirector.jsp?fun=employer_forgot_pwd" id="mb9" class="mb" rel="width:700,height:300">Forgot Password?</a></p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="new_user mar-bot-none">
						<h2>New User? Register Now</h2>
						<ul>
							<li><a href="#">Java Candidates</a></li>
							<li><a href="#">.NET Candidates</a></li>
							<li><a href="#">Oracle Developers</a></li>
							<li><a href="#">Network Administrators</a></li>
							<li><a href="#">Web Developers</a></li>
							<li><a href="#">Database Administrators</a></li>
						</ul>
						<a href="#" class="yel-btn"></a> 
					</div>
				</section>
				<section class="right-left">
					
				</section>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="clear"></div>
	<%@include file="footer.jsp" %>
	<c:if test="jQ{not empty from_regn}">
		<script type="text/javascript">
			viewMessage(0);
		</script>	
	</c:if>
	</body>
</html>