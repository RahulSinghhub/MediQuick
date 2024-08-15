package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyManagerHomePageDto {
	
	
	private int orderId;
	private int medicineItemId;
	private String medicineItemName;
	private String medicineItemImagePath;
	private Double medicineItemPrice;
	private int orderItemQuantity;

}
