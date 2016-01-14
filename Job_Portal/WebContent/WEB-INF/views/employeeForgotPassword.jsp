<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Forgot Password</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css" />" />
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
<script type="text/javascript">
	function validateEmail(sEmail) {
		var filter = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
		if (filter.test(sEmail)) {
			return true;
		}
		else {
			return false;
	}
}

	function doSendEmail() {	 			
		if( !validateEmail(document.forgotPassword.email.value)){
			alert("Please enter a valid email!!!");
			document.forgotPassword.email.focus();
		}else{			
			$.ajax({
	           url : 'sendEmployeePassword',
	           data : "email=" + $("#email").val(),
	            success : function(data) {
	            	if( data == "0") {
 		            	alert("Password sent to your email. Please check.");
	            	} else if(data == "1"){
	            		alert("There is no employees registered with this e-mail id");
					}else{
	            		alert("Problem with your internet connection to send the password!!!");		            		
	            	}		               
	            }
	        });	
		}
	}	
</script>
</head>
<body>
<div class="seeker_login" style="width:635px;height:250px;">
	<h2>Forgot Password?</h2>
	<form name="forgotPassword" method="post" action=""/>
		<table border="0" cellpadding="5" cellspacing="5">
			<tr><td colspan="2">Please enter your e-mail to receive the password</td></tr>
			<tr>
				<td><label>Email Address</label></td>
				<td><input type="text" name="email" id="email" value=""/></td>
			</tr>	
			<tr>
				<td colspan="2">
					<input class="log_for_input" type="button" value="Send Password" name="" onClick="javascript:doSendEmail();"/>
				</td>
			</tr>
		</table>
	</form>	
</div>
</body>
</html>