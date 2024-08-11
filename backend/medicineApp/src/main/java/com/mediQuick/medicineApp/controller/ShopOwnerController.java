package com.mediQuick.medicineApp.controller;

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

import com.mediQuick.medicineApp.dto.ShopOwnerDto;
import com.mediQuick.medicineApp.service.ShopOwnerService;

@RestController
@RequestMapping("/shop")
public class ShopOwnerController {
	
	@Autowired
	private ShopOwnerService shopOwnerServ;
	
	@GetMapping
	public ResponseEntity<?> getAllShops(){
		return ResponseEntity.ok(shopOwnerServ.getAllVendors());
	}
	
	//get particular medicines
	@GetMapping("/{shopId}")
	public ResponseEntity<?> getShop(@PathVariable Long shopId){
		return ResponseEntity.ok(shopOwnerServ.getVendor(shopId));
	}
	
	
	
	//permission-admin
	@PostMapping("/{med}")
	public ResponseEntity<?> addShop(@RequestBody ShopOwnerDto shop ){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(shopOwnerServ.addVendor(shop));
	}
	
	//permission-admin
	@PutMapping("/{shopId}")
	public ResponseEntity<?> updateShop(@RequestParam Long shopId,@ModelAttribute ShopOwnerDto shop ){
		return ResponseEntity.ok(shopOwnerServ.updateVendor(shopId,shop));
	}
	
	@DeleteMapping("/{delId}")
	public ResponseEntity<?> deleteShop(@PathVariable Long delId){
		return ResponseEntity.ok(shopOwnerServ.delVendor(delId));
	}
	
	

}
