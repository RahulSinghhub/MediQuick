package com.mediQuick.medicineApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediQuick.medicineApp.service.MedicineService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private MedicineService homeService;
	
	public ResponseEntity<?> getAll(){
		return new ResponseEntity<>("hi",HttpStatus.OK);
	}
	

}
