<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
			messages[0] = "Please select a profile to apply for this job!!!";
			messages[1] = "Your profile has been sent to the employer. He will contact you shortly!!!";	
			messages[2] = "Problem with your internet connection to send the profile to the employer!!!";						

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
			
			function doApplyJob(){				
				if ( $("#employeeProfile").val() == "0" ){
					viewMessage(0);
				}else{
					$.ajax({
	 		            url : 'applyByEmail',
	 		           data : "employerJobId=${employerJob.employerJobId}&employeeProfileId=" + $("#employeeProfile").val(),
	 		            success : function(data) { 		            	
							if( data == "0") {
	 		            		viewMessage(1);
		            		}else{
		            			viewMessage(2);		            		
		            		}
	 		           }	 		            
	 		        });
				}
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
				<td class="heading-top"><h1>Apply Job</h1></td>
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
		<div class="post-resume-box" style="height:800px;">		
			<br/><br/>						
			<table border="0" cellpadding="5" cellspacing="5" style="padding-left:150px;">
				<tr>
					<td width="150">
						<label><b>Job Title</b></label>
					</td>
					<td width="300" class="input">${employerJob.employerJobTitle}</td>
				</tr>
				<tr>
					<td width="150">
						<label><b>Company Name</b></label>
					</td>
					<td width="300" class="input" height="80">${employerJob.employer.companyName}</td>
				</tr>
				<tr>
					<td width="150">
						<label><b>Job Description</b></label>
					</td>
					<td width="300" class="input" height="80">${employerJob.employerJobDescr}</td>
				</tr>
				<tr>
					<td width="150">
						<label><b>Experience</b></label>
					</td>
					<td width="300" class="input" height="80">								
            			${employerJob.expYear}<label>Years</label>&nbsp;${employerJob.expMonth}<label>Months</label>           																																
					</td>
				</tr>
				<tr>
					<td width="150">
						<label><b>Salary</b></label>
					</td>
					<td width="300" class="input" height="80">			
						${employerJob.salary}
						<label>Lakhs per annum</label>																														
					</td>
				</tr>	
				<tr>
					<td width="150">
						<label><b>Job Role</b></label>
					</td>
					<td width="300" class="input" height="80">			
						${employerJob.employerJobRole}																													
					</td>
				</tr>
				<tr>
					<td width="150">
						<label><b>Job Location</b></label>
					</td>
					<td width="300" class="input" height="80">			
						${employerJob.employerJobLocation}																														
					</td>
				</tr>
				<tr>
				    <td width="150">
						<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i><b>Select Profile to Apply</b></label>
					</td>
					
					<td width="300" class="input" height="80">			
						<select name="employeeProfile" class="select_regn" id="employeeProfile">
            				<option value="0">Select</option>
            				<c:forEach var="employerProfile" items="${employeeProfilesList}">
								<option value="${employerProfile.employeeProfileId}"><c:out value='${employerProfile.employeeProfileTitle}'/></option>
							</c:forEach>																			
						</select>																														
					</td>
				</tr>	
				<tr>
					<td width="400" align="center" colspan="2">
						<input type="button" class="red-btn" value="Apply for this Job" onClick="javascript:doApplyJob();"/>											
					</td>							
				</tr>		
			</table>			
		</div>						
		<div class="clear"></div>  
	</div>
</div>
<div class="clear"></div>
<%@include file="footer.jsp" %>
<form name="applyJob" id="applyJob" method="post" action="/Job_Portal/applyByEmail">
	<input type="hidden" name="employerJobId" value="${employerJob.employerJobId}" />
</form>
</body>
</html>
