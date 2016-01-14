package com.job.portal.service;

import java.util.List;

import com.job.portal.domain.City;
import com.job.portal.domain.Country;
import com.job.portal.domain.EducationLevel;
import com.job.portal.domain.Employee;
import com.job.portal.domain.EmployeeProfile;
import com.job.portal.domain.JobLevel;
import com.job.portal.domain.State;

public interface EmployeeService {
	
	public List<State> getStateList(Integer countryId);
	
	public List<City> getCityList(Integer stateId);
	
	public Employee getEmployeeById(Integer employeeId);
	
	public Employee getEmployeeByEmail(String email);
	
	public Employee getEmployeeByMobile(String mobile);
	
	public Employee getEmployeeForLogin(String email, String password);
	
	public List<Country> getCountryList();
	
	public List<EducationLevel> getEducationLevelList();
	
	public List<JobLevel> getJobLevelList();
	
	public void updateEmployee(Employee employee);
	
}
