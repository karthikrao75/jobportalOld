package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.City;

public interface CityDAO {
	 
    public void addCity(City city);
    public void updateCity(City city);
    public List<City> listCity();
    public City getCityById(int id);
    public void removeCity(int id);
    public List<City> listCityByState(Integer stateId);
}