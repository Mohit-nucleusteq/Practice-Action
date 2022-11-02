package com.foodorderingsys.services;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderingsys.custom.exception.ServiceException;
import com.foodorderingsys.dao.BuisnessRepository;
import com.foodorderingsys.dto.BuisnessUserOutDto;
import com.foodorderingsys.model.BuisnessUser;


@Service
public class BuisnessServices {
	@Autowired
	BuisnessRepository buisnessRepository;


	public BuisnessUser signUpUser(BuisnessUser buisnessUser) {
		BuisnessUser user=null;
		if(!Pattern.matches("^[a-z\\sA-Z]{4,16}", buisnessUser.getName()))
		{
			throw new ServiceException("605","name is below 4 character or you have entered digit into it");
		}
		
		else
		if(!Pattern.matches("^[0-9]{10}", Long.toString( buisnessUser.getContactno())))
		{
			throw new ServiceException("606","Mobile number should be of 10 digit");
		}
		else
		if(!Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$", buisnessUser.getPassword()))
		{
			throw new ServiceException("607","password is weak");
		}
		
		try {
			user = buisnessRepository.save(buisnessUser);
		}catch(IllegalArgumentException e) {
			throw new ServiceException("620","entity must not be null");
			
		}catch(Exception e) {
			throw new ServiceException("603","Something went wrong in service layer");
		}
		return user;
	}
	public List<BuisnessUserOutDto> getAllBuisnessUserOutDto() {
		List<BuisnessUserOutDto> buisnessuserlist;
		try {
			buisnessuserlist = this.buisnessRepository.findAll().stream().map(this::convertBuisnessUserEntityToOutDto).collect(Collectors.toList());
			if(buisnessuserlist.size()==0||buisnessuserlist.isEmpty()==true) {
				throw new ServiceException("617","list is empty");
			}
		} catch (ServiceException e) {
			throw new ServiceException("603","Something went wrong in service layer");
		}
		return buisnessuserlist;
	}
	public BuisnessUserOutDto convertBuisnessUserEntityToOutDto(BuisnessUser buisnessUser) {
		BuisnessUserOutDto buisnessUserOutDto=new BuisnessUserOutDto();
		buisnessUserOutDto.setId(buisnessUser.getId());
		buisnessUserOutDto.setName(buisnessUser.getName());
		buisnessUserOutDto.setR_id(buisnessUser.getRestaurants().getId());
		return buisnessUserOutDto;
	}

}
