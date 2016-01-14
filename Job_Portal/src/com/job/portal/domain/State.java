package com.job.portal.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tab_state")
public class State {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id")
    private Integer stateId;
	
	@Column(name = "state_name")
	private String stateName;
	
	@OneToMany(mappedBy="state")
    private Set<Employee> employees;
	
	@OneToMany(mappedBy="state")
    private Set<City> cities;
	
	@ManyToOne
    @JoinColumn(name="country_id")
	private Country country;
	/**
	 * @return the cities
	 */
	public Set<City> getCities() {
		return cities;
	}

	/**
	 * @param cities the cities to set
	 */
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	/**
	 * @return the stateId
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
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
