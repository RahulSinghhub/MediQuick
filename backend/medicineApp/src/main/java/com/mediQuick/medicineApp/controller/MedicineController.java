package com.mediQuick.medicineApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/medicine")
public class MedicineController {
	@Autowired
	private MedicineService medserv;
	
	
	

}
