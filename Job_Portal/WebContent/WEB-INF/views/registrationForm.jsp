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
 			messages[0] = "Please enter a valid email!!!";
 			messages[1] = "Please enter password!!!";
 			messages[2] = "Please enter First Name!!!";
 			messages[3] = "Please enter Last Name!!!";	
 			messages[4] = "Please select country!!!";
 			messages[5] = "Please select state!!!";
 			messages[6] = "Please select city!!!";
 			messages[7] = "Please enter postal code!!!";
 			messages[8] = "Please enter job category!!!";
 			messages[9] = "Please select education level!!!";
 			messages[10] = "Please enter Current Job Title!!!";
 			messages[11] = "Please select job level!!!";
 			messages[12] = "Please enter mobile number!!!";	
 			messages[13] = "Email already exists!!!";
 			messages[14] = "Mobile number already exists!!!";
 			messages[15] = "Please enter Primary Skills!!!";
 			messages[16] = "Please select Years of experience!!!";	

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

			function doRegister() {				
				if( !validateEmail($('#employeeEmail').val())){
					viewMessage(0);
					$('#employeeEmail').focus();
				}else if( $('#employeePassword').val() == "" ) {
					viewMessage(1);
					$('#employeePassword').focus();
				}else if( $('#employeeFirstName').val() == "" ) {
					viewMessage(2);
					$('#employeeFirstName').focus();
				}else if( $('#employeeLastName').val() == "" ) {
					viewMessage(3);
					$('#employeeLastName').focus();
				}else if( $('#country.countryId').val() == "0" ) {
					viewMessage(4);
					$('#country.countryId').focus();
				}else if( $('#state.stateId').val() == "0" ) {
					viewMessage(5);
					$('#state.stateId').focus();
				}else if( $('#city.cityId').val() == "0" ) {
					viewMessage(6);
					$('#city.cityId').focus();
				}else if( $('#postalCode').val() == "" ) {
					viewMessage(7);
					$('#postalCode').focus();
				}else if( $('#jobCategory').val() == "" ) {
					viewMessage(8);
					$('#jobCategory').focus();
				}else if( $('#educationLevel.educationLevelId').val() == "0" ) {
					viewMessage(9);
					$('#educationLevel.educationLevelId').focus();
				}else if( $('#currentJobTitle').val() == "" ) {
					viewMessage(10);
					$('#currentJobTitle').focus();
				}else if( $('#jobLevel.jobLevelId').val() == "0" ) {
					viewMessage(11);
					$('#jobLevel.jobLevelId').focus();
				}else if( $('#employeePrimarySkills').val() == "" ) {
					viewMessage(15);
					$('#employeeMobile').focus();
				}else if( $('#employeeYearExp').val() == "0" ) {
					viewMessage(16);
					$('#employeeMobile').focus();
				}else if( $('#employeeMobile').val() == "" ) {
					viewMessage(12);
					$('#employeeMobile').focus();
				}else{
					checkCandidateExistsAndRegister();
				}	
				
			}
 			function doPopulateState() { 	
 	 			resetCity();			
 	 			$.ajax({
 		            url : 'getStateList',
 		           data : "countryId=" + document.getElementById("country.countryId").value + "&stateId=",
 		            success : function(data) { 		            	
 						document.getElementById("state.stateId").innerHTML=data;
 		            }
 		        });
 			}

 			function resetCity(){
 				$('#city.cityId').html("<option value='0'>Select</option>");
 			}

 			function doPopulateCity() { 	 						
 				$.ajax({
 		            url : 'getCityList',
 		           data : "stateId=" + document.getElementById("state.stateId").value + "&cityId=",
 		            success : function(data) { 		            	
 						document.getElementById("city.cityId").innerHTML=data;
 		            }
 		        });
 			}

 			function checkCandidateExistsAndRegister(){
 				$.ajax({
 		           url : 'checkEmailExists',
 		           data : "email=" + $("#employeeEmail").val(),
 		            success : function(data) {
 		            	if( data == "1") {
 	 		            	viewMessage(13);
 	 		            	$('#divMessage').focus();
 		            	}else{
 		            		$.ajax({
 		    		            url : 'checkMobileExists',
 		    		           data : "mobile=" + $("#employeeMobile").val(),
 		    		            success : function(data) {							            	
	 		            			if( data == "1") {
	 		 	 		            	viewMessage(14);
	 		 	 		            	$('#divMessage').focus();
	 		 		            	}else{
	 		 		            		hideMessage();
	 		 							$('#employee').submit();	
	 		 		            	}
 		    		            }
 		    		        });
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
					<td class="heading-top"><h1>Registration</h1></td>
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
  				<form:form method="POST" action="/Job_Portal/addEmployee" commandName="employee" name="employee" >
	    			<div class="row" align="center">	          			
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Email ID</label>
	            		<form:input path="employeeEmail" cssClass="input-text" />
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Password</label>
	            		<form:password path="employeePassword" cssClass="input-text" />          			
	        		</div>        		
	    			<div class="row" align="center">	          			
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>First Name</label>
	            		<form:input path="employeeFirstName" cssClass="input-text" />
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Last Name</label>
	           			<form:input path="employeeLastName" cssClass="input-text" />         			
	          		</div>        		
	    			<div class="row" align="center">
	          			
	            			<label>
	            				<i><img src="<c:url value="/resources/images/star.gif" />" /></i>
	            				Country
	            			</label>
	            			<form:select path="country.countryId" cssClass="select_regn" id="country.countryId" onchange="javascript:doPopulateState()" >
	            				<form:option value="0">Select</form:option>
	            				<c:forEach var="country" items="${countryList}">
									<form:option value="${country.countryId}"><c:out value='${country.countryName}'/></form:option>
								</c:forEach>																			
							</form:select>
							<label>
	            				<i><img src="<c:url value="/resources/images/star.gif" />" /></i>
	            				State
	            			</label>
	            			<form:select path="state.stateId" cssClass="select_regn" id="state.stateId" onchange="javascript:doPopulateCity()">
								<form:option value="">Select</form:option>											
							</form:select>           			        			
	          			
	        		</div>
	        		<div class="row" align="center">
	          			
	            			<label>
	            				<i><img src="<c:url value="/resources/images/star.gif" />" /></i>
	            				City
	            			</label>
	            			<form:select path="city.cityId" cssClass="select_regn" id="city.cityId">
								<form:option value="">Select</form:option>											
							</form:select> 	  
							<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Postal Code</label>
	            			<form:input path="postalCode" cssClass="input-text" />            			         			
	          			
	        		</div>
	        		<div class="row" align="center">
	          			
	          				<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Job Category</label>
	            			<form:input path="jobCategory" cssClass="input-text" />  
	            			<label>
	            				<i><img src="<c:url value="/resources/images/star.gif" />" /></i>
	            				Education Level
	            			</label>
	            			<form:select path="educationLevel.educationLevelId" id="educationLevel.educationLevelId" cssClass="select_regn">
	            				<form:option value="0">Select</form:option>
	            				<c:forEach var="educationLevel" items="${educationLevelList}">
									<form:option value="${educationLevel.educationLevelId}"><c:out value='${educationLevel.educationLevelName}'/></form:option>
								</c:forEach>																			
							</form:select>            				            			        			
	          			
	        		</div>
	        		<div class="row" align="center">
	          			
	          				<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Current Job Title</label>
	            			<form:input path="currentJobTitle" cssClass="input-text" />  
	            			<label>
	            				<i><img src="<c:url value="/resources/images/star.gif" />" /></i>
	            				Job Level
	            			</label>
	            			<form:select path="jobLevel.jobLevelId" id="jobLevel.jobLevelId" cssClass="select_regn">
	            				<form:option value="0">Select</form:option>
	            				<c:forEach var="jobLevel" items="${jobLevelList}">
									<form:option value="${jobLevel.jobLevelId}"><c:out value='${jobLevel.jobLevelName}'/></form:option>
								</c:forEach>																			
							</form:select>              			      			
	          			
	        		</div>
	        		<div class="row" align="center">
	          			
	          				<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Primary Skills</label>
	            			<form:input path="employeePrimarySkills" cssClass="input-text" />  
	            			<label>	            				
	            				Experience: 
	            			</label>
	            			<label>
	            				<i><img src="<c:url value="/resources/images/star.gif" />" /></i>
	            				Years: 
	            			</label>
	            			<form:select path="employeeYearExp" id="employeeYearExp" cssClass="select_exp">
	            				<form:option value="0">0</form:option>
	            				<c:forEach var="i" begin="1" end="100" step="1" varStatus="loop">
    								<form:option value="${i}">${i}</form:option>
								</c:forEach>	            																							
							</form:select> 
							<label>	            				
	            				Months: 
	            			</label>
	            			<form:select path="employeeMonthExp" id="employeeMonthExp" cssClass="select_exp">
	            				<form:option value="0">0</form:option>
	            				<c:forEach var="i" begin="1" end="11" step="1" varStatus="loop">
    								<form:option value="${i}">${i}</form:option>
								</c:forEach>																			
							</form:select>      			
	          			
	        		</div>
	        		<div class="row" align="center">	          			       				
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Contact Number</label>
	            		<form:input path="employeeMobile" cssClass="input-text" />         			
	        		</div>
	        		<div class="row" align="center">
	        			<input type="button" class="red-btn" value="Create Account" onClick="javascript:doRegister();"/>
	        		</div>
	        	</form:form>
  			</div>  			    
		</div>
	</div>
	<div class="clear"></div>	
	<%@include file="footer.jsp" %>
	</body>
</html>
