package com.mediQuick.medicineApp.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mediQuick.medicineApp.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotBlank
	@Email(message = "Invalid email")
	private String email;
	
	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@NotBlank(message = "enter first name")
	private String firstName;
	
	
	private String lastName;
	
	@NotBlank(message = "fill date of birth")
	private LocalDate dob;
	
	@Digits(fraction=0,integer=10)
	@NotBlank(message = "contact cannot be blank")
	private String contact;
	
	@NotBlank(message = "Address cannot be empty")
	private String addressLine;
	
	
	private String landmark;
	
	@NotEmpty(message = "at least 1 role should be chosen")
	private Set<User> roles = new HashSet<>();
	
	

}
