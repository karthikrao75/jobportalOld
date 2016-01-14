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
@Table(name = "tab_applied_cand")
public class AppliedCandidates {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "applied_cand_id")
    private Integer appliedCandId;
	
	@ManyToOne
    @JoinColumn(name="employee_profile_id")
	private EmployeeProfile employeeProfile;
	
	@ManyToOne
    @JoinColumn(name="employer_job_id")
	private EmployerJob employerJob;

	/**
	 * @return the appliedCandId
	 */
	public Integer getAppliedCandId() {
		return appliedCandId;
	}

	/**
	 * @param appliedCandId the appliedCandId to set
	 */
	public void setAppliedCandId(Integer appliedCandId) {
		this.appliedCandId = appliedCandId;
	}

	/**
	 * @return the employeeProfile
	 */
	public EmployeeProfile getEmployeeProfile() {
		return employeeProfile;
	}

	/**
	 * @param employeeProfile the employeeProfile to set
	 */
	public void setEmployeeProfile(EmployeeProfile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}

	/**
	 * @return the employerJob
	 */
	public EmployerJob getEmployerJob() {
		return employerJob;
	}

	/**
	 * @param employerJob the employerJob to set
	 */
	public void setEmployerJob(EmployerJob employerJob) {
		this.employerJob = employerJob;
	}	

}
