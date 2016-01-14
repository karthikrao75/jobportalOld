package com.job.portal.service;

import java.util.List;

import com.job.portal.domain.AppliedCandidates;
import com.job.portal.domain.EmployerJob;

public interface EmployerJobService {

	public void addEmployerJob(EmployerJob employerJob);
	
	public void addAppliedCandidates(AppliedCandidates appliedCandidates);
	
	public EmployerJob getEmployerJobById(Integer employerJobId);
	
	public List<EmployerJob> listEmployerJobByEmployerId(Integer employerId);
	
	public List<EmployerJob> listJobs(String primarySkills, String location, String experience, String minSalary, String maxSalary);
	
	public void removeEmployerJob(Integer employerJobId);
	
	public List<AppliedCandidates> listAppliedCandidates(Integer employerId);
	
	public void removeApplication(Integer applyCandId);
	
}
