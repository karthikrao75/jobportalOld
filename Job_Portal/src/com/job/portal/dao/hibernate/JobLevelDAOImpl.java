package com.job.portal.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository; 
import com.job.portal.dao.JobLevelDAO;
import com.job.portal.domain.JobLevel;

 
@Repository
public class JobLevelDAOImpl implements JobLevelDAO {    
	
	private static final Logger logger = LoggerFactory.getLogger(JobLevelDAOImpl.class);
 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addJobLevel(JobLevel jobLevel) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(jobLevel);
        logger.info("JobLevel saved successfully, JobLevel Details="+ jobLevel);
    }
 
    
    @Override
    public void updateJobLevel(JobLevel jobLevel) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(jobLevel);
        logger.info("JobLevel updated successfully, JobLevel Details="+ jobLevel);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<JobLevel> listJobLevel() {
        Session session = this.sessionFactory.getCurrentSession();
        List<JobLevel> jobLevelList = session.createQuery("from JobLevel").list();
        for(JobLevel jobLevel : jobLevelList){
            logger.info("JobLevel List::"+ jobLevel);
        }
        return jobLevelList;
    }
 
    @Override
    public JobLevel getJobLevelById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        JobLevel jobLevel = (JobLevel) session.load(JobLevel.class, new Integer(id));
        logger.info("JobLevel loaded successfully, JobLevel details="+ jobLevel);
        return jobLevel;
    }
 
    @Override
    public void removeJobLevel(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        JobLevel jobLevel = (JobLevel) session.load(JobLevel.class, new Integer(id));
        if(null != jobLevel){
            session.delete(jobLevel);
        }
        logger.info("Job Level deleted successfully, Job Level details="+ jobLevel);
    }
 
}