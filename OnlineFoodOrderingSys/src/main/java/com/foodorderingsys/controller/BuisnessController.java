package com.foodorderingsys.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingsys.dto.AddMenuDto;
import com.foodorderingsys.model.BuisnessUser;
import com.foodorderingsys.model.Menu;
import com.foodorderingsys.model.PlaceOrder;
import com.foodorderingsys.model.Restaurant;
import com.foodorderingsys.services.BuisnessServices;
import com.foodorderingsys.services.MenuServices;
import com.foodorderingsys.services.PlaceOrderServices;
import com.foodorderingsys.services.RestaurantServices;

@RestController
@RequestMapping("/buisnessuser")
public class BuisnessController {
	@Autowired
	BuisnessServices buisnessServices;
	@Autowired
	MenuServices menuServices;
	@Autowired
	RestaurantServices restaurantServices;
	@Autowired
	PlaceOrderServices orderServices;

	@PostMapping("/signup")
	public ResponseEntity<BuisnessUser> userSignup(@RequestBody BuisnessUser buisnessUser) {
		BuisnessUser user;
		try {
			user = buisnessServices.signUpUser(buisnessUser);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of(user));

	}

	@PostMapping("/addRestaurantWithMenu")
	public ResponseEntity<Restaurant> addRestaurantWithMenu(@RequestBody AddMenuDto menuitems) {
		Restaurant restaurant = null;
		try {
			restaurant = restaurantServices.addRestaurantWithMenu(menuitems);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of(restaurant));
	}

//by rest id
	@GetMapping("/getMenu/{id}")
	public ResponseEntity<List<Menu>> getMenu(@PathVariable int id) {
		List<Menu> listofitems = null;
		try {
			listofitems = menuServices.getMenu(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of(listofitems));
	}

	@PutMapping("/updateMenu/{id}")
	public ResponseEntity<Menu> updateMenu(@PathVariable int id, @RequestBody Menu menu) {
		Menu menu1 = null;
		try {
			menu1 = menuServices.updateMenu(id, menu);
		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			e.printStackTrace();
		}
		return ResponseEntity.of(Optional.of(menu1));
	}

	@DeleteMapping("/deleteItem/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") int id) {
		String msg = null;
		try {
			msg = menuServices.deleteItem(id);
		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			e.printStackTrace();
		}
		return ResponseEntity.of(Optional.of(msg));
	}

	@PostMapping("/itemAddInMenu")
	public ResponseEntity<String> addItem(@RequestBody Menu menu) {

		try {
			menuServices.addItem(menu);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of("updated successfully"));

	}

	@GetMapping("/historyCheckByRestaurantId /{id}")
	public ResponseEntity<List<PlaceOrder>> getOrderDetailsByRestaurantId(@PathVariable int id) {
		List<PlaceOrder> orderlist;
		try {
			orderlist = orderServices.getOrderDetailsByRestaurantId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (orderlist.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.of(Optional.of(orderlist));
	}

	@PutMapping("/updateOrder/{id}")
	ResponseEntity<String> updateOrder(@PathVariable int id, @RequestBody PlaceOrder placeorder) {
		String msg = null;
		try {
			msg = orderServices.update(id, placeorder);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("order id wrong");
			// TODO: handle exception
		}
		return ResponseEntity.of(Optional.of(msg));
	}
}
