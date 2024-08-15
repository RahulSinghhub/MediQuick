package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPersonDto {
	private int id;
	private String name;
	private String email;
	private boolean isAvailable;
	private String password;
}
