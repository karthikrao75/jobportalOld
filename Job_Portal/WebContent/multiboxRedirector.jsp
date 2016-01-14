<%@ page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<script>
		function doRedirect() {			
			<c:choose> 
			<c:when test="${param.fun == 'employer_forgot_pwd'}">				
			document.frm.action = "/Job_Portal/employerForgotPassword";
			</c:when>	
			<c:when test="${param.fun == 'employee_forgot_pwd'}">				
			document.frm.action = "/Job_Portal/employeeForgotPassword";
			</c:when>	
			<c:when test="${param.fun == 'apply_job'}">	
			document.frm.employerJobId.value = 	${param.id};		
			document.frm.action = "/Job_Portal/applyJob";
			</c:when>	
			<c:when test="${param.fun == 'reply_cand'}">	
			document.frm.employerJobId.value = 	"${param.email}";		
			document.frm.action = "/Job_Portal/sendReply";
			</c:when>		
			<c:otherwise>			
			document.frm.action = "/Job_Portal/selectProfile";
			</c:otherwise>
			</c:choose>			
			document.frm.submit();
		}
		</script>
	</head>	
	<body onLoad="javascript:doRedirect()">
	<form name="frm" method="post" action="">
		<input type="hidden" name="employerJobId" value=""/>
	</form>	
	</body>
</html>