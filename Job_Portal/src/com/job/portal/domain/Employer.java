package com.job.portal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tab_employer")
public class Employer {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employer_id")
    private Integer employerId;
	
	@Column(name = "employer_email")
	private String employerEmail;
	
	@Column(name = "employer_password")
	private String employerPassword;
	
	@Column(name = "employer_first_name")
	private String employerFirstName;
	
	@Column(name = "employer_last_name")
	private String employerLastName;
	
	@Column(name = "employer_address")
	private String employerAddress;
	
	@ManyToOne
    @JoinColumn(name="country_id")
	private Country country;
	
	@ManyToOne
    @JoinColumn(name="state_id")
	private State state;
	
	@ManyToOne
    @JoinColumn(name="city_id")
	private City city;
	
	@Column(name = "postal_code")
    private Integer postalCode;
	
	@Column(name = "employer_position")
	private String employerPosition;
	
	@Column(name = "employer_industry")
	private String employerIndustry;
	
	@Column(name = "employer_mobile")
	private String employerMobile;
	
	@Column(name = "number_of_employees")
    private Integer numberOfEmployees;
	
	@Column(name = "company_name")
	private String companyName;

	/**
	 * @return the employerId
	 */
	public Integer getEmployerId() {
		return employerId;
	}

	/**
	 * @param employerId the employerId to set
	 */
	public void setEmployerId(Integer employerId) {
		this.employerId = employerId;
	}

	/**
	 * @return the employerEmail
	 */
	public String getEmployerEmail() {
		return employerEmail;
	}

	/**
	 * @param employerEmail the employerEmail to set
	 */
	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}

	/**
	 * @return the employerPassword
	 */
	public String getEmployerPassword() {
		return employerPassword;
	}

	/**
	 * @param employerPassword the employerPassword to set
	 */
	public void setEmployerPassword(String employerPassword) {
		this.employerPassword = employerPassword;
	}

	/**
	 * @return the employerFirstName
	 */
	public String getEmployerFirstName() {
		return employerFirstName;
	}

	/**
	 * @param employerFirstName the employerFirstName to set
	 */
	public void setEmployerFirstName(String employerFirstName) {
		this.employerFirstName = employerFirstName;
	}

	/**
	 * @return the employerLastName
	 */
	public String getEmployerLastName() {
		return employerLastName;
	}

	/**
	 * @param employerLastName the employerLastName to set
	 */
	public void setEmployerLastName(String employerLastName) {
		this.employerLastName = employerLastName;
	}

	/**
	 * @return the employerAddress
	 */
	public String getEmployerAddress() {
		return employerAddress;
	}

	/**
	 * @param employerAddress the employerAddress to set
	 */
	public void setEmployerAddress(String employerAddress) {
		this.employerAddress = employerAddress;
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
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @return the postalCode
	 */
	public Integer getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the employerPosition
	 */
	public String getEmployerPosition() {
		return employerPosition;
	}

	/**
	 * @param employerPosition the employerPosition to set
	 */
	public void setEmployerPosition(String employerPosition) {
		this.employerPosition = employerPosition;
	}

	/**
	 * @return the employerIndustry
	 */
	public String getEmployerIndustry() {
		return employerIndustry;
	}

	/**
	 * @param employerIndustry the employerIndustry to set
	 */
	public void setEmployerIndustry(String employerIndustry) {
		this.employerIndustry = employerIndustry;
	}

	/**
	 * @return the employerMobile
	 */
	public String getEmployerMobile() {
		return employerMobile;
	}

	/**
	 * @param employerMobile the employerMobile to set
	 */
	public void setEmployerMobile(String employerMobile) {
		this.employerMobile = employerMobile;
	}

	/**
	 * @return the numberOfEmployees
	 */
	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}

	/**
	 * @param numberOfEmployees the numberOfEmployees to set
	 */
	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}	

}
