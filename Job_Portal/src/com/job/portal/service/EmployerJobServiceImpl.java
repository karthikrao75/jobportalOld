package com.job.portal.service;


import java.util.List;
import javax.transaction.Transactional;

import com.job.portal.dao.AppliedCandidatesDAO;
import com.job.portal.dao.EmployerJobDAO;
import com.job.portal.dao.hibernate.AppliedCandidatesDAOImpl;
import com.job.portal.domain.AppliedCandidates;
import com.job.portal.domain.EmployerJob;

public class EmployerJobServiceImpl implements EmployerJobService {
	
	
	private EmployerJobDAO employerJobDAO;	
	
	private AppliedCandidatesDAO appliedCandidatesDAO;
	
	/**
	 * @return the employerJobDAO
	 */
	public EmployerJobDAO getEmployerJobDAO() {
		return employerJobDAO;
	}	
	
	/**
	 * @return the appliedCandidatesDAO
	 */
	public AppliedCandidatesDAO getAppliedCandidatesDAO() {
		return appliedCandidatesDAO;
	}

	/**
	 * @param appliedCandidatesDAO the appliedCandidatesDAO to set
	 */
	public void setAppliedCandidatesDAO(AppliedCandidatesDAO appliedCandidatesDAO) {
		this.appliedCandidatesDAO = appliedCandidatesDAO;
	}

	/**
	 * @param employerJobDAO the employerJobDAO to set
	 */
	public void setEmployerJobDAO(EmployerJobDAO employerJobDAO) {
		this.employerJobDAO = employerJobDAO;
	}

	@Override
	@Transactional
	public void addEmployerJob(EmployerJob employerJob) {
		employerJobDAO.addEmployerJob(employerJob);		
	}
	
	@Override
	@Transactional
	 public List<EmployerJob> listEmployerJobByEmployerId(Integer employerId) {
		return employerJobDAO.listEmployerJobByEmployerId(employerId);
	}
	
	@Override
	@Transactional
	public EmployerJob getEmployerJobById(Integer employerJobId) {
		return employerJobDAO.getEmployerJobById(employerJobId);
	}
	
	@Override
	@Transactional
	public void removeEmployerJob(Integer employerJobId) {
		employerJobDAO.removeEmployerJob(employerJobId);
	}
	
	@Override
	@Transactional
	public List<EmployerJob> listJobs(String primarySkills, String location, String experience, String minSalary, String maxSalary) {
		return employerJobDAO.listJobs(primarySkills, location, experience, minSalary, maxSalary);
	}
	
	@Override
	@Transactional
	public void addAppliedCandidates(AppliedCandidates appliedCandidates){
		appliedCandidatesDAO.addAppliedCandidates(appliedCandidates);
	}
	
	@Override
	@Transactional
	public List<AppliedCandidates> listAppliedCandidates(Integer employerId){
		return appliedCandidatesDAO.listAppliedCandidatesByEmployerId(employerId);
	}
	
	@Override
	@Transactional
	public void removeApplication(Integer applyCandId){
		appliedCandidatesDAO.removeAppliedCandidates(applyCandId);
	}
	
	
}
