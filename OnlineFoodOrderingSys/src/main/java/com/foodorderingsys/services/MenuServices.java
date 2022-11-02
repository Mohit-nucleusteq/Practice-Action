package com.foodorderingsys.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.foodorderingsys.custom.exception.ServiceException;
import com.foodorderingsys.dao.MenuRepository;
import com.foodorderingsys.dao.RestaurantsRepository;

import com.foodorderingsys.model.Menu;

@Service
public class MenuServices {
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	RestaurantsRepository restaurantRepository;

	/*
	 * public Menu addItemToMenu(Menu menu) { Menu menu1=menuRepository.save(menu);
	 * return menu1; }
	 */

	public Menu updateMenu(int id, Menu menu) {
		menu.setId(id);
		try {
			menuRepository.save(menu);
		} catch (IllegalArgumentException e) {
	       throw new ServiceException("622","send menu id ,and entity also ");
		}catch (Exception e) {
			throw new ServiceException("603","something went wrong in service layer");
		}
		return menu;
	}

	public String deleteItem(int id) {
		try {
			menuRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ServiceException("623", "menur id is null please send some id");

		} catch (Exception e) {
			throw new ServiceException("610",
					"your id is not in the database put your correct id or Something went wrong in service layer");
		}

		return "Deleted Item SuccessFully";
	}

	public List<Menu> getMenu(int id) {
		List<Menu> listofitems=new ArrayList<Menu>();
		try {
			listofitems = menuRepository.getMenu(id);
			if(listofitems.isEmpty()==true) {
				throw new ServiceException("624","menu list is null ");
			}
		} catch (Exception e) {
			throw new ServiceException("603","something went wrong in service layer");
		}
		return listofitems;
	}

	public String addItem(Menu menu) {
		try {
			menuRepository.save(menu);
			if(menu.getPrice()==0) {
				throw new ServiceException("626","price must be grater than 0");
			}
			if(menu.getItem().length()<=5) {
				throw new ServiceException("627","item name must be greater than 5 characters and name should be understandble");
			}
		} catch (Exception e) {
			throw new ServiceException("603","something went wrong in service layer");
		}
		return "Updated Successfully";
	}

}
