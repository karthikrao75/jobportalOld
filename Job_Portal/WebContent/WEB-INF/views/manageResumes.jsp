<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<script type="text/javascript">
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
		messages[0] = "New Resume uploaded sucessfully!!!";
		messages[1] = "Resume delete successfully!!!";
		
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
		
		function doDeleteProfile(employeeProfileID){
			var r = confirm("Are you sure you want to delete this profile?");
			if (r == true) {
				document.employeeProfileDelete.employeeProfileId.value = employeeProfileID;
				document.employeeProfileDelete.submit();
			}
		}	

		function doEditProfile(employeeProfileID){
			document.employeeProfileEdit.employeeProfileId.value = employeeProfileID;
			document.employeeProfileEdit.submit();			
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
<%@include file="header.jsp" %>
<div class="content-warp">
	<div class="container">
		<table width="100%" cellspacing="0" cellpading="0">
			<tr height="30">
				<td class="heading-top"><h1>Manage Resumes</h1></td>
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
		<div class="post-resume-box">
			<br/>
			<br/>
			<table border="0" cellpadding="5" cellspacing="5" align="center" width="800">
				<tr>
					<td width="400" class="top-resumes"><h3><b>Profile Title</b></h3></td>
					<td width="400" class="top-resumes"><h3><b>Resume</b></h3></td>
					<td width="50" class="top-resumes"><h3><b>EDIT</b></h3></td>
					<td width="50" class="top-resumes"><h3><b>DELETE</b></h3></td>
				</tr>
				<c:forEach var="employeeProfile" items="${employeeProfilesList}">
				<tr>
					<td width="400" class="top-resumes"><h3>${employeeProfile.employeeProfileTitle}</h3></td>
					<td width="400" class="top-resumes"><a href="/Job_Portal/downloadResume?empProfileId=${employeeProfile.employeeProfileId}"><h3>${employeeProfile.resumePath}</h3></a></td>
					<td width="50" align="center" class="top-resumes"><a href="javascript:doEditProfile('${employeeProfile.employeeProfileId}')"><img src="<c:url value="/resources/images/css.png" />" border="0"></a></td>
					<td width="50" align="center" class="top-resumes"><a href="javascript:doDeleteProfile('${employeeProfile.employeeProfileId}')"><img src="<c:url value="/resources/images/close.jpg" />" border="0"></a></td>
				</tr>
				</c:forEach>
			</table>
		</div>		
		<form name="employeeProfileDelete" id="employeeProfileDelete" action="/Job_Portal/deleteProfile" method="post">
			<input type="hidden" name="employeeProfileId" value=""/>
		</form>	
		<form name="employeeProfileEdit" id="employeeProfileEdit" action="/Job_Portal/editProfile" method="post">
			<input type="hidden" name="employeeProfileId" value=""/>
		</form>			
		<div class="clear"></div>  
	</div>
</div>
<div class="clear"></div>
<%@include file="footer.jsp" %>
<c:if test="${not empty from_profile_form}">
<script type="text/javascript">
	viewMessage(0);
</script>	
</c:if>
<c:if test="${not empty from_delete_profile}">
	<script type="text/javascript">
		viewMessage(1);
	</script>	
</c:if>
</body>
</html>
