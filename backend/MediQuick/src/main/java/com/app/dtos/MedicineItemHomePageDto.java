package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineItemHomePageDto {
	
	private int id;
	private int medicineTypeId;
	private int pharmacyId;
	private String name;
	private double price;
	private boolean isRequired;
	private String imagePath;
	
}
