package com.job.portal.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.job.portal.dao.StateDAO;
import com.job.portal.domain.State;

@Repository
public class StateDAOImpl implements StateDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(StateDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addState(State state) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(state);
        logger.info("State saved successfully, State Details="+ state);
    }
 
    
    @Override
    public void updateState(State state) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(state);
        logger.info("State updated successfully, State Details="+ state);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<State> listState() {
        Session session = this.sessionFactory.getCurrentSession();
        List<State> stateList = session.createQuery("from State").list();
        for(State state : stateList){
            logger.info("State List::"+ state);
        }
        return stateList;
    }
 
    @Override
    public State getStateById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        State state = (State) session.load(State.class, new Integer(id));
        logger.info("State loaded successfully, State details="+ state);
        return state;
    }
 
    @Override
    public void removeState(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        State state = (State) session.load(State.class, new Integer(id));
        if(null != state){
            session.delete(state);
        }
        logger.info("State deleted successfully, state details="+ state);
    }
    
    public List<State> listStateByCountry(Integer countryId) {
    	Session session = this.sessionFactory.getCurrentSession();
        List<State> stateList = session.createQuery("from State as state where state.country.countryId=" + countryId).list();        
        return stateList;
    }

}
