package com.job.portal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.job.portal.dao.EmployeeProfileDAO;
import com.job.portal.domain.EmployeeProfile;

public class EmployeeProfileDAOImpl implements EmployeeProfileDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(StateDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addEmployeeProfile(EmployeeProfile employeeProfile) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(employeeProfile);
        logger.info("EmployeeProfile saved successfully, EmployeeProfile Details="+ employeeProfile);
    }
    
    @Override
    public Long getNoOfProfilesByEmployeeId(Integer employeeId) {
    	Session session = this.sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select count(*) from EmployeeProfile employeeProfile where " +
    			"employeeProfile.employee.employeeId=:employeeId");
    	query.setInteger("employeeId", employeeId);
    	Long count = (Long)query.uniqueResult();    	
    	return count;
    }
 
    
    @Override
    public void updateEmployeeProfile(EmployeeProfile employeeProfile) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(employeeProfile);
        logger.info("EmployeeProfile updated successfully, EmployeeProfile Details="+ employeeProfile);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<EmployeeProfile> listEmployeeProfile() {
        Session session = this.sessionFactory.getCurrentSession();
        List<EmployeeProfile> employeeProfileList = session.createQuery("from EmployeeProfile").list();
        for(EmployeeProfile employeeProfile : employeeProfileList){
            logger.info("EmployeeProfile List::"+ employeeProfile);
        }
        return employeeProfileList;
    }
 
    @Override
    public EmployeeProfile getEmployeeProfileById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        EmployeeProfile employeeProfile = (EmployeeProfile) session.load(EmployeeProfile.class, new Integer(id));
        logger.info("EmployeeProfile loaded successfully, EmployeeProfile details="+ employeeProfile);
        return employeeProfile;
    }
 
    @Override
    public void removeEmployeeProfile(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        EmployeeProfile employeeProfile = (EmployeeProfile) session.load(EmployeeProfile.class, new Integer(id));
        if(null != employeeProfile){
            session.delete(employeeProfile);
        }
        logger.info("EmployeeProfile deleted successfully, employeeProfile details="+ employeeProfile);
    }
    
    public List<EmployeeProfile> listEmployeeProfileByEmployeeId(Integer employeeId) {
    	Session session = this.sessionFactory.getCurrentSession();
        List<EmployeeProfile> employeeProfileList = session.createQuery("from EmployeeProfile as employeeProfile where employeeProfile.employee.employeeId=" + employeeId).list();        
        return employeeProfileList;
    }
    
    public List<EmployeeProfile> getCanidateList(String primarySkills, String minExperience, String maxExperience) {    	   	
    	Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(EmployeeProfile.class);    	
    	
    	String[] primarySkillsArray = primarySkills.split(" ");        
        Disjunction primarySkillsDisjunction = Restrictions.disjunction(); // A or B or C
        for (int i=0; i < primarySkillsArray.length; i++) {
        	System.out.println (" PrimarySkills >>> " + primarySkillsArray[i]);
        	primarySkillsDisjunction.add(Restrictions.like("employeeProfileTitle", "%" + primarySkillsArray[i] + "%"));        	
        }
        
        criteria.add(Restrictions.and(primarySkillsDisjunction));
    	
    	if ( !"".equals(minExperience) && !"".equals(maxExperience)){
    		criteria.createAlias("employee", "e").
    		add(Restrictions.ge("e.employeeYearExp", minExperience)).
    		add(Restrictions.le("e.employeeYearExp", maxExperience));
    	}else if ( !"".equals(minExperience)){
    		criteria.createAlias("employee", "e").add(Restrictions.ge("e.employeeYearExp", minExperience));
    	}else if ( !"".equals(maxExperience)){
    		criteria.createAlias("employee", "e").add(Restrictions.le("e.employeeYearExp", maxExperience));
    	}
    		
    	List<EmployeeProfile> candidateList = (List<EmployeeProfile>)criteria.list();
    	System.out.println ( " Canidates Size >>>> " + candidateList.size());
    	return candidateList;
    }

}
