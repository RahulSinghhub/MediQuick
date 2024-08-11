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

import com.mediQuick.medicineApp.dto.MedicineDto;
import com.mediQuick.medicineApp.entity.Medicines;
import com.mediQuick.medicineApp.service.MedicineService;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
	@Autowired
	private MedicineService medserv;
	
	//both admin and customers can view all medicines
	@GetMapping
	public ResponseEntity<?> getAllMedicines(){
		return ResponseEntity.ok(medserv.getAllMed());
	}
	
	//get particular medicines
	@GetMapping("/{medId}")
	public ResponseEntity<?> getMedicine(@PathVariable Long medId){
		return ResponseEntity.ok(medserv.getMed(medId));
	}
	
	
	
	//permission-admin
	@PostMapping("/{med}")
	public ResponseEntity<?> addMedicines(@RequestBody MedicineDto med ){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(medserv.addMed(med));
	}
	
	//permission-admin
	@PutMapping("/{medId}")
	public ResponseEntity<?> updateMedicines(@RequestParam Long medId,@ModelAttribute MedicineDto med ){
		return ResponseEntity.ok(medserv.updateMed(medId,med));
	}
	
	@DeleteMapping("/{delId}")
	public ResponseEntity<?> deleteMedicines(@PathVariable Long delId){
		return ResponseEntity.ok(medserv.delMed(delId));
	}
	
	
	
	

}
