package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmaManAndpharmaSignUpDto {
	private int pharmacyIdId;
	private String pharmacyIdName;
	private String pharmacyIdAdressText;
	private int pharmacyIdPinCode;
	
	private int managerId;
	private String managerName;
	private String managerEmail;
	private String managerPassword;

}
