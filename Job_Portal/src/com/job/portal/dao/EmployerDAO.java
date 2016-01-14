package com.job.portal.dao;

import java.util.List;
import com.job.portal.domain.Employer;

public interface EmployerDAO {
	
	public void addEmployer(Employer employee);
    public void updateEmployer(Employer employee);
    public List<Employer> listEmployer();
    public Employer getEmployerById(int id);
    public void removeEmployer(int id);
    public Employer getEmployerByEmail(String email);
    public Employer getEmployerByMobile(String mobile);
    public Employer getEmployerForLogin(String email, String password);

}
