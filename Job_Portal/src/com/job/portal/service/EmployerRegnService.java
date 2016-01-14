package com.job.portal.service;

import java.util.List;

import com.job.portal.domain.Country;
import com.job.portal.domain.Employer;


public interface EmployerRegnService {
	
	public void addEmployer(Employer employer);
	
	public List<Country> getCountryList();

}
