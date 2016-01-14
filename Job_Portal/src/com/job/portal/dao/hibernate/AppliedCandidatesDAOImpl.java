package com.job.portal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.job.portal.dao.AppliedCandidatesDAO;
import com.job.portal.domain.AppliedCandidates;

public class AppliedCandidatesDAOImpl implements AppliedCandidatesDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(StateDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addAppliedCandidates(AppliedCandidates appliedCandidates) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(appliedCandidates);
        logger.info("AppliedCandidates saved successfully, AppliedCandidates Details="+ appliedCandidates);
    }
    
    @Override
    public void updateAppliedCandidates(AppliedCandidates appliedCandidates) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(appliedCandidates);
        logger.info("AppliedCandidates updated successfully, AppliedCandidates Details="+ appliedCandidates);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<AppliedCandidates> listAppliedCandidates() {
        Session session = this.sessionFactory.getCurrentSession();
        List<AppliedCandidates> appliedCandidatesList = session.createQuery("from AppliedCandidates").list();
        for(AppliedCandidates appliedCandidates : appliedCandidatesList){
            logger.info("AppliedCandidates List::"+ appliedCandidates);
        }
        return appliedCandidatesList;
    }
 
    @Override
    public AppliedCandidates getAppliedCandidatesById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        AppliedCandidates appliedCandidates = (AppliedCandidates) session.load(AppliedCandidates.class, new Integer(id));
        logger.info("AppliedCandidates loaded successfully, AppliedCandidates details="+ appliedCandidates);
        return appliedCandidates;
    }
 
    @Override
    public void removeAppliedCandidates(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        AppliedCandidates appliedCandidates = (AppliedCandidates) session.load(AppliedCandidates.class, new Integer(id));
        if(null != appliedCandidates){
            session.delete(appliedCandidates);
        }
        logger.info("AppliedCandidates deleted successfully, appliedCandidates details="+ appliedCandidates);
    }
    
    public List<AppliedCandidates> listAppliedCandidatesByEmployerId(Integer employerId) {
    	Session session = this.sessionFactory.getCurrentSession();
        List<AppliedCandidates> appliedCandidatesList = session.createQuery("from AppliedCandidates as appliedCandidates where appliedCandidates.employerJob.employer.employerId=" + employerId).list();        
        return appliedCandidatesList;
    }   
    
}
