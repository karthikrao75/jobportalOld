package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.EducationLevel;;
 
public interface EducationLevelDAO {
 
    public void addEducationLevel(EducationLevel educationLevel);
    public void updateEducationLevel(EducationLevel educationLevel);
    public List<EducationLevel> listEducationLevel();
    public EducationLevel getEducationLevelById(int id);
    public void removeEducationLevel(int id);
}