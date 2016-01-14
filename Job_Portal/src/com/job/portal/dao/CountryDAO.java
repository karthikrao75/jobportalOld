package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.Country;
 
public interface CountryDAO {
 
    public void addCountry(Country country);
    public void updateCountry(Country country);
    public List<Country> listCountry();
    public Country getCountryById(int id);
    public void removeCountry(int id);
}