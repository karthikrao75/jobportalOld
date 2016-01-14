package com.job.portal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tab_employee_profile")
public class EmployeeProfile {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_profile_id")
    private Integer employeeProfileId;
	
	@Column(name = "employee_profile_title")
	private String employeeProfileTitle;
	
	@Column(name = "profile_covering_letter")
	private String profileCoveringLetter;
	
	@Column(name = "resume_path")
	private String resumePath;
	
	@ManyToOne
    @JoinColumn(name="employee_id")
	private Employee employee;

	/**
	 * @return the employeeProfileId
	 */
	public Integer getEmployeeProfileId() {
		return employeeProfileId;
	}

	/**
	 * @param employeeProfileId the employeeProfileId to set
	 */
	public void setEmployeeProfileId(Integer employeeProfileId) {
		this.employeeProfileId = employeeProfileId;
	}

	/**
	 * @return the employeeProfileTitle
	 */
	public String getEmployeeProfileTitle() {
		return employeeProfileTitle;
	}

	/**
	 * @param employeeProfileTitle the employeeProfileTitle to set
	 */
	public void setEmployeeProfileTitle(String employeeProfileTitle) {
		this.employeeProfileTitle = employeeProfileTitle;
	}

	/**
	 * @return the profileCoveringLetter
	 */
	public String getProfileCoveringLetter() {
		return profileCoveringLetter;
	}

	/**
	 * @param profileCoveringLetter the profileCoveringLetter to set
	 */
	public void setProfileCoveringLetter(String profileCoveringLetter) {
		this.profileCoveringLetter = profileCoveringLetter;
	}	

	/**
	 * @return the resumePath
	 */
	public String getResumePath() {
		return resumePath;
	}

	/**
	 * @param resumePath the resumePath to set
	 */
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
