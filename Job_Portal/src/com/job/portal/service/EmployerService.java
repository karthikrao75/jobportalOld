package com.job.portal.service;

import java.util.List;

import com.job.portal.domain.City;
import com.job.portal.domain.Country;
import com.job.portal.domain.Employer;
import com.job.portal.domain.State;

public interface EmployerService {
	
	public List<State> getStateList(Integer countryId);
	
	public List<City> getCityList(Integer stateId);
	
	public Employer getEmployerById(Integer employerId);
	
	public Employer getEmployerByEmail(String email);
	
	public Employer getEmployerByMobile(String mobile);
	
	public Employer getEmployerForLogin(String email, String password);
	
	public List<Country> getCountryList();	
	
	public void updateEmployer(Employer employer);
	
}
