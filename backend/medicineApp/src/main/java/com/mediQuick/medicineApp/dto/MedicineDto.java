package com.mediQuick.medicineApp.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {
	
	private String medicineName;
	
	//images of medicines their urls
	@Column(length = 150)
	private String url;
	
	private String description;
	
	private double price;
	
	private LocalDate mfd;
	
	private LocalDate exp;
	
	private String medicineCompany;
	

}
