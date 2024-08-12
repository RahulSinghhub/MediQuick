package com.mediQuick.medicineApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartAddDto {
	private Long medicineId;
	private Long CustomerId;
	private Integer quantity;
	

}
