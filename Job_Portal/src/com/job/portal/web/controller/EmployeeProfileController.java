package com.job.portal.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.job.portal.domain.Employee;
import com.job.portal.domain.EmployeeProfile;
import com.job.portal.domain.State;
import com.job.portal.service.EmployeeProfileService;
import com.job.portal.service.EmployeeService;

@Controller
public class EmployeeProfileController {
	
	@Autowired(required=true)
    @Qualifier(value="employeeProfileService")
	private EmployeeProfileService employeeProfileService;
	
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
	
	@RequestMapping(value= "/addEmployeeProfile", method = RequestMethod.POST)
    public String addEmployeeProfile(@RequestParam("resumeFile") MultipartFile myFile, 
    		Model model, HttpServletRequest request) throws IOException {	
		
		String redirectURL = "redirect:manageResumes";
		EmployeeProfile employeeProfile = new EmployeeProfile();	
		String uploadFilePath = "E:\\Uma\\Project\\UPLOADED_FILES\\";				
		String employeeId = (String)request.getSession().getAttribute("employee_login_id");		
		Integer employeeProfileId = null;
		Employee employee = employeeService.getEmployeeById(Integer.parseInt(employeeId));		
		String employeeResumePath = uploadFilePath + employeeId;		
		File empResumeFile = new File(employeeResumePath);		
		if(myFile != null) {
			if(!empResumeFile.exists()) {
				empResumeFile.mkdirs();
			}		
		}
		
		if( request.getParameter("employeeProfileId") != null && !"".equals(request.getParameter("employeeProfileId")) ){
			employeeProfileId = Integer.parseInt((String)request.getParameter("employeeProfileId"));
			employeeProfile.setEmployeeProfileId(employeeProfileId);
			model.addAttribute("from_profile_form_update", "1");
			redirectURL = "postResume";
		}else{
			 model.addAttribute("from_profile_form", "1");
		}
		employeeProfile.setResumePath(request.getParameter("resumePath"));
		employeeProfile.setEmployeeProfileTitle(request.getParameter("employeeProfileTitle"));
		employeeProfile.setProfileCoveringLetter(request.getParameter("profileCoveringLetter"));	
		employeeProfile.setEmployee(employee);
		employeeProfileService.addEmployeeProfile(employeeProfile, myFile, employeeResumePath);		
       
		if(employeeProfile.getEmployeeProfileId() != null){
			model.addAttribute("employeeProfile", employeeProfile);
		}
        return redirectURL;
        
    }
	
	@RequestMapping(value= "/checkFiveProfilesUploaded")
	public @ResponseBody String checkFiveProfilesUploaded(HttpServletRequest request) {	
		
		Integer employeeId = Integer.parseInt((String)request.getSession().getAttribute("employee_login_id"));
		String response = employeeProfileService.checkFiveProfilesUploaded(employeeId);
		
		return response;
    }
	
	@RequestMapping(value= "/manageResumes")
    public String manageResumes(HttpServletRequest request, Model model){  		
		Integer employeeId = Integer.parseInt((String)request.getSession().getAttribute("employee_login_id"));		
		List<EmployeeProfile> employeeProfilesList = employeeProfileService.listEmployeeProfileByEmployeeId(employeeId);		
		model.addAttribute("employeeProfilesList", employeeProfilesList);
    	return "manageResumes";        
    }
	
	@RequestMapping(value= "/downloadResume")
    public void downloadResume(@RequestParam(value = "empProfileId") String empProfileId, 
    		HttpServletRequest request, HttpServletResponse response) throws IOException {  		
		
		EmployeeProfile employeeProfile = employeeProfileService.getEmployeeProfileById(Integer.parseInt(empProfileId));
		
		// get absolute path of the application
        ServletContext context = request.getSession().getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);
 
        // construct the complete absolute path of the file
        String fullPath = "E:\\Uma\\Project\\UPLOADED_FILES\\" + employeeProfile.getEmployee().getEmployeeId()
        					+ File.separator + employeeProfile.getResumePath();        
        
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
    }
	
	@RequestMapping(value= "/deleteProfile") 
	public String deleteProfile(HttpServletRequest request, Model model) {  
		
		Integer employeeProfileId = Integer.parseInt((String)request.getParameter("employeeProfileId"));
		employeeProfileService.removeEmployeeProfile(employeeProfileId);
		
		model.addAttribute("from_delete_profile", "1");
		return "redirect:manageResumes";
		
	}
	
	@RequestMapping(value= "/editProfile") 
	public String editProfile(HttpServletRequest request, Model model) {  
		
		Integer employeeProfileId = Integer.parseInt((String)request.getParameter("employeeProfileId"));
		EmployeeProfile employeeProfile = employeeProfileService.getEmployeeProfileById(employeeProfileId);
		
		model.addAttribute("employeeProfile", employeeProfile);	
		return "postResume";
		
	}
	
	@RequestMapping(value= "/listCandidates", method = RequestMethod.POST)
    public String listCandidates(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if( session.getAttribute("employer_login_id") != null) {
			
			String primarySkills = request.getParameter("primarySkills") == null ? "" : request.getParameter("primarySkills");
			String minExperience = request.getParameter("minExperience") == null ? "" : request.getParameter("minExperience");
			String maxExperience = request.getParameter("maxExperience") == null ? "" : request.getParameter("maxExperience");	
			
			List<EmployeeProfile> candidateList = employeeProfileService.getCandidateList(primarySkills, minExperience, maxExperience);
			model.addAttribute( "candidateList", candidateList);
			return "listCandidates";        
		}		
		return "employerLogin";
    }
	

}
