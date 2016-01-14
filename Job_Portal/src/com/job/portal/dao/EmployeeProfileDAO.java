package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.EmployeeProfile;

public interface EmployeeProfileDAO {
	
	public void addEmployeeProfile(EmployeeProfile employeeProfile);
    public void updateEmployeeProfile(EmployeeProfile employeeProfile);
    public List<EmployeeProfile> listEmployeeProfile();
    public EmployeeProfile getEmployeeProfileById(int id);
    public List<EmployeeProfile> listEmployeeProfileByEmployeeId(Integer employeeId);
    public Long getNoOfProfilesByEmployeeId(Integer employeeId);
    public void removeEmployeeProfile(int id);
    public List<EmployeeProfile> getCanidateList(String primarySkills, String minExperience, String maxExperience);
    
}
