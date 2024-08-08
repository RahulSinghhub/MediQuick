package com.mediQuick.medicineApp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "medicine")
public class Medicines {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long medicine_id;
	@Column(name = "name")
	private String medicine_name; 
	@Column(name = "tell")
	private String medicine_description;
	@Column(name = "price")
	private double medicine_price;
	@Column(name = "mfd")
	private LocalDate medicine_mfd_date;
	@Column(name = "exp")
	private LocalDate medicine_expiry_date;
	@Column(name = "company")
	private String medicine_company;
	
	
}
