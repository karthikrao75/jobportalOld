<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Your Jobs</title>
	<!-- html 5 jquery -->
	<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
	<script>var jQ = jQuery.noConflict();</script>
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
		messages[0] = "Please enter a skill in keyword box to search candidates!!!";
		messages[1] = "Min. Experience should not be greater than Max. Experience";	
		messages[2] = "Min. Salary should not be greater than Max. Salary";		

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
		
		function doSearchCandidates(){
			if( jQ('#primarySkills').val() == "" || jQ('#primarySkills').val()== "Enter keyword(Java J2EE)") {
				viewMessage(0);
				jQ('#primarySkills').focus();
			}else if ( isMinExperienceGreater() ) {
				viewMessage(1);
			}else{
				jQ('#searchCandidates').submit();
			}
		}	

		function isMinExperienceGreater() {		
			if ( jQ('#minExperience').val() > jQ('#maxExperience').val() ){
				return true;
			}else{
				return false;
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
		<td class="heading-top"><h1>Search Candidates</h1></td>
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
  <div class="search-box">
    <div class="left-box">
      <form name="searchCandidates" action="/Job_Portal/listCandidates" method="post" id="searchCandidates">
        <input class="left-box-input" type="text" name="primarySkills" id="primarySkills" value="Enter keyword(Java J2EE)">       
        <div class="form-block2">
          <label><strong>Experience(min):</strong></label>
          <br />
          <select name="minExperience" id="minExperience">
            <option value="">-select-</option>
	        <c:forEach var="i" begin="1" end="100" step="1" varStatus="loop">
    			<option value="${i}">${i} Years</option>
			</c:forEach>
          </select>
        </div>
        <div class="form-block2">
          <label><strong>Experience(max):</strong></label>
          <br />
          <select name="maxExperience" id="maxExperience">
            <option value="">-select-</option>
	        <c:forEach var="i" begin="1" end="100" step="1" varStatus="loop">
    			<option value="${i}">${i} Years</option>
			</c:forEach>
          </select>
        </div>   <br/>     
        <div class="clear"></div>
         <input type="button" class="red-btn" value="Search Candidates" onClick="javascript:doSearchCandidates();"/>
      </form>
    </div>
    <div class="right-search"> <a href="#">&nbsp;</a> </div>
  </div>
  <div class="bottom-img"><img src="<c:url value="/resources/images/sraech-bg.gif" />" width="1006" height="14" alt="" /></div>
  <div class="clear"></div>  
  <div class="clear"></div>  
</div>
</div>
<div class="clear"></div>
<%@include file="footer.jsp" %>
</body>
</html>
