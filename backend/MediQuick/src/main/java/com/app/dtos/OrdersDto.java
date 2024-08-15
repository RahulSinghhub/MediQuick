package com.app.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {
	private int orderId;
	private List<MedicineItemInOrderDto> medicineItems;
	private CustomerDto customer;
	private PharmacyHomePageDto pharmacy;
	private String status;
	private DeliveryPersonDto deliveryPerson;
	
	public OrdersDto(int orderId, List<MedicineItemInOrderDto> medicineItems, CustomerDto customer,
			PharmacyHomePageDto pharmacy, String status) {
		super();
		this.orderId = orderId;
		this.medicineItems = medicineItems;
		this.customer = customer;
		this.pharmacy = pharmacy;
		this.status = status;
	}
	
}
