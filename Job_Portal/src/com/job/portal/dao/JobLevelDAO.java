package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.JobLevel;;
 
public interface JobLevelDAO { 
    public void addJobLevel(JobLevel jobLevel);
    public void updateJobLevel(JobLevel jobLevel);
    public List<JobLevel> listJobLevel();
    public JobLevel getJobLevelById(int id);
    public void removeJobLevel(int id);
}