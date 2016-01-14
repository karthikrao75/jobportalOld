package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.EmployerJob;

public interface EmployerJobDAO {
	
	public void addEmployerJob(EmployerJob employerJob);
    public void updateEmployerJob(EmployerJob employerJob);
    public List<EmployerJob> listEmployerJob();
    public EmployerJob getEmployerJobById(int id);
    public List<EmployerJob> listEmployerJobByEmployerId(Integer employerId);    
    public void removeEmployerJob(int id);
    public List<EmployerJob> listJobs(String primarySkills, String location, String experience, String minSalary, String maxSalary);
    
}
