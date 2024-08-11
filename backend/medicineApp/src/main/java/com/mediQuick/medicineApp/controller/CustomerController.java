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

import com.mediQuick.medicineApp.dto.CustomerDto;
import com.mediQuick.medicineApp.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService custService;
	
	@GetMapping
	public ResponseEntity<?> getAllCustomers(){
		return ResponseEntity.ok(custService.getAllcust());
	}
	
	//get particular medicines
	@GetMapping("/{custId}")
	public ResponseEntity<?> getCustomers(@PathVariable Long custId){
		return ResponseEntity.ok(custService.getcust(custId));
	}
	
	
	
	//permission-admin
	@PostMapping("/{med}")
	public ResponseEntity<?> addCustomers(@RequestBody CustomerDto med ){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(custService.addcust(med));
	}
	
	//permission-admin
	@PutMapping("/{custId}")
	public ResponseEntity<?> updateCustomers(@RequestParam Long custId,@ModelAttribute CustomerDto cust ){
		return ResponseEntity.ok(custService.updatecust(custId,cust));
	}
	
	@DeleteMapping("/{delId}")
	public ResponseEntity<?> deleteCustomers(@PathVariable Long delId){
		return ResponseEntity.ok(custService.delcust(delId));
	}

}
