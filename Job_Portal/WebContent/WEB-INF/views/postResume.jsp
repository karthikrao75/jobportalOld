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
			messages[0] = "Please enter profile title!!!";
			messages[1] = "Please enter covering letter!!!";
			messages[2] = "Please select resume!!!";
			messages[3] = "Resume should be .doc or .docx file";	
			messages[4] = "Resume profile updated successfully!!!";	
			messages[5] = "You can post only 5 Resumes. Please delete any resumes in Manage Resume Section!!!";		

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
			
			function doPostResume() {
				if( $('#employeeProfileTitle').val() == "" ) {
					viewMessage(0);
					$('#employeeProfileTitle').focus();
				}else if( $('#profileCoveringLetter').val() == "" ) {
					viewMessage(1);
					$('#profileCoveringLetter').focus();
				}else if( $('#resumeFile').val() == "" && $('#resumePath').val() == "" ) {
					viewMessage(2);					
				}else {
					// get the file name, possibly with path (depends on browser)
			        var filename = $("#resumeFile").val();
			        if ( $("#resumeFile").val() == "" ){
				        filename = $('#resumePath').val();
			        }
			        // Use a regular expression to trim everything before final dot
			        var extension = filename.replace(/^.*\./, '');
			        if ( extension == "doc" || extension == "docx" ){
				        if ( $("#employeeProfileId").val() == "" ) {
				        	check5ProfilesUploaded();		
				        }else{
				        	hideMessage();
				        	$('#employeeProfile').submit();
				        }		        	
			        }else{
				       viewMessage(3);
			        }			        
				}	
			}

			function check5ProfilesUploaded(){
 				$.ajax({
 		           url : 'checkFiveProfilesUploaded',
 		           success : function(data) {
		           	if( data == "1") {
	 		            	viewMessage(5);	 		            	
		            	}else{
		            		hideMessage();
				        	$('#employeeProfile').submit();
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
<%@include file="header.jsp" %>
<div class="content-warp">
	<div class="container">
		<table width="100%" cellspacing="0" cellpading="0">
			<tr height="30">
				<td class="heading-top"><h1>Post Resume</h1></td>
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
			<form method="post" action="/Job_Portal/addEmployeeProfile" enctype="multipart/form-data" name="employeeProfile" id="employeeProfile">					
				<table border="0" cellpadding="5" cellspacing="5" align="center">
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Profile Title</label>
						</td>
						<td width="300" class="input">
							<input type="text" name="employeeProfileTitle" id="employeeProfileTitle" class="input-text" width="250" value="${employeeProfile.employeeProfileTitle}" />						
						</td>
					</tr>
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Covering Letter</label>
						</td>
						<td width="300" class="input" height="80">
							<textarea name="profileCoveringLetter" rows="8" cols="100" id="profileCoveringLetter">${employeeProfile.profileCoveringLetter}</textarea>																				
						</td>
					</tr>
					<tr>
						<td width="100">
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Select Resume</label>
						</td>
						<td width="300" class="input" height="40">
							<table border="0" cellspacing="3" cellpadding="3">
								<tr>
									<td><input type="file" name="resumeFile" id="resumeFile" width="150" /></td>
									<td width="150">
										<a href="/Job_Portal/downloadResume?empProfileId=${employeeProfile.employeeProfileId}">${employeeProfile.resumePath}</a>
									</td>		
								</tr>								
							</table>									
																		
						</td>
					</tr>
					<tr>
						<td width="400" align="center" colspan="2">
							<c:choose>
								<c:when test="${not empty employeeProfile.employeeProfileId}">
									<input type="button" class="red-btn" value="Update Resume" onClick="javascript:doPostResume();"/>
								</c:when>
								<c:otherwise>					
									<input type="button" class="red-btn" value="Post Resume" onClick="javascript:doPostResume();"/>			
								</c:otherwise>
							</c:choose>							
						</td>							
					</tr>
				</table>				
				<input type="hidden" name="resumePath" id="resumePath" value="${employeeProfile.resumePath}"/>
				<input type="hidden" name="employeeProfileId" id="employeeProfileId" value="${employeeProfile.employeeProfileId}"/>				
			</form>
		</div>		
				
		<div class="clear"></div>  
	</div>
</div>
<div class="clear"></div>
<%@include file="footer.jsp" %>
<c:if test="${not empty from_profile_form_update}">
<script type="text/javascript">
	viewMessage(4);
</script>	
</c:if>
</body>
</html>
