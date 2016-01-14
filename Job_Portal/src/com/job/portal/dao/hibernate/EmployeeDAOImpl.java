package com.job.portal.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.job.portal.dao.EmployeeDAO;
import com.job.portal.domain.Employee;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
        logger.info("Employee saved successfully, Employee Details="+ employee);
    }
 
    
    @Override
    public void updateEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();     
        session.saveOrUpdate(employee);
        logger.info("Employee updated successfully, Employee Details="+ employee);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> listEmployee() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Employee> employeeList = session.createQuery("from Employee").list();
        for(Employee employee : employeeList){
            logger.info("Employee List::"+ employee);
        }
        return employeeList;
    }
 
    @Override
    public Employee getEmployeeById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Employee employee = (Employee) session.load(Employee.class, new Integer(id));
        logger.info("Employee loaded successfully, Employee details="+ employee);
        return employee;
    }
 
    @Override
    public void removeEmployee(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.load(Employee.class, new Integer(id));
        if(null != employee){
            session.delete(employee);
        }
        logger.info("Employee deleted successfully, Employee details="+ employee);
    }
    
    @Override
    public Employee getEmployeeByEmail(String email) {
    	Employee employee = null;
        Session session = this.sessionFactory.getCurrentSession();      
        List<Employee> employeeList = session.createQuery("from Employee where employeeEmail='" + email + "'").list();
        
        if (null != employeeList && employeeList.size() > 0) {
        	employee = employeeList.get(0);
		 }	 
        logger.info("Employee loaded successfully, Employee details="+ employee);
        return employee;
    }
    
    @Override
    public Employee getEmployeeByMobile(String mobile) {
    	Employee employee = null;
        Session session = this.sessionFactory.getCurrentSession();      
        List<Employee> employeeList = session.createQuery("from Employee where employeeMobile='" + mobile + "'").list();
        
        if (null != employeeList && employeeList.size() > 0) {
        	employee = employeeList.get(0);
		 }	 
        logger.info("Employee loaded successfully, Employee details="+ employee);
        return employee;
    }
    
    public Employee getEmployeeForLogin(String email, String password){
    	Employee employee = null;
        Session session = this.sessionFactory.getCurrentSession();      
        List<Employee> employeeList = session.createQuery("from Employee where employeeEmail='" + email + "' and " +
        		"employeePassword='" + password + "'" ).list();
        
        if (null != employeeList && employeeList.size() > 0) {
        	employee = employeeList.get(0);
		 }	 
        logger.info("Employee loaded successfully, Employee details="+ employee);
        return employee;
    }

}
