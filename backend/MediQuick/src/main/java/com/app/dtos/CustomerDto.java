package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	private int id;
	private String name;
	private String email;
	private String addressText;
	private int pinCode;
	private String password;	
}
