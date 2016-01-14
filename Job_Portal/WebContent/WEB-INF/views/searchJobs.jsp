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
		messages[0] = "Please enter a skill in keyword box to search jobs!!!";			
		messages[1] = "Min. Salary should not be greater than Max. Salary";		

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

 		function doSearchJobs(){
 			if( jQ('#primarySkills').val() == "" || jQ('#primarySkills').val()== "Enter keyword(Web Developer, Java Developer)") {
				viewMessage(0);
				jQ('#primarySkills').focus();
			}else if ( isMinExperienceGreater() ) {
				viewMessage(1);
			}else{
				jQ('#searchJobs').submit();
			}
 		}

 		function isMinExperienceGreater() {		
			if ( jQ('#minExperience').val() > jQ('#maxExperience').val() ){
				return true;
			}else{
				return false;
			}
		}

		function doResetLocation(){
			document.searchJobs.location.value = "";
		}

		function doSetLocation(){
			if ( jQ('#location').val() == "" ) {
				document.searchJobs.location.value = "City or State or Country";
			}
		}

		function doResetSkills(){
			document.searchJobs.primarySkills.value = "";
		}

		function doSetSkills(){
			if ( jQ('#primarySkills').val() == "" ) {
				document.searchJobs.primarySkills.value = "Enter keyword(Web Developer, Java Developer)";
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
		<td class="heading-top"><h1>Search Jobs</h1></td>
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
  <div class="search-jobs-box">
    <div class="left-box">
      <form name="searchJobs" id="searchJobs" action="/Job_Portal/listJobs" method="post">
        <input class="left-box-input" type="text" name="primarySkills" id="primarySkills" value="Enter keyword(Web Developer, Java Developer)" 
        onFocus="javascript:doResetSkills();" onBlur="javascript:doSetSkills();">
        <div class="form-block">
          <label><strong>Location</strong></label>
          <br />
          <input class="location-box-input" type="text" name="location" id="location" value="City or State or Country" onFocus="javascript:doResetLocation();" onBlur="javascript:doSetLocation();"/>          
        </div>
        <div class="form-block2">
          <label><strong>Experience:</strong></label>
          <br />
          <select name="experience" id="experience">
            <option value="">-select-</option>
	        <c:forEach var="i" begin="1" end="100" step="1" varStatus="loop">
    			<option value="${i}">${i} Years</option>
			</c:forEach>
          </select>
        </div>
        <div class="form-block2">
          <label><strong>Salary(min):</strong></label>
          <br />
          <select name="minSalary" id="minSalary">
            <option value="">-select-</option>
	        <c:forEach var="i" begin="1" end="100" step="1" varStatus="loop">
    			<option value="${i}">${i} Lakhs</option>
			</c:forEach>
          </select>
        </div>
        <div class="form-block2">
          <label><strong>Salary(max):</strong></label>
          <br />
          <select name="maxSalary" id="maxSalary">
            <option value="">-select-</option>
	        <c:forEach var="i" begin="1" end="100" step="1" varStatus="loop">
    			<option value="${i}">${i} Lakhs</option>
			</c:forEach>
          </select>
        </div>
        <div class="clear"></div>
        <input type="button" class="red-btn" value="Advance Search" onClick="javascript:doSearchJobs();"/>
      </form>
    </div>
    <div class="right-search"> <a href="#">&nbsp;</a> </div>
  </div>  
  <div class="clear"></div>
  <section>
    <div class="left-content">
      <div class="browse">
        <h2>Jobs</h2>        
        <div class="clear"></div>
      </div>
      <div class="filter-jobs">
        <div class="coulmn">
          <h2>Software</h2>
          <ul>
            <li><a href="#">PHP Developer<i>(312)</i></a></li>
            <li><a href="#">MySQL Developer<i>(12)</i></a></li>
            <li><a href="#">Articles Writter<i>(312)</i></a></li>
            <li><a href="#">Social Networking<i>(312)</i></a></li>
            <li><a href="#">Technical Writing<i>(312)</i></a></li>
            <li><a href="#">C# Programming<i>(312)</i></a></li>
            <li><a href="#">Joomla & Wordpress<i>(312)</i></a></li>
            <li><a href="#">Java/JRE Developer<i>(312)</i></a></li>
            <li><a href="#">PHP <i></i></a></li>
          </ul>
          <h3><a href="#">See all jobs</a></h3>
          <div class="clear"></div>
        </div>
        <div class="coulmn">
          <ul>
            <li><a href="#">PHP Developer<i>(312)</i></a></li>
            <li><a href="#">MySQL Developer<i>(12)</i></a></li>
            <li><a href="#">Articles Writter<i>(312)</i></a></li>
            <li><a href="#">Social Networking<i>(312)</i></a></li>
          </ul>
          <h2>Hardware/Networking</h2>
          <ul>
            <li><a href="#">PHP Developer<i>(312)</i></a></li>
            <li><a href="#">MySQL Developer<i>(12)</i></a></li>
            <li><a href="#">Articles Writter<i>(312)</i></a></li>
            <li><a href="#">Social Networking<i>(312)</i></a></li>
          </ul>
          <div class="clear"></div>
        </div>
        <div class="coulmn">
          <h2>SEO/Content</h2>
          <ul>
            <li><a href="#"> SEO/Contentr<i>(312)</i></a></li>
            <li><a href="#">Project Manager<i>(12)</i></a></li>
            <li><a href="#">Junior Support Staff<i>(312)</i></a></li>
            <li><a href="#">HR technical<i>(312)</i></a></li>
          </ul>
          <h2>UI/UX/Creative</h2>
          <ul>
            <li><a href="#">Graphic Designer<i>(312)</i></a></li>
            <li><a href="#">Creative Director<i>(12)</i></a></li>
         
          </ul>
          <div class="clear"></div>
        </div>
      </div>
    </div>
    <div class="right-content_2">
      <h1>Recruit the right <strong>Candidate</strong><br />
        for your <strong>Business</strong></h1>
      <ul>
        <li>Efficient and Cost-effective way to hire online</li>
        <li>Job Posting according to your talent needs and complete management system.</li>
        <li>Search the online database with more than millions of searchable resumes. </li>
        <li>Cutting edge Branding Solution fulfils all your advertising needs.</li>
      </ul>
      <div class="space"></div>
     <!-- <div > <a class="btn-big" href="#">Post Jobs Now</a> </div> -->
     </div>
  </section>
  <div class="clear"></div>  
</div>
</div>
<div class="clear"></div>
<%@include file="footer.jsp" %>
</body>
</html>
