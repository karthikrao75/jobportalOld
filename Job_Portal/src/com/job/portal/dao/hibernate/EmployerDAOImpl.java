package com.job.portal.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.job.portal.dao.EmployerDAO;
import com.job.portal.domain.Employer;


@Repository
public class EmployerDAOImpl implements EmployerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployerDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addEmployer(Employer employer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(employer);
        logger.info("Employer saved successfully, Employer Details="+ employer);
    }
 
    
    @Override
    public void updateEmployer(Employer employer) {
        Session session = this.sessionFactory.getCurrentSession();     
        session.saveOrUpdate(employer);
        logger.info("Employer updated successfully, Employer Details="+ employer);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Employer> listEmployer() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Employer> employerList = session.createQuery("from Employer").list();
        for(Employer employer : employerList){
            logger.info("Employer List::"+ employer);
        }
        return employerList;
    }
 
    @Override
    public Employer getEmployerById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Employer employer = (Employer) session.load(Employer.class, new Integer(id));
        logger.info("Employer loaded successfully, Employer details="+ employer);
        return employer;
    }
 
    @Override
    public void removeEmployer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Employer employer = (Employer) session.load(Employer.class, new Integer(id));
        if(null != employer){
            session.delete(employer);
        }
        logger.info("Employer deleted successfully, Employer details="+ employer);
    }
    
    @Override
    public Employer getEmployerByEmail(String email) {
    	Employer employer = null;
        Session session = this.sessionFactory.getCurrentSession();      
        List<Employer> employerList = session.createQuery("from Employer where employerEmail='" + email + "'").list();
        
        if (null != employerList && employerList.size() > 0) {
        	employer = employerList.get(0);
		 }	 
        logger.info("Employer loaded successfully, Employer details="+ employer);
        return employer;
    }
    
    @Override
    public Employer getEmployerByMobile(String mobile) {
    	Employer employer = null;
        Session session = this.sessionFactory.getCurrentSession();      
        List<Employer> employerList = session.createQuery("from Employer where employerMobile='" + mobile + "'").list();
        
        if (null != employerList && employerList.size() > 0) {
        	employer = employerList.get(0);
		 }	 
        logger.info("Employer loaded successfully, Employer details="+ employer);
        return employer;
    }
    
    public Employer getEmployerForLogin(String email, String password){
    	Employer employer = null;
        Session session = this.sessionFactory.getCurrentSession();      
        List<Employer> employerList = session.createQuery("from Employer where employerEmail='" + email + "' and " +
        		"employerPassword='" + password + "'" ).list();
        
        if (null != employerList && employerList.size() > 0) {
        	employer = employerList.get(0);
		 }	 
        logger.info("Employer loaded successfully, Employer details="+ employer);
        return employer;
    }

}
