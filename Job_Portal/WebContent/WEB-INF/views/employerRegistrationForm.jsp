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
 			messages[8] = "Please enter address!!!";
 			messages[9] = "Please select position!!!";
 			messages[10] = "Please enter Company Industry!!!";
 			messages[11] = "Please select no. of employees!!!";
 			messages[12] = "Please enter conact number!!!";	
 			messages[13] = "Email already exists!!!";
 			messages[14] = "Mobile number already exists!!!";	
 			messages[15] = "Profie updated sucessfully!!!";
 			messages[15] = "Please enter company name!!!";

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
				if( !validateEmail($('#employerEmail').val())){
					viewMessage(0);
					$('#employerEmail').focus();
				}else if( $('#employerPassword').val() == "" ) {
					viewMessage(1);
					$('#employerPassword').focus();
				}else if( $('#employerFirstName').val() == "" ) {
					viewMessage(2);
					$('#employerFirstName').focus();
				}else if( $('#employerLastName').val() == "" ) {
					viewMessage(3);
					$('#employerLastName').focus();
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
				}else if( $('#employerAddress').val() == "" ) {
					viewMessage(8);
					$('#employerAddress').focus();
				}else if( $('#employerPosition').val() == "" ) {
					viewMessage(9);
					$('#employerPosition').focus();
				}else if( $('#employerIndustry').val() == "" ) {
					viewMessage(10);
					$('#employerIndustry').focus();
				}else if( $('#numberOfEmployees').val() == "" ) {
					viewMessage(11);
					$('#numberOfEmployees').focus();
				}else if( $('#companyName').val() == "" ) {
					viewMessage(16);
					$('#companyName').focus();
				}else if( $('#employerMobile').val() == "" ) {
					viewMessage(12);
					$('#employerMobile').focus();
				}else{
					checkEmployerExistsAndRegister();
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
 			 			
 			function checkEmployerExistsAndRegister(){
 				$.ajax({
 		           url : 'checkEmployerEmailExists',
 		           data : "email=" + $("#employerEmail").val(),
 		            success : function(data) {
 		            	if( data == "1") {
 	 		            	viewMessage(13);
 	 		            	$('#divMessage').focus();
 		            	}else{
 		            		$.ajax({
 		    		            url : 'checkEmployerMobileExists',
 		    		           data : "mobile=" + $("#employerMobile").val(),
 		    		            success : function(data) {							            	
	 		            			if( data == "1") {
	 		 	 		            	viewMessage(14);
	 		 	 		            	$('#divMessage').focus();
	 		 		            	}else{
	 		 		            		hideMessage();
	 		 							$('#employer').submit();	
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
	<%@include file="employerHeader.jsp" %>
	<div class="content-warp">
		<div class="container">
			
			<table width="100%" cellspacing="0" cellpading="0">
				<tr height="30">
					<td class="heading-top"><h1>Employer Registration</h1></td>
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
  			<div class="registration-box" style="height:690px;">
  				<br/>  	
  				<form:form method="POST" action="/Job_Portal/addEmployer" commandName="employer" name="employer" >						
  				<div class="row" align="center">	          			
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Email ID</label>
	            		<form:input path="employerEmail" cssClass="input-text"/>
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Password</label>
	            		<form:password path="employerPassword" cssClass="input-text"  showPassword="true" />          			
	        		</div>        		
	    			<div class="row" align="center">	          			
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>First Name</label>
	            		<form:input path="employerFirstName" cssClass="input-text" />
	            		<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Last Name</label>
	           			<form:input path="employerLastName" cssClass="input-text" />         			
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
	          			
	          				<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Employer Address</label>
	            			<form:input path="employerAddress" cssClass="input-text" />  
	            			<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Employer Position</label>
	            			<form:input path="employerPosition" cssClass="input-text" />            				            			        			
	          			
	        		</div>
	        		<div class="row" align="center">	          			
          				<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Company Industry</label>
            			<form:input path="employerIndustry" cssClass="input-text" />  
            			<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>No. of Employees</label>
            			<form:input path="numberOfEmployees" cssClass="input-text" />
            		</div>	
            		<div class="row" align="center">	          			
          				<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Company Name</label>
            			<form:input path="companyName" cssClass="input-text" />  
            			<label><i><img src="<c:url value="/resources/images/star.gif" />" /></i>Contact Number</label>
            			<form:input path="employerMobile" cssClass="input-text" />
            		</div>       		
	        		<div class="row" align="center">
	        			<input type="button" class="red-btn" value="Register" onClick="javascript:doRegister();"/>
	        		</div>	        		
	        	</form:form>
  			</div>  			    
		</div>
	</div>
	<div class="clear"></div>	
	<%@include file="footer.jsp" %>
	</body>
</html>
