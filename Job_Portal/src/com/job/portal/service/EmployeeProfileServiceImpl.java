package com.job.portal.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

import com.job.portal.dao.EmployeeProfileDAO;
import com.job.portal.domain.EmployeeProfile;

public class EmployeeProfileServiceImpl implements EmployeeProfileService {
	
	private EmployeeProfileDAO employeeProfileDAO;	
		
	/**
	 * @return the employeeProfileDAO
	 */
	public EmployeeProfileDAO getEmployeeProfileDAO() {
		return employeeProfileDAO;
	}



	/**
	 * @param employeeProfileDAO the employeeProfileDAO to set
	 */
	public void setEmployeeProfileDAO(EmployeeProfileDAO employeeProfileDAO) {
		this.employeeProfileDAO = employeeProfileDAO;
	}



	@Override
	@Transactional
	public void addEmployeeProfile(EmployeeProfile employeeProfile, MultipartFile file, String uploadFilePath) {
		// TODO Auto-generated method stub		
		java.io.InputStream inputStream = null;
        java.io.OutputStream outputStream = null;
        String fileName = null;        
        try {
        	
	        if (file != null && file.getSize() > 0) {
	            inputStream = file.getInputStream();
	           /* if (file.getSize() > 10000) {
	                System.out.println("File Size:::" + file.getSize());
	                return "/uploadfile";
	            }*/
	            System.out.println("size::" + file.getSize());
	            fileName = uploadFilePath + File.separator + file.getOriginalFilename();
	            outputStream = new java.io.FileOutputStream(fileName);
	            System.out.println("fileName:" + file.getOriginalFilename());
	
	            int readBytes = 0;
	            byte[] buffer = new byte[10000];
	            while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
	                outputStream.write(buffer, 0, readBytes);
	            }
	            outputStream.close();
	            inputStream.close();
	            
	            employeeProfile.setResumePath(file.getOriginalFilename());
	        }
        }catch (Exception exception) {
        	System.out.println(" Error in upload file >>> " + exception.getMessage());
        }
        
        employeeProfileDAO.addEmployeeProfile(employeeProfile);		
	}
	
	@Override
	@Transactional
	public String checkFiveProfilesUploaded(Integer employeeId) {			
		Long count = employeeProfileDAO.getNoOfProfilesByEmployeeId(employeeId);		
		if(count==5) {
			return "1";
		}		
		return "0";		
	}
	
	@Override
	@Transactional
	 public List<EmployeeProfile> listEmployeeProfileByEmployeeId(Integer employeeId) {
		return employeeProfileDAO.listEmployeeProfileByEmployeeId(employeeId);
	}
	
	@Override
	@Transactional
	public EmployeeProfile getEmployeeProfileById(Integer employeeProfileId) {
		return employeeProfileDAO.getEmployeeProfileById(employeeProfileId);
	}
	
	@Override
	@Transactional
	public void removeEmployeeProfile(Integer employeeId) {
		employeeProfileDAO.removeEmployeeProfile(employeeId);
	}
	
	@Override
	@Transactional
	public List<EmployeeProfile> getCandidateList(String primarySkills, String minExperience, String maxExperience) {
		// TODO Auto-generated method stub
		
		List<EmployeeProfile> candidateList = employeeProfileDAO.getCanidateList(primarySkills, minExperience, maxExperience);
		return candidateList;
	}

}
