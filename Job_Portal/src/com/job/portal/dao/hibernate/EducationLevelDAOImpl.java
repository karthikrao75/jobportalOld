package com.job.portal.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository; 
import com.job.portal.dao.EducationLevelDAO;
import com.job.portal.domain.EducationLevel;

 
@Repository
public class EducationLevelDAOImpl implements EducationLevelDAO {    
	
	private static final Logger logger = LoggerFactory.getLogger(EducationLevelDAOImpl.class);
 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addEducationLevel(EducationLevel educationLevel) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(educationLevel);
        logger.info("EducationLevel saved successfully, EducationLevel Details="+ educationLevel);
    }
 
    
    @Override
    public void updateEducationLevel(EducationLevel educationLevel) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(educationLevel);
        logger.info("EducationLevel updated successfully, EducationLevel Details="+ educationLevel);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<EducationLevel> listEducationLevel() {
        Session session = this.sessionFactory.getCurrentSession();
        List<EducationLevel> educationLevelList = session.createQuery("from EducationLevel").list();
        for(EducationLevel educationLevel : educationLevelList){
            logger.info("EducationLevel List::"+ educationLevel);
        }
        return educationLevelList;
    }
 
    @Override
    public EducationLevel getEducationLevelById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        EducationLevel educationLevel = (EducationLevel) session.load(EducationLevel.class, new Integer(id));
        logger.info("EducationLevel loaded successfully, EducationLevel details="+ educationLevel);
        return educationLevel;
    }
 
    @Override
    public void removeEducationLevel(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        EducationLevel educationLevel = (EducationLevel) session.load(EducationLevel.class, new Integer(id));
        if(null != educationLevel){
            session.delete(educationLevel);
        }
        logger.info("Education Level deleted successfully, Education Level details="+ educationLevel);
    }
 
}