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
				<td class="heading-top"><h1>List Jobs</h1></td>
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
		<div class="registration-box">
			<br/>
			<br/>
			<table border="0" cellpadding="5" cellspacing="5" align="center" width="800">
				<tr>
					<td width="200" class="top-resumes"><h3><b>Job Title</b></h3></td>
					<td width="200" class="top-resumes"><h3><b>Job Location</b></h3></td>
					<td width="200" class="top-resumes"><h3><b>Role</b></h3></td>
					<td width="200" class="top-resumes"><h3><b>Experience</b></h3></td>
					<td width="200" class="top-resumes"><h3><b>Company Name</b></h3></td>	
					<td width="125" class="top-resumes"><h3><b>Salary</b></h3></td>					
				</tr>	
				<c:forEach var="employerJob" items="${jobsList}">
					<tr>
						<td class="top-resumes">${employerJob.employerJobTitle}</td>
						<td class="top-resumes">${employerJob.employerJobLocation}</td>
						<td class="top-resumes">${employerJob.employerJobRole}</td>
						<td class="top-resumes">${employerJob.expYear} Years</td>
						<td class="top-resumes">${employerJob.employer.companyName} </td>
						<td class="top-resumes">${employerJob.salary} Lakhs</td>
					</tr>
				</c:forEach>			
			</table><br><br/>
			<table border="0" cellpadding="0" cellspacing="0" align="center" width="100">
				<tr>
					<td><a href="/Job_Portal/searchJobs">
						<input type="button" class="red-btn" value="Back to Search"/>
						</a>
					</td>
				</tr>
			</table>
		</div>				
		<div class="clear"></div>  
	</div>
</div>
<div class="clear"></div>
<%@include file="footer.jsp" %>
</body>
</html>
