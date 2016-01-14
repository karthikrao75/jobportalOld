package com.job.portal.web.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.portal.domain.AppliedCandidates;
import com.job.portal.domain.Employee;
import com.job.portal.domain.EmployeeProfile;
import com.job.portal.domain.Employer;
import com.job.portal.domain.EmployerJob;
import com.job.portal.mail.MailUtility;
import com.job.portal.service.EmployeeProfileService;
import com.job.portal.service.EmployerJobService;
import com.job.portal.service.EmployerService;

@Controller
public class JobsController {
	
	@Autowired(required=true)
    @Qualifier(value="employerJobService")
	private EmployerJobService employerJobService;	
	
	@Autowired(required=true)
    @Qualifier(value="employerService")
    private EmployerService employerService;	
	
	@Autowired(required=true)
    @Qualifier(value="employeeProfileService")
    private EmployeeProfileService employeeProfileService;	
	
	/**
	 * @return the employerJobService
	 */
	public EmployerJobService getEmployerJobService() {
		return employerJobService;
	}

	/**
	 * @param employerJobService the employerJobService to set
	 */
	public void setEmployerJobService(EmployerJobService employerJobService) {
		this.employerJobService = employerJobService;
	}	

	/**
	 * @return the employerService
	 */
	public EmployerService getEmployerService() {
		return employerService;
	}

	/**
	 * @param employerService the employerService to set
	 */
	public void setEmployerService(EmployerService employerService) {
		this.employerService = employerService;
	}		

	/**
	 * @return the employeeProfileService
	 */
	public EmployeeProfileService getEmployeeProfileService() {
		return employeeProfileService;
	}

	/**
	 * @param employeeProfileService the employeeProfileService to set
	 */
	public void setEmployeeProfileService(
			EmployeeProfileService employeeProfileService) {
		this.employeeProfileService = employeeProfileService;
	}

	@RequestMapping(value= "/searchJobs", method = RequestMethod.GET)
    public String showSearchJobs(Model model){      	
    	return "searchJobs";        
    }
	
	@RequestMapping(value= "/advanceSearch", method = RequestMethod.GET)
    public String showAdvanceSearch(Model model){      	
    	return "advanceSearch";        
    }
	
	@RequestMapping(value= "/postJobs", method = RequestMethod.GET)
    public String postJobs(Model model, HttpServletRequest request){      	
		HttpSession session = request.getSession();
		if( session.getAttribute("employer_login_id") != null) {
			model.addAttribute("employerJob", new EmployerJob());
			return "postJobs";
		}else{
			return "employerLogin";
		}       
    }
	
	@RequestMapping(value= "/manageJobs")
    public String manageResumes(HttpServletRequest request, Model model){	
		Integer employerId = Integer.parseInt((String)request.getSession().getAttribute("employer_login_id"));		
		List<EmployerJob> employerJobList = employerJobService.listEmployerJobByEmployerId(employerId);
		model.addAttribute("employerJobList", employerJobList);    	
    	return "manageJobs";        
    }
	
