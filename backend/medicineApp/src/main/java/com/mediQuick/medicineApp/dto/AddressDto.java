package com.mediQuick.medicineApp.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
	@NotBlank(message = "contact is required")
	private long contact;
	@NotBlank(message = "address is required")
	private String addressLine;

	private String landmark;
	@NotBlank(message = "pincode is required")
	private int pincode;

}
