package com.job.portal.dao;

import java.util.List;

import com.job.portal.domain.Employee;

public interface EmployeeDAO {
	
	public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public List<Employee> listEmployee();
    public Employee getEmployeeById(int id);
    public void removeEmployee(int id);
    public Employee getEmployeeByEmail(String email);
    public Employee getEmployeeByMobile(String mobile);
    public Employee getEmployeeForLogin(String email, String password);

}
