package com.mediQuick.medicineApp.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;

import com.mediQuick.medicineApp.entity.Address;
import com.mediQuick.medicineApp.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopOwnerDto {
private Integer id;
	
	@Column(length = 20)
	private String email;
	
	@Column(length = 20)
	private String password;
	
	@Column(length = 20)
	private String firstName;
	
	@Column(length = 20)
	private String lastName;
	
	@ReadOnlyProperty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDate;	
	
	private Address address;
	
	@NonNull
	private String shopName;
	
	@NotBlank(message = "GST number must be supplied")
	private String gstNo;
}
