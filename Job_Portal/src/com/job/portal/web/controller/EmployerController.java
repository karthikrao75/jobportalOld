package com.job.portal.web.controller;

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

import com.job.portal.domain.Country;
import com.job.portal.domain.Employer;
import com.job.portal.mail.MailUtility;
import com.job.portal.service.EmployerService;

@Controller
public class EmployerController {
	
	@Autowired(required=true)
    @Qualifier(value="employerService")
	private EmployerService employerService;	

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

	@RequestMapping(value= "/employerLogin", method = RequestMethod.GET)
    public String showEmployeeLogin(Model model, HttpServletRequest request){      
		if ( request.getSession().getAttribute("employer_login_id") != null ){
			return "searchCandidates";
		}
    	return "employerLogin";        
    }
	
	@RequestMapping(value= "/employerLoginChk")
	public @ResponseBody String employerLoginChk(@RequestParam(value = "email") String email, 
			@RequestParam(value = "password") String password, HttpServletRequest request) {		
		Employer employer = employerService.getEmployerForLogin(email, password);		
		if( employer != null) {
			HttpSession session = request.getSession();
			session.setAttribute("employer_login_email", email);
			session.setAttribute("employer_login_id", employer.getEmployerId().toString());
			return "1";		
		}
		return "0";
    }
	
	@RequestMapping(value= "/checkEmployerEmailExists")
	public @ResponseBody String checkEmployerEmailExists(@RequestParam(value = "email") String email) {		
		Employer employer = employerService.getEmployerByEmail(email);		
		if( employer != null)
			return "1";		
		return "0";
    }
	
	@RequestMapping(value= "/checkEmployerMobileExists")
	public @ResponseBody String checkEmployerMobileExists(@RequestParam(value = "mobile") String mobile) {		
		Employer employer = employerService.getEmployerByMobile(mobile);		
		if( employer != null)
			return "1";		
		return "0";
    }
	
	@RequestMapping(value= "/showEmployerProfile")
    public String showEmployerLogin(@ModelAttribute("employer") Employer employer, 
    		Model model, HttpServletRequest request){ 		
		HttpSession session = request.getSession();
		if( session.getAttribute("employer_login_id") != null) {
			List<Country> countryList = this.employerService.getCountryList();    	
	    	model.addAttribute("countryList", countryList);	    	
			employer = employerService.getEmployerById(Integer.parseInt((String)session.getAttribute("employer_login_id")));			
			model.addAttribute("employer", employer);
			return "showEmployerProfile";
		}else{
			return "employerLogin";
		}
    }
	
	@RequestMapping(value= "/employerLogout", method = RequestMethod.GET)
    public String doEmployerLogout(Model model, HttpServletRequest request){    
		HttpSession session = request.getSession();
		session.invalidate();		
    	return "employerLogin";        
    }
	
	@RequestMapping(value= "/updateEmployer", method = RequestMethod.POST)
    public String addEmployer(@ModelAttribute("employer") Employer employer, Model model){
		
        this.employerService.updateEmployer(employer);         
        employer = employerService.getEmployerById(employer.getEmployerId());   
        List<Country> countryList = this.employerService.getCountryList();   		
    	
    	model.addAttribute("countryList", countryList);    	
        model.addAttribute("employer", employer);
        model.addAttribute("update_employer_profile", "1");
        return "showEmployerProfile";
        
    }
	
	@RequestMapping(value= "/sendEmployerPassword")
    public @ResponseBody String sendEmployerPassword(@RequestParam(value = "email") String email){		
		String response = "0";
		Employer employer = employerService.getEmployerByEmail(email);			
		if(employer != null ){
			System.out.println(" Employer name >>> " + employer.getEmployerFirstName());
			MailUtility mailUtility = new MailUtility(email, "Your Jobs Password", employer.getEmployerPassword());
			if(!mailUtility.send()){
				response="2";
			}
		}else{
			response = "1";
		}	
    	return response;        
    }
	
	@RequestMapping(value= "/employerForgotPassword")
    public String employerForgotPassword(Model model, HttpServletRequest request){			
    	return "employerForgotPassword";        
    }
	
	@RequestMapping(value= "/sendReply")
    public String employeeForgotPassword(Model model, HttpServletRequest request){			
		
		String email = request.getParameter("employerJobId");
		model.addAttribute("candidateEmail", email);
    	return "sendReply";        
    }
	
}
