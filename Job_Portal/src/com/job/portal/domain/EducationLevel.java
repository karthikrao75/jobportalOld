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
@Table(name = "tab_education_level")
public class EducationLevel {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "education_level_id")
    private Integer educationLevelId;
	
	@Column(name = "education_level_name")
	private String educationLevelName;
	
	@OneToMany(mappedBy="educationLevel")
    private Set<Employee> employees;

	/**
	 * @return the educationLevelId
	 */
	public Integer getEducationLevelId() {
		return educationLevelId;
	}

	/**
	 * @param educationLevelId the educationLevelId to set
	 */
	public void setEducationLevelId(Integer educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	/**
	 * @return the educationLevelName
	 */
	public String getEducationLevelName() {
		return educationLevelName;
	}

	/**
	 * @param educationLevelName the educationLevelName to set
	 */
	public void setEducationLevelName(String educationLevelName) {
		this.educationLevelName = educationLevelName;
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
