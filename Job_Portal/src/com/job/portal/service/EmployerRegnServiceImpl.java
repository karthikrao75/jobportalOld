package com.job.portal.service;

import java.util.List;

import javax.transaction.Transactional;

import com.job.portal.dao.CityDAO;
import com.job.portal.dao.CountryDAO;
import com.job.portal.dao.EmployerDAO;
import com.job.portal.dao.StateDAO;
import com.job.portal.domain.Country;
import com.job.portal.domain.Employer;

public class EmployerRegnServiceImpl implements EmployerRegnService {
	
	private EmployerDAO employerDAO;
	
	private CountryDAO countryDAO;
	
	private StateDAO stateDAO;
	
	private CityDAO cityDAO;

	/**
	 * @return the employerDAO
	 */
	public EmployerDAO getEmployerDAO() {
		return employerDAO;
	}

	/**
	 * @param employerDAO the employerDAO to set
	 */
	public void setEmployerDAO(EmployerDAO employerDAO) {
		this.employerDAO = employerDAO;
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
	
	@Override
    @Transactional
    public void addEmployer(Employer employer) {
        this.employerDAO.addEmployer(employer);
    }
	
	@Override
    @Transactional
    public List<Country> getCountryList() {
        return (this.countryDAO.listCountry());
    }
	
}
