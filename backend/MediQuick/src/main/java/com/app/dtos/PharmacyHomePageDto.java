package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyHomePageDto {
	private int id;
	private String name;
	private String adressText;
	private int pinCode;
}
