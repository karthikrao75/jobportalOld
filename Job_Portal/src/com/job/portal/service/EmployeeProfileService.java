package com.job.portal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.job.portal.domain.EmployeeProfile;

public interface EmployeeProfileService {
	
	public void addEmployeeProfile(EmployeeProfile employeeProfile, MultipartFile file, String uploadFilePath);
	
	public EmployeeProfile getEmployeeProfileById(Integer employeeProfileId);
	
	public String checkFiveProfilesUploaded(Integer employeeId);
	
	public List<EmployeeProfile> listEmployeeProfileByEmployeeId(Integer employeeId);
	
	public void removeEmployeeProfile(Integer employeeId);
	
	public List<EmployeeProfile> getCandidateList(String primarySkills, String minExperience, String maxExperience);


}
