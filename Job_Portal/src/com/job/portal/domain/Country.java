package com.job.portal.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tab_country")
public class Country {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private Integer countryId;
	
	@Column(name = "country_name")
	private String countryName;
	
	@OneToMany(mappedBy="country")
    private Set<State> states;
	
	@OneToMany(mappedBy="country")
    private Set<Employee> employees;

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the states
	 */
	public Set<State> getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(Set<State> states) {
		this.states = states;
	}

	/**
	 * @return the employees
	 */
	public Set<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}	
	
	

}
