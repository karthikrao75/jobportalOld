package com.job.portal.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository; 
import com.job.portal.dao.CountryDAO;
import com.job.portal.domain.Country;

 
@Repository
public class CountryDAOImpl implements CountryDAO{    
	
	private static final Logger logger = LoggerFactory.getLogger(CountryDAOImpl.class);
 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addCountry(Country country) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(country);
        logger.info("Country saved successfully, Country Details="+ country);
    }
 
    
    @Override
    public void updateCountry(Country country) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(country);
        logger.info("Country updated successfully, Country Details="+ country);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Country> listCountry() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Country> countryList = session.createQuery("from Country").list();
        for(Country country : countryList){
            logger.info("Country List::"+ country);
        }
        return countryList;
    }
 
    @Override
    public Country getCountryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Country country = (Country) session.load(Country.class, new Integer(id));
        logger.info("Country loaded successfully, Country details="+ country);
        return country;
    }
 
    @Override
    public void removeCountry(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Country country = (Country) session.load(Country.class, new Integer(id));
        if(null != country){
            session.delete(country);
        }
        logger.info("Person deleted successfully, person details="+ country);
    }
 
}