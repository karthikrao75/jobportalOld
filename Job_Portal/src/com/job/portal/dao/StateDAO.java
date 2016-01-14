package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.State;

public interface StateDAO {
	 
    public void addState(State state);
    public void updateState(State state);
    public List<State> listState();
    public State getStateById(int id);
    public List<State> listStateByCountry(Integer countryId);
    public void removeState(int id);
}