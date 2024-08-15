package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPersonHomePageDto {

	
	private int orderId;
	private String pharmacyName;
	private String pharmacyAddress;
	private int pharmacyPinCode;
	private String customerName;
	private String customerAddress;
	private int customerPinCode;
}
