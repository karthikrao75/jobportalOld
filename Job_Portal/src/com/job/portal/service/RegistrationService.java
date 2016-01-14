package com.job.portal.service;

import java.util.List;

import com.job.portal.domain.Country;
import com.job.portal.domain.EducationLevel;
import com.job.portal.domain.Employee;
import com.job.portal.domain.JobLevel;

public interface RegistrationService {
	public void addEmployee(Employee employee);
	
	public List<Country> getCountryList();
	
	public List<EducationLevel> getEducationLevelList();
	
	public List<JobLevel> getJobLevelList();
	
}
