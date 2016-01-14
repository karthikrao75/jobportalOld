package com.job.portal.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.job.portal.dao.EmployerJobDAO;
import com.job.portal.domain.EmployeeProfile;
import com.job.portal.domain.EmployerJob;

public class EmployerJobDAOImpl implements EmployerJobDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(StateDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addEmployerJob(EmployerJob employerJob) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(employerJob);
        logger.info("EmployerJob saved successfully, EmployerJob Details="+ employerJob);
    }
    
    @Override
    public void updateEmployerJob(EmployerJob employerJob) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(employerJob);
        logger.info("EmployerJob updated successfully, EmployerJob Details="+ employerJob);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<EmployerJob> listEmployerJob() {
        Session session = this.sessionFactory.getCurrentSession();
        List<EmployerJob> employerJobList = session.createQuery("from EmployerJob").list();
        for(EmployerJob employerJob : employerJobList){
            logger.info("EmployerJob List::"+ employerJob);
        }
        return employerJobList;
    }
 
    @Override
    public EmployerJob getEmployerJobById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        EmployerJob employerJob = (EmployerJob) session.load(EmployerJob.class, new Integer(id));
        logger.info("EmployerJob loaded successfully, EmployerJob details="+ employerJob);
        return employerJob;
    }
 
    @Override
    public void removeEmployerJob(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        EmployerJob employerJob = (EmployerJob) session.load(EmployerJob.class, new Integer(id));
        if(null != employerJob){
            session.delete(employerJob);
        }
        logger.info("EmployerJob deleted successfully, employerJob details="+ employerJob);
    }
    
    @Override
    public List<EmployerJob> listEmployerJobByEmployerId(Integer employerId) {
    	Session session = this.sessionFactory.getCurrentSession();
        List<EmployerJob> employerJobList = session.createQuery("from EmployerJob as employerJob where employerJob.employer.employerId=" + employerId).list();        
        return employerJobList;
    }
    
    @Override
    public List<EmployerJob> listJobs(String primarySkills, String location, String experience, String minSalary, String maxSalary) {
    	List<EmployerJob> jobsList = null;
    	
    	Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(EmployerJob.class);  	
    	
    	String[] primarySkillsArray = primarySkills.split(" ");        
        Disjunction primarySkillsDisjunction = Restrictions.disjunction();
        for (int i=0; i < primarySkillsArray.length; i++) {        	
        	primarySkillsDisjunction.add(Restrictions.like("employerJobTitle", "%" + primarySkillsArray[i] + "%"));        	
        }        
        criteria.add(Restrictions.and(primarySkillsDisjunction));       
        
        if ( !"".equals(location)){
        	 criteria.add(Restrictions.like("employerJobLocation", "%" + location + "%"));
        }
        
        if ( !"".equals(experience)){        	
       	 criteria.add(Restrictions.eq("expYear", Integer.parseInt(experience)));
       }
        
        if ( !"".equals(minSalary) && !"".equals(maxSalary)){
    		criteria.add(Restrictions.ge("salary", Integer.parseInt(minSalary))).
    		add(Restrictions.le("salary", Integer.parseInt(maxSalary)));
    	}else if ( !"".equals(minSalary)){
    		criteria.add(Restrictions.ge("salary", Integer.parseInt(minSalary)));
    	}else if ( !"".equals(maxSalary)){
    		criteria.add(Restrictions.le("salary", Integer.parseInt(maxSalary)));
    	}   	
        
        jobsList = (List<EmployerJob>)criteria.list();
    	
    	return jobsList;
    }

}
