package com.mediQuick.medicineApp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
	
	@NotBlank
	@Email(message = "Email invalid")
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

}
