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
		messages[0] = "New job posted sucessfully!!!";
		messages[1] = "Job delete successfully!!!";
		
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
		
		function doDeleteJobs(employeeJobID){
			var r = confirm("Are you sure you want to delete this job?");
			if (r == true) {
				document.employerJobDelete.employerJobId.value = employeeJobID;
				document.employerJobDelete.submit();
			}
		}	

		function doEditJobs(employeeJobID){
			document.employerJobEdit.employerJobId.value = employeeJobID;
			document.employerJobEdit.submit();			
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
				<td class="heading-top"><h1>Manage Jobs</h1></td>
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
		<div class="post-resume-box" style="height:650px;" >
			<br/>
			<br/>
			<table border="0" cellpadding="5" cellspacing="5" align="center" width="900">
				<tr>
					<td width="190" class="top-resumes"><h3><b>Job Title</b></h3></td>
					<td width="170" class="top-resumes"><h3><b>Experience</b></h3></td>
					<td width="160" class="top-resumes"><h3><b>Salary</b></h3></td>
					<td width="190" class="top-resumes"><h3><b>Role</b></h3></td>
					<td width="190" class="top-resumes"><h3><b>Location</b></h3></td>
					<td width="50" class="top-resumes"><h3><b>EDIT</b></h3></td>
					<td width="50" class="top-resumes"><h3><b>DELETE</b></h3></td>
				</tr>		
				<c:forEach var="employerJob" items="${employerJobList}">
				<tr>
					<td width="190" class="top-resumes"><h3>${employerJob.employerJobTitle}</h3></td>
					<td width="170" class="top-resumes"><h3>${employerJob.expYear}.${employerJob.expMonth} Years</h3></td>
					<td width="160" class="top-resumes"><h3>${employerJob.salary} Lakhs</h3></td>
					<td width="190" class="top-resumes"><h3>${employerJob.employerJobRole}</h3></td>
					<td width="190" class="top-resumes"><h3>${employerJob.employerJobLocation}</h3></td>
					<td width="50" class="top-resumes"><h3><a href="javascript:doEditJobs('${employerJob.employerJobId}')"><img src="<c:url value="/resources/images/css.png" />" border="0"></a></h3></td>
					<td width="50" class="top-resumes"><h3><a href="javascript:doDeleteJobs('${employerJob.employerJobId}')"><img src="<c:url value="/resources/images/close.jpg" />" border="0"></a></h3></td>
				</tr>
				</c:forEach>		
			</table>
		</div>		
		<form name="employerJobDelete" id="employerJobDelete" action="/Job_Portal/deleteJob" method="post">
			<input type="hidden" name="employerJobId" value=""/>
		</form>	
		<form name="employerJobEdit" id="employerJobEdit" action="/Job_Portal/editJob" method="post">
			<input type="hidden" name="employerJobId" value=""/>
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
