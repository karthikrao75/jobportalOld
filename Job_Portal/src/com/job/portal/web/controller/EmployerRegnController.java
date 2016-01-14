package com.job.portal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.job.portal.domain.Country;
import com.job.portal.domain.Employer;
import com.job.portal.service.EmployerRegnService;


@Controller
public class EmployerRegnController {
	
	private EmployerRegnService employerRegnService;
	
	@Autowired(required=true)
    @Qualifier(value="employerRegnService")
    public void setEmployerRegnService(EmployerRegnService employerRegnService){
        this.employerRegnService = employerRegnService;
    }	
	
	@RequestMapping(value= "/addEmployer", method = RequestMethod.POST)
    public String addEmployer(@ModelAttribute("employer") Employer employer, Model model){    	
        this.employerRegnService.addEmployer(employer);               
        model.addAttribute("from_regn", "1");
        return "employerLogin";        
    }
	
	@RequestMapping(value= "/employerRegistrationForm", method = RequestMethod.GET)
    public String showRegistrationForm(Model model){   	
    	List<Country> countryList = this.employerRegnService.getCountryList();   	
    	model.addAttribute("countryList", countryList);    	
    	model.addAttribute("employer", new Employer());
    	return "employerRegistrationForm";        
    }

}
