package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineItemInOrderDto {
	private int medicineItemId;
	private String medicineName;
	private double medicinePrice;
	private int medicineQuantity;
	private String medicineItemUrl;
	
	
	public MedicineItemInOrderDto(int medicineItemId, String medicineName, double medicinePrice, int medicineQuantity) {
		super();
		this.medicineItemId = medicineItemId;
		this.medicineName = medicineName;
		this.medicinePrice = medicinePrice;
		this.medicineQuantity = medicineQuantity;
	}
	
	
}
