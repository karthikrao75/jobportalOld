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
			messages[0] = "Please enter job title!!!";
			messages[1] = "Please enter job description!!!";
			messages[2] = "Please select years of experience!!!";			
			messages[3] = "Please enter salary!!!";
			messages[4] = "Please enter job role";	
			messages[5] = "Please enter job location";	
			messages[6] = "Job updated successfully!!!";	
			messages[7] = "Salary should be numeric!!!";				

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
			
			function doPostJob() {
				if( $('#employerJobTitle').val() == "" ) {
					viewMessage(0);
					$('#employerJobTitle').focus();
				}else if( $('#employerJobDescr').val() == "" ) {
					viewMessage(1);
					$('#employerJobDescr').focus();
				}else if( $('#expYear').val() == "0" ) {
					viewMessage(2);
					$('#expYear').focus();
				}else if( $('#salary').val() == "" ) {
					viewMessage(3);
					$('#salary').focus();
				}else if(!$.isNumeric( $('#salary').val() )) {
					viewMessage(7);
					$('#salary').focus();
				}else if( $('#employerJobRole').val() == "" ) {
					viewMessage(4);
					$('#employerJobRole').focus();
				}else if( $('#employerJobLocation').val() == "" ) {
					viewMessage(5);
					$('#employerJobLocation').focus();
				}else{
					hideMessage();
					$('#employerJob').submit();
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
<%@include file="employerHeader.jsp" %>
<div class="content-warp">
	<div class="container">
		<table width="100%" cellspacing="0" cellpading="0">
			<tr height="30">
				<td class="heading-top"><h1>Post Jobs</h1></td>
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
		<div class="post-resume-box" style="height:650px;">
			<form:form method="post" action="/Job_Portal/addEmployerJob" commandName="employerJob" name="employerJob">					
				<table border="0" cellpadding="5" cellspacing="5" align="center">
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Job Title</label>
						</td>
						<td width="300" class="input">
							<form:input path="employerJobTitle" cssClass="input-text" width="250" />												
						</td>
					</tr>
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Job Description</label>
						</td>
						<td width="300" class="input" height="80">								
							<form:textarea path="employerJobDescr" rows="8" cols="100"/>																											
						</td>
					</tr>
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Experience</label>
						</td>
						<td width="300" class="input" height="80">			
							<label>
	            				<i><img src="<c:url value="/resources/images/star.gif" />" /></i>
	            				Years: 
	            			</label>
	            			<form:select path="expYear" id="expYear" cssClass="select_exp">
	            				<form:option value="0">0</form:option>
	            				<c:forEach var="i" begin="1" end="100" step="1" varStatus="loop">
    								<form:option value="${i}">${i}</form:option>
								</c:forEach>	            																							
							</form:select> 
							<label>	            				
	            				Months: 
	            			</label>
	            			<form:select path="expMonth" id="expMonth" cssClass="select_exp">
	            				<form:option value="0">0</form:option>
	            				<c:forEach var="i" begin="1" end="11" step="1" varStatus="loop">
    								<form:option value="${i}">${i}</form:option>
								</c:forEach>																			
							</form:select> 																														
						</td>
					</tr>
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Salary</label>
						</td>
						<td width="300" class="input" height="80">			
							<form:input path="salary" cssClass="input-text" width="250" />
							<label>Lakhs per annum</label>																														
						</td>
					</tr>	
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Job Role</label>
						</td>
						<td width="300" class="input" height="80">			
							<form:input path="employerJobRole" cssClass="input-text" width="250" />																														
						</td>
					</tr>
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Job Location</label>
						</td>
						<td width="300" class="input" height="80">			
							<form:input path="employerJobLocation" cssClass="input-text" width="250" />																														
						</td>
					</tr>					
					<tr>
						<td width="400" align="center" colspan="2">
							<c:choose>
								<c:when test="${not empty employerJob.employerJobId}">
									<input type="button" class="red-btn" value="Update Job" onClick="javascript:doPostJob();"/>
								</c:when>
								<c:otherwise>					
									<input type="button" class="red-btn" value="Post Job" onClick="javascript:doPostJob();"/>			
								</c:otherwise>
							</c:choose>							
						</td>							
					</tr>
				</table>				
				<form:hidden path="employerJobId"/>					
			</form:form>
		</div>		
				
		<div class="clear"></div>  
	</div>
</div>
<div class="clear"></div>
<%@include file="footer.jsp" %>
<c:if test="${not empty from_profile_form_update}">
<script type="text/javascript">
	viewMessage(6);
</script>	
</c:if>
</body>
</html>
