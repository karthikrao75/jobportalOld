package com.job.portal.service;

import java.util.List;

import javax.transaction.Transactional;

import com.job.portal.dao.CityDAO;
import com.job.portal.dao.CountryDAO;
import com.job.portal.dao.EducationLevelDAO;
import com.job.portal.dao.EmployeeDAO;
import com.job.portal.dao.JobLevelDAO;
import com.job.portal.dao.StateDAO;
import com.job.portal.domain.Country;
import com.job.portal.domain.EducationLevel;
import com.job.portal.domain.Employee;
import com.job.portal.domain.JobLevel;

public class RegistrationServiceImpl implements RegistrationService {
	
	private EmployeeDAO employeeDAO;
	
	private CountryDAO countryDAO;
	
	private StateDAO stateDAO;
	
	private CityDAO cityDAO;
	
	private EducationLevelDAO educationLevelDAO;
	
	private JobLevelDAO jobLevelDAO;	
	
	/**
	 * @return the employeeDAO
	 */
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	/**
	 * @param employeeDAO the employeeDAO to set
	 */
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	

	/**
	 * @return the countryDAO
	 */
	public CountryDAO getCountryDAO() {
		return countryDAO;
	}

	/**
	 * @param countryDAO the countryDAO to set
	 */
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	/**
	 * @return the stateDAO
	 */
	public StateDAO getStateDAO() {
		return stateDAO;
	}

	/**
	 * @param stateDAO the stateDAO to set
	 */
	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	/**
	 * @return the cityDAO
	 */
	public CityDAO getCityDAO() {
		return cityDAO;
	}

	/**
	 * @param cityDAO the cityDAO to set
	 */
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	/**
	 * @return the educationLevelDAO
	 */
	public EducationLevelDAO getEducationLevelDAO() {
		return educationLevelDAO;
	}

	/**
	 * @param educationLevelDAO the educationLevelDAO to set
	 */
	public void setEducationLevelDAO(EducationLevelDAO educationLevelDAO) {
		this.educationLevelDAO = educationLevelDAO;
	}

	/**
	 * @return the jobLevelDAO
	 */
	public JobLevelDAO getJobLevelDAO() {
		return jobLevelDAO;
	}

	/**
	 * @param jobLevelDAO the jobLevelDAO to set
	 */
	public void setJobLevelDAO(JobLevelDAO jobLevelDAO) {
		this.jobLevelDAO = jobLevelDAO;
	}

	@Override
    @Transactional
    public void addEmployee(Employee employee) {
        this.employeeDAO.addEmployee(employee);
    }
	
	@Override
    @Transactional
    public List<Country> getCountryList() {
        return (this.countryDAO.listCountry());
    }
	
	@Override
    @Transactional
    public List<EducationLevel> getEducationLevelList() {
        return (this.educationLevelDAO.listEducationLevel());
    }
	
	@Override
    @Transactional
    public List<JobLevel> getJobLevelList() {
        return (this.jobLevelDAO.listJobLevel());
    }

}
