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
@Table(name = "tab_job_level")
public class JobLevel {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_level_id")
    private Integer jobLevelId;
	
	@Column(name = "job_level_name")
	private String jobLevelName;
	
	@OneToMany(mappedBy="jobLevel")
    private Set<Employee> employees;

	/**
	 * @return the jobLevelId
	 */
	public Integer getJobLevelId() {
		return jobLevelId;
	}

	/**
	 * @param jobLevelId the jobLevelId to set
	 */
	public void setJobLevelId(Integer jobLevelId) {
		this.jobLevelId = jobLevelId;
	}

	/**
	 * @return the jobLevelName
	 */
	public String getJobLevelName() {
		return jobLevelName;
	}

	/**
	 * @param jobLevelName the jobLevelName to set
	 */
	public void setJobLevelName(String jobLevelName) {
		this.jobLevelName = jobLevelName;
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
