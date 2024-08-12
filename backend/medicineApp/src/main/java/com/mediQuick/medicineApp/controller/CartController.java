package com.mediQuick.medicineApp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediQuick.medicineApp.dto.CartAddDto;
import com.mediQuick.medicineApp.dto.CustomerDto;
import com.mediQuick.medicineApp.entity.CartItem;
import com.mediQuick.medicineApp.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@GetMapping
	public ResponseEntity<?> getAllCartItems(){
		return ResponseEntity.ok(cartService.getAllcart());
	}
	
	//get particular medicines
	@GetMapping("/{cartId}")
	public ResponseEntity<?> getCustomers(@PathVariable Long cartId){
		return ResponseEntity.ok(cartService.getcust(cartId));
	}
	
	
	
	//permission-admin
	@PostMapping("/{cart}")
	public ResponseEntity<?> addCustomers(@RequestBody CartAddDto cart ){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(cartService.addcust(cart));
	}
	
	//permission-admin
	@PutMapping("/{cartId}")
	public ResponseEntity<?> updateCustomers(@RequestParam Long cartId,@ModelAttribute CartAddDto cart ){
		return ResponseEntity.ok(cartService.updatecust(cartId,cart));
	}
	
	@DeleteMapping("/{delId}")
	public ResponseEntity<?> deleteCustomers(@PathVariable Long delId){
		return ResponseEntity.ok(cartService.delcust(delId));
	}

}
