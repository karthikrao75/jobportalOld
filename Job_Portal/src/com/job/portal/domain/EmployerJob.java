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
@Table(name = "tab_employer_job")
public class EmployerJob {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employer_job_id")
    private Integer employerJobId;
	
	@Column(name = "employer_job_title")
	private String employerJobTitle;
	
	@Column(name = "employer_job_descr")
	private String employerJobDescr;
	
	@Column(name = "exp_year")
	private Integer expYear;
	
	@Column(name = "exp_month")
	private Integer expMonth;
	
	@Column(name = "salary")
	private Integer salary;
	
	@Column(name = "employer_job_role")
	private String employerJobRole;
	
	@Column(name = "employer_job_location")
	private String employerJobLocation;
	
	@ManyToOne
    @JoinColumn(name="employer_id")
	private Employer employer;

	/**
	 * @return the employerJobId
	 */
	public Integer getEmployerJobId() {
		return employerJobId;
	}

	/**
	 * @param employerJobId the employerJobId to set
	 */
	public void setEmployerJobId(Integer employerJobId) {
		this.employerJobId = employerJobId;
	}

	/**
	 * @return the employerJobTitle
	 */
	public String getEmployerJobTitle() {
		return employerJobTitle;
	}

	/**
	 * @param employerJobTitle the employerJobTitle to set
	 */
	public void setEmployerJobTitle(String employerJobTitle) {
		this.employerJobTitle = employerJobTitle;
	}

	/**
	 * @return the employerJobDescr
	 */
	public String getEmployerJobDescr() {
		return employerJobDescr;
	}

	/**
	 * @param employerJobDescr the employerJobDescr to set
	 */
	public void setEmployerJobDescr(String employerJobDescr) {
		this.employerJobDescr = employerJobDescr;
	}

	/**
	 * @return the expYear
	 */
	public Integer getExpYear() {
		return expYear;
	}

	/**
	 * @param expYear the expYear to set
	 */
	public void setExpYear(Integer expYear) {
		this.expYear = expYear;
	}

	/**
	 * @return the expMonth
	 */
	public Integer getExpMonth() {
		return expMonth;
	}

	/**
	 * @param expMonth the expMonth to set
	 */
	public void setExpMonth(Integer expMonth) {
		this.expMonth = expMonth;
	}

	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	/**
	 * @return the employerJobRole
	 */
	public String getEmployerJobRole() {
		return employerJobRole;
	}

	/**
	 * @param employerJobRole the employerJobRole to set
	 */
	public void setEmployerJobRole(String employerJobRole) {
		this.employerJobRole = employerJobRole;
	}

	/**
	 * @return the employerJobLocation
	 */
	public String getEmployerJobLocation() {
		return employerJobLocation;
	}

	/**
	 * @param employerJobLocation the employerJobLocation to set
	 */
	public void setEmployerJobLocation(String employerJobLocation) {
		this.employerJobLocation = employerJobLocation;
	}

	/**
	 * @return the employer
	 */
	public Employer getEmployer() {
		return employer;
	}

	/**
	 * @param employer the employer to set
	 */
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
}
