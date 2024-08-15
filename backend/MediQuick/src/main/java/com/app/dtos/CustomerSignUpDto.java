package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignUpDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String addressText;
	private int pinCode;
}
