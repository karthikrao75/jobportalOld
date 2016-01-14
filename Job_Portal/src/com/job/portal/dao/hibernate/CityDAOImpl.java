package com.job.portal.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.job.portal.dao.CityDAO;
import com.job.portal.domain.City;
import com.job.portal.domain.State;


@Repository
public class CityDAOImpl implements CityDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CityDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addCity(City city) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(city);
        logger.info("City saved successfully, City Details="+ city);
    }
 
    
    @Override
    public void updateCity(City city) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(city);
        logger.info("City updated successfully, City Details="+ city);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<City> listCity() {
        Session session = this.sessionFactory.getCurrentSession();
        List<City> cityList = session.createQuery("from City").list();
        for(City city : cityList){
            logger.info("City List::"+ city);
        }
        return cityList;
    }
 
    @Override
    public City getCityById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        City city = (City) session.load(City.class, new Integer(id));
        logger.info("City loaded successfully, City details="+ city);
        return city;
    }
 
    @Override
    public void removeCity(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        City city = (City) session.load(City.class, new Integer(id));
        if(null != city){
            session.delete(city);
        }
        logger.info("State deleted successfully, state details="+ city);
    }
    
    public List<City> listCityByState(Integer stateId) {
    	Session session = this.sessionFactory.getCurrentSession();
        List<City> cityList = session.createQuery("from City as city where city.state.stateId=" + stateId).list();        
        return cityList;
    }

}