	@RequestMapping(value= "/addEmployerJob", method = RequestMethod.POST)
    public String addEmployerJob(@ModelAttribute("employerJob") EmployerJob employerJob, 
    		Model model, HttpServletRequest request) {	
		
		String redirectURL = "redirect:manageJobs";		
		Integer employerId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("employer_login_id")));
		Employer employer = employerService.getEmployerById(employerId);	
		employerJob.setEmployer(employer);			
		if(employerJob.getEmployerJobId() != null) {
			model.addAttribute("from_profile_form_update", "1");
			model.addAttribute("employerJob", employerJob);
			redirectURL = "postJobs";
		}else{
			model.addAttribute("from_profile_form", "1");
		}		
		employerJobService.addEmployerJob(employerJob);		
		return redirectURL;       
    }
	
	@RequestMapping(value= "/deleteJob") 
	public String deleteProfile(HttpServletRequest request, Model model) { 		
		Integer employerJobId = Integer.parseInt((String)request.getParameter("employerJobId"));
		employerJobService.removeEmployerJob(employerJobId);		
		model.addAttribute("from_delete_profile", "1");
		return "redirect:manageJobs";		
	}
	
	@RequestMapping(value= "/editJob") 
	public String editJob(HttpServletRequest request, Model model) { 		
		Integer employerJobId = Integer.parseInt((String)request.getParameter("employerJobId"));
		EmployerJob employerJob = employerJobService.getEmployerJobById(employerJobId);		
		model.addAttribute("employerJob", employerJob);	
		return "postJobs";		
	}
	
	@RequestMapping(value= "/listJobs", method = RequestMethod.POST)
    public String listJobs(Model model, HttpServletRequest request){		
			
		String primarySkills = request.getParameter("primarySkills") == null ? "" : request.getParameter("primarySkills");
		String location = (request.getParameter("location") == null || ("City or State or Country".equals(request.getParameter("location"))))
					? "" : request.getParameter("location");		
		String experience = request.getParameter("experience") == null ? "" : request.getParameter("experience");
		String minSalary = request.getParameter("minSalary") == null ? "" : request.getParameter("minSalary");
		String maxSalary = request.getParameter("maxSalary") == null ? "" : request.getParameter("maxSalary");	
			
		List<EmployerJob> jobsList = employerJobService.listJobs(primarySkills, location, experience, minSalary, maxSalary);
		model.addAttribute( "jobsList", jobsList);
		
		if ( request.getSession().getAttribute("employee_login_id") != null ){
			return "listJobsAndApply";
		}		
		
		return "listJobs";        
		
    }
	
	@RequestMapping(value= "/applyJob")
    public String applyJob(Model model, HttpServletRequest request){		
		HttpSession session = request.getSession();		
		String employeeId = (String)session.getAttribute("employee_login_id");
		String employerJobId = (String)request.getParameter("employerJobId");		
		List<EmployeeProfile> employeeProfilesList = employeeProfileService.listEmployeeProfileByEmployeeId(Integer.parseInt(employeeId));		
		EmployerJob employerJob = employerJobService.getEmployerJobById(Integer.parseInt(employerJobId));		
		model.addAttribute("employeeProfilesList", employeeProfilesList);
		model.addAttribute("employerJob", employerJob);
    	return "applyJob";        
    }
	
	@RequestMapping(value= "/applyByEmail")
    public @ResponseBody String applyByEmail(@RequestParam(value = "employerJobId") String employerJobId, 
    		@RequestParam(value = "employeeProfileId") String employeeProfileId){		
		String response = "0";
		
		EmployerJob employerJob = employerJobService.getEmployerJobById(Integer.parseInt(employerJobId));
		EmployeeProfile employeeProfile = employeeProfileService.getEmployeeProfileById(Integer.parseInt(employeeProfileId));
		
		System.out.println ( " Employer Job >> " + employerJob );
		System.out.println ( " Employee Profile >> " + employeeProfile );
		
		File attachFile = new File("E:\\Uma\\Project\\UPLOADED_FILES\\" 
				+ employeeProfile.getEmployee().getEmployeeId() + "\\" + 
				employeeProfile.getResumePath());
		
		String subject = employeeProfile.getEmployee().getEmployeeFirstName() + " applied for " + employerJob.getEmployerJobTitle();
		StringBuffer mailContent = new StringBuffer();
		
		mailContent.append("<html><body>");
		mailContent.append("<span>Dear Employer,</span><br/>");
		mailContent.append("<span>" + employeeProfile.getEmployee().getEmployeeFirstName() + " has applied for your job vacancy for the title " +
				employerJob.getEmployerJobTitle() + "</span><br/>");
		mailContent.append("<span> Please check the attached resume and contact him if his/her profile suits your job vacancy </span><br/>");
		mailContent.append("<br/><br/><span>Yours Sincerely,</span><br/>");
		mailContent.append("<br/><br/><span>Your Jobs</span><br/>");
		mailContent.append("</body></html>");		
		
		AppliedCandidates appliedCandidates = new AppliedCandidates();
		appliedCandidates.setEmployeeProfile(employeeProfile);
		appliedCandidates.setEmployerJob(employerJob);
		employerJobService.addAppliedCandidates(appliedCandidates);
		
		MailUtility mailUtility = new MailUtility(employerJob.getEmployer().getEmployerEmail(), subject, mailContent.toString());
		if(!mailUtility.sendWithAttachment(attachFile)){		
			response = "1";
		}		
		return response;
    }
	
	@RequestMapping(value= "/appliedCandidates")
    public String appliedCandidates(Model model, HttpServletRequest request){		
		HttpSession session = request.getSession();		
		String employerId = (String)session.getAttribute("employer_login_id");
		List<AppliedCandidates> appliedCandidateList = employerJobService.listAppliedCandidates(Integer.parseInt(employerId));				
		model.addAttribute("appliedCandidatesList", appliedCandidateList);		
    	return "appliedCandidates";        
    }
	
	@RequestMapping(value= "/replyByEmail")
    public @ResponseBody String replyByEmail(@RequestParam(value = "email") String email, 
    		@RequestParam(value = "subject") String subject, 
    		@RequestParam(value = "message") String message){		
		String response = "0";			
		MailUtility mailUtility = new MailUtility(email, subject, message);
		if(!mailUtility.send()){		
			response = "1";
		}		
		return response;
    }
	
	@RequestMapping(value= "/deleteApplications") 
	public String deleteApplications(HttpServletRequest request, Model model) { 		
		Integer applyCandId = Integer.parseInt((String)request.getParameter("applyCandId"));
		employerJobService.removeApplication(applyCandId);		
		return "redirect:appliedCandidates";		
	}

}
