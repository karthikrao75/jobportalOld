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

import com.job.portal.domain.City;
import com.job.portal.domain.Country;
import com.job.portal.domain.EducationLevel;
import com.job.portal.domain.Employee;
import com.job.portal.domain.EmployeeProfile;
import com.job.portal.domain.Employer;
import com.job.portal.domain.JobLevel;
import com.job.portal.domain.State;
import com.job.portal.mail.MailUtility;
import com.job.portal.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired(required=true)
    @Qualifier(value="employeeService")
	private EmployeeService employeeService;
	
	/**
	 * @return the employeeService
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value= "/getStateList")
	public @ResponseBody String getStateList(@RequestParam(value = "countryId") String countryId, 
			@RequestParam(value = "stateId") String stateId) {		
		List<State> stateList = employeeService.getStateList(Integer.parseInt(countryId));		
		String stateListData = "<option value=\"0\">Select</option>";		
		for (State state : stateList) {
			if(stateId != null && stateId.equals(state.getStateId().toString())) {
				stateListData += "<option value=\""+ state.getStateId() +"\" selected>" + state.getStateName() + "</option>";
			}else{
				stateListData += "<option value=\""+ state.getStateId() +"\">" + state.getStateName() + "</option>";
			}
		}		
    	return stateListData;        
    }
	
	@RequestMapping(value= "/getCityList")
	public @ResponseBody String getCityList(@RequestParam(value = "stateId") String stateId, 
			@RequestParam(value = "cityId") String cityId) {		
		List<City> cityList = employeeService.getCityList(Integer.parseInt(stateId));		
		String cityListData = "<option value=\"0\">Select</option>";		
		for (City city : cityList) {
			if(cityId != null && cityId.equals(city.getCityId().toString())) {
				cityListData += "<option value=\""+ city.getCityId() +"\" selected>" + city.getCityName() + "</option>";
			}else{
				cityListData += "<option value=\""+ city.getCityId() +"\">" + city.getCityName() + "</option>";
			}
		}		
    	return cityListData;        
    }
	
	@RequestMapping(value= "/checkEmailExists")
	public @ResponseBody String checkEmailExists(@RequestParam(value = "email") String email) {		
		Employee employee = employeeService.getEmployeeByEmail(email);		
		if( employee != null)
			return "1";		
		return "0";
    }
	
	@RequestMapping(value= "/checkMobileExists")
	public @ResponseBody String checkMobileExists(@RequestParam(value = "mobile") String mobile) {		
		Employee employee = employeeService.getEmployeeByMobile(mobile);		
		if( employee != null)
			return "1";		
		return "0";
    }
	
	@RequestMapping(value= "/employeeLogin", method = RequestMethod.GET)
    public String showEmployeeLogin(Model model, HttpServletRequest request){		
    	return "employeeLogin";        
    }
	
	@RequestMapping(value= "/employeeLoginChk")
	public @ResponseBody String employeeLoginChk(@RequestParam(value = "email") String email, 
			@RequestParam(value = "password") String password, HttpServletRequest request) {		
		Employee employee = employeeService.getEmployeeForLogin(email, password);		
		if( employee != null) {
			HttpSession session = request.getSession();
			session.setAttribute("employee_login_email", email);
			session.setAttribute("employee_login_id", employee.getEmployeeId().toString());
			return "1";		
		}
		return "0";
    }
	
	@RequestMapping(value= "/showEmployeeProfile")
    public String showEmployeeLogin(@ModelAttribute("employee") Employee employee, 
    		Model model, HttpServletRequest request){ 		
		HttpSession session = request.getSession();
		if( session.getAttribute("employee_login_id") != null) {
			List<Country> countryList = this.employeeService.getCountryList();
	    	List<EducationLevel> educationLevelList = this.employeeService.getEducationLevelList();
	    	List<JobLevel> jobLevelList = this.employeeService.getJobLevelList();  	
	    	
	    	model.addAttribute("countryList", countryList);
	    	model.addAttribute("educationLevelList", educationLevelList);
	    	model.addAttribute("jobLevelList", jobLevelList);
			employee = employeeService.getEmployeeById(Integer.parseInt((String)session.getAttribute("employee_login_id")));			
			model.addAttribute("employee", employee);
			return "showEmployeeProfile";
		}else{
			return "employeeLogin";
		}
    }
	
	@RequestMapping(value= "/employeeLogout", method = RequestMethod.GET)
    public String doEmployeeLogout(Model model, HttpServletRequest request){    
		HttpSession session = request.getSession();
		session.invalidate();		
    	return "employeeLogin";        
    }
	
	@RequestMapping(value= "/updateEmployee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employee") Employee employee, Model model){
		
        this.employeeService.updateEmployee(employee);         
        employee = employeeService.getEmployeeById(employee.getEmployeeId());   
        List<Country> countryList = this.employeeService.getCountryList();
    	List<EducationLevel> educationLevelList = this.employeeService.getEducationLevelList();
    	List<JobLevel> jobLevelList = this.employeeService.getJobLevelList();  	
    	
    	model.addAttribute("countryList", countryList);
    	model.addAttribute("educationLevelList", educationLevelList);
    	model.addAttribute("jobLevelList", jobLevelList);
        model.addAttribute("employee", employee);
        model.addAttribute("update_profile", "1");
        return "showEmployeeProfile";
        
    }
	
	@RequestMapping(value= "/sendEmployeePassword")
    public @ResponseBody String sendEmployerPassword(@RequestParam(value = "email") String email){		
		String response = "0";
		Employee employee = employeeService.getEmployeeByEmail(email);			
		if(employee != null ){			
			MailUtility mailUtility = new MailUtility(email, "Your Jobs Password", employee.getEmployeePassword());
			if(!mailUtility.send()){
				response="2";
			}
		}else{
			response = "1";
		}	
    	return response;        
    }
	
	@RequestMapping(value= "/employeeForgotPassword")
    public String employeeForgotPassword(Model model, HttpServletRequest request){			
    	return "employeeForgotPassword";        
    }
	
	@RequestMapping(value= "/searchCandidates", method = RequestMethod.GET)
    public String showSearchCandidates(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if( session.getAttribute("employer_login_id") != null) {
			return "searchCandidates";        
		}		
		return "employerLogin";
    }
}
