package com.app.dtos;

import com.app.entities.Pharmacy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyManagerDto {

	private int id;
	private String email;
	private String name;
	private int pharmacyId;
	private String pharmacyName;
	private String password;
	
	
	
	public PharmacyManagerDto(int id, String email, String name, int pharmacyId) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.pharmacyId = pharmacyId;
	}
	
	
}
