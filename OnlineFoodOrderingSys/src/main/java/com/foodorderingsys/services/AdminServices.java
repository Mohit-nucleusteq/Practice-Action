package com.foodorderingsys.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodorderingsys.custom.exception.ServiceException;
import com.foodorderingsys.dao.AdminRepository;
import com.foodorderingsys.model.Admin;

@Service
public class AdminServices {
	@Autowired
	private AdminRepository adminRepository;
	public AdminServices(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	public Admin addAdmin(Admin admin) {
		if(!Pattern.matches("^[a-z\\sA-Z]{4,16}",admin.getName()))
		{
			throw new ServiceException("605","name is below 4 character or you have entered digit into it");
		}
		else
		if(!Pattern.matches("^[0-9]{10}", Long.toString(admin.getContactno())))
		{
			throw new ServiceException("606","Mobile number should be of 10 digit");
		}
		else
		if(!Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$",admin.getPassword()))
		{
			throw new ServiceException("607","password is weak");
		}
		
		try {
			Admin adminObj = adminRepository.save(admin);
			return adminObj;
		}catch(IllegalArgumentException e) {
			throw new ServiceException("602","admin info must not be null");
			
		}catch(Exception e) {
			throw new ServiceException("603","Something went wrong in service layer");
		}
		
	}

	public List<Admin> getAdmin() {
		List<Admin> listadmin=new ArrayList<Admin>();
		try {
		listadmin = this.adminRepository.findAll();
		 	if(listadmin.isEmpty()==true||listadmin.size()==0) {
		 		throw new ServiceException("604","list is completely null");
		 	}
		}
		catch(Exception e) {
			throw new ServiceException("603","something went wrong in servicelayer");
		}
		return listadmin;
	}

}
