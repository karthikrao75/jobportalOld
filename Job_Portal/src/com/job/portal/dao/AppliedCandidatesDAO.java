package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.AppliedCandidates;

public interface AppliedCandidatesDAO {
	
	public void addAppliedCandidates(AppliedCandidates appliedCandidates);
    public void updateAppliedCandidates(AppliedCandidates appliedCandidates);
    public List<AppliedCandidates> listAppliedCandidates();
    public AppliedCandidates getAppliedCandidatesById(int id);
    public List<AppliedCandidates> listAppliedCandidatesByEmployerId(Integer employerId);    
    public void removeAppliedCandidates(int id);   
    
}
