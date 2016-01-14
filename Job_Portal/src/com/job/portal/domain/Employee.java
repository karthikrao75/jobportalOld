package com.job.portal.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tab_employee")
public class Employee {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Integer employeeId;
	
	@Column(name = "employee_email")
	private String employeeEmail;
	
	@Column(name = "employee_password")
	private String employeePassword;
	
	@Column(name = "employee_first_name")
	private String employeeFirstName;
	
	@Column(name = "employee_last_name")
	private String employeeLastName;
	
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
	
	@ManyToOne
    @JoinColumn(name="education_level_id")
	private EducationLevel educationLevel;
	
	@Column(name = "job_category")
	private String jobCategory;
	
	@ManyToOne
    @JoinColumn(name="job_level_id")
	private JobLevel jobLevel;
	
	@Column(name = "current_job_title")
	private String currentJobTitle;
	
	@Column(name = "employee_mobile")
	private String employeeMobile;
	
	@Column(name = "employee_primary_skills")
	private String employeePrimarySkills;
	
	@Column(name = "employee_year_exp")
	private String employeeYearExp;
	
	@Column(name = "employee_month_exp")
	private String employeeMonthExp;
	
	@OneToMany(mappedBy="employee")
    private Set<EmployeeProfile> employeeProfile;

	/**
	 * @return the employeeId
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeEmail
	 */
	public String getEmployeeEmail() {
		return employeeEmail;
	}

	/**
	 * @param employeeEmail the employeeEmail to set
	 */
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	/**
	 * @return the employeePassword
	 */
	public String getEmployeePassword() {
		return employeePassword;
	}

	/**
	 * @param employeePassword the employeePassword to set
	 */
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	/**
	 * @return the employeeFirstName
	 */
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	/**
	 * @param employeeFirstName the employeeFirstName to set
	 */
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	/**
	 * @return the employeeLastName
	 */
	public String getEmployeeLastName() {
		return employeeLastName;
	}

	/**
	 * @param employeeLastName the employeeLastName to set
	 */
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
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
	 * @return the educationLevel
	 */
	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	/**
	 * @param educationLevel the educationLevel to set
	 */
	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	/**
	 * @return the jobCategory
	 */
	public String getJobCategory() {
		return jobCategory;
	}

	/**
	 * @param jobCategory the jobCategory to set
	 */
	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	/**
	 * @return the jobLevel
	 */
	public JobLevel getJobLevel() {
		return jobLevel;
	}

	/**
	 * @param jobLevel the jobLevel to set
	 */
	public void setJobLevel(JobLevel jobLevel) {
		this.jobLevel = jobLevel;
	}

	/**
	 * @return the currentJobTitle
	 */
	public String getCurrentJobTitle() {
		return currentJobTitle;
	}

	/**
	 * @param currentJobTitle the currentJobTitle to set
	 */
	public void setCurrentJobTitle(String currentJobTitle) {
		this.currentJobTitle = currentJobTitle;
	}

	/**
	 * @return the employeeMobile
	 */
	public String getEmployeeMobile() {
		return employeeMobile;
	}

	/**
	 * @param employeeMobile the employeeMobile to set
	 */
	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
	}

	/**
	 * @return the employeePrimarySkills
	 */
	public String getEmployeePrimarySkills() {
		return employeePrimarySkills;
	}

	/**
	 * @param employeePrimarySkills the employeePrimarySkills to set
	 */
	public void setEmployeePrimarySkills(String employeePrimarySkills) {
		this.employeePrimarySkills = employeePrimarySkills;
	}

	/**
	 * @return the employeeYearExp
	 */
	public String getEmployeeYearExp() {
		return employeeYearExp;
	}

	/**
	 * @param employeeYearExp the employeeYearExp to set
	 */
	public void setEmployeeYearExp(String employeeYearExp) {
		this.employeeYearExp = employeeYearExp;
	}

	/**
	 * @return the employeeMonthExp
	 */
	public String getEmployeeMonthExp() {
		return employeeMonthExp;
	}

	/**
	 * @param employeeMonthExp the employeeMonthExp to set
	 */
	public void setEmployeeMonthExp(String employeeMonthExp) {
		this.employeeMonthExp = employeeMonthExp;
	}

	/**
	 * @return the employeeProfile
	 */
	public Set<EmployeeProfile> getEmployeeProfile() {
		return employeeProfile;
	}

	/**
	 * @param employeeProfile the employeeProfile to set
	 */
	public void setEmployeeProfile(Set<EmployeeProfile> employeeProfile) {
		this.employeeProfile = employeeProfile;
	}

	
}
