package com.mediQuick.medicineApp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicine")
public class Medicines extends BaseEntity {
	
	@Column(length=50 ,unique = true)
	private String medicineName;
	
	//images of medicines their urls
	@Column(length = 150)
	private String url;
	
	@Column(name = "medicine_description")
	private String description;
	
	@Column(name = "medicine_price")
	private double price;
	
	@Column(name = "mfd")
	private LocalDate mfd;
	
	@Column(name = "exp")
	private LocalDate exp;
	
	@Column(name = "companyName")
	private String medicineCompany;
	
	
}
