package com.job.portal.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.job.portal.domain.EmployeeProfile;

@Controller
public class ResumeController {
	
	@RequestMapping(value= "/postResume", method = RequestMethod.GET)
    public String postResume(Model model, HttpServletRequest request){      
		HttpSession session = request.getSession();
		if( session.getAttribute("employee_login_id") != null) {
			model.addAttribute("employeeProfile", new EmployeeProfile());
			return "postResume";
		}else{
			return "employeeLogin";
		}
    }
	

}
