package com.job.portal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import com.job.portal.domain.Country;
import com.job.portal.domain.EducationLevel;
import com.job.portal.domain.Employee;
import com.job.portal.domain.JobLevel;
import com.job.portal.service.RegistrationService;

@Controller
public class RegistrationController {
	
	private RegistrationService registrationService;
	
	@Autowired(required=true)
    @Qualifier(value="registrationService")
    public void setRegistrationService(RegistrationService registrationService){
        this.registrationService = registrationService;
    }	
	
    @RequestMapping(value= "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employee") Employee employee, Model model){
         
    	System.out.println( "Education level >> " + employee.getEducationLevel());
        this.registrationService.addEmployee(employee);        
        
        model.addAttribute("from_regn", "1");
        return "employeeLogin";
        
    }
    
    @RequestMapping(value= "/registrationForm", method = RequestMethod.GET)
    public String showRegistrationForm(Model model){  
    	
    	List<Country> countryList = this.registrationService.getCountryList();
    	List<EducationLevel> educationLevelList = this.registrationService.getEducationLevelList();
    	List<JobLevel> jobLevelList = this.registrationService.getJobLevelList();  	
    	
    	model.addAttribute("countryList", countryList);
    	model.addAttribute("educationLevelList", educationLevelList);
    	model.addAttribute("jobLevelList", jobLevelList);
    	model.addAttribute("employee", new Employee());
    	return "registrationForm";        
    }

}
