<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
			<div class="container">
				<div class="logo" height="78"  width="228">
					<a href="#"> <img src="<c:url value="/resources/images/logo.jpg" />" width="228" height="78" alt="" /> </a> 
				</div>
				<c:choose>
					<c:when test="${not empty sessionScope.employee_login_id}">
						<div class="loggedin_userarea">
							<a class="register" href="#">Welcome!</a> <a class="sign-in" href="employeeLogout">Sign out</a> 
							<div class="clear"></div>							
						</div>					
					</c:when>
					<c:otherwise>
						<div class="userarea">
							<a class="register" href="registrationForm">Register</a> <a class="sign-in" href="employeeLogin">Sign in</a> 
							<div class="clear"></div>							
						</div>						
					</c:otherwise>
				</c:choose>				
				<div class="clear"></div>
				<div class="navigation-wrap">
					<nav>
					<c:choose>
						<c:when test="${not empty sessionScope.employee_login_id}">
						<ul>
							<li><a class="current" href="searchJobs">Home</a></li>
							<li><a href="searchJobs">Search Jobs</a></li>
							<li><a href="postResume">Post Resume</a></li>
							<li><a href="manageResumes">Manage Resumes</a></li>
							<li><a href="showEmployeeProfile">My Profile</a></li>
						</ul>
						</c:when>
						<c:otherwise>
						<ul>
							<li><a class="current" href="searchJobs">Home</a></li>
							<li><a href="searchJobs">Search Jobs</a></li>
							<li><a href="postResume">Post Resume</a></li>
							<li><a href="showEmployeeProfile">My Profile</a></li>
							<li><a href="employerLogin">Employer's Zone</a></li>
						</ul>
						</c:otherwise>
					</c:choose>					
					</nav>
				<div class="clear"></div>
			</div>
		</div>
	</header>