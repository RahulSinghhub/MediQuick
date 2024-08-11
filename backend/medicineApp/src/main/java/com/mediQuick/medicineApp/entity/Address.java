package com.mediQuick.medicineApp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address extends BaseEntity{
	
	
	private long contact;
	@Column(length = 200)
	private String addressLine;
	@Column(length = 200)
	private String landmark;
	@Column(length = 6)
	private int pincode;
	

}
