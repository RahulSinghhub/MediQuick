package com.app.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {

	private List<MedicineItemInOrderDto> medicineItemInOrder;
	private int pharmacyIdId;
	private int customerId;	
}
