<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employer Forgot Password</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css" />" />
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
<script type="text/javascript">
	function doSendEmail() {	 			
		if( $('#subject').val() == "" ) {
			alert("Please enter subject!!!");
		}else if( $('#message').val() == "" ) {
			alert("Please enter message!!!");
		}else{
			$.ajax({
		           url : 'replyByEmail',
		           data : "email=${candidateEmail}&subject=" + $('#subject').val() + "&message=" + $('#message').val(),
		           success : function(data) { 		            	
						if( data == "0") {
							alert("You Replied to the candidate sucesssfully!!!");
            			}else{
            				alert("Problem in Sending Mail. Please check your net connection!!!");	            				      		
            			}
						window.parent.box.close(parent.document.getElementById('mb9'));     
		           }	 		            
		   	});
		}
	}	
</script>
</head>
<body>
<div class="seeker_login" style="width:635px;height:450px;">
	<h2>Send Reply to: ${candidateEmail}</h2>
	<form name="forgotPassword" method="post" action=""/>
		<table border="0" cellpadding="5" cellspacing="5">
			<tr><td colspan="2">Please enter the subject and message to send reply</td></tr>
			<tr>
				<td><label>Subject</label></td>
				<td><input type="text" name="subject" id="subject" value=""/></td>
			</tr>
			<tr>
				<td><label>Message</label></td>
				<td>
					<textarea rows="" rows="40" cols="60" name="message" id="message"></textarea>
				</td>
			</tr>	
			<tr>
				<td colspan="2">
					<input class="log_for_input" type="button" value="Send Reply" name="" onClick="javascript:doSendEmail();"/>
				</td>
			</tr>
		</table>
		<input type="hidden" name="candidateEmail" value="${candidateEmail}" />
	</form>	
</div>
</body>
</html>