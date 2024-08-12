package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Address")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

	@Column(length = 10)
	private int pinCode;

	@Column(length = 30)
	private String city;

	@Column(length = 50)
	private String state;

	@Column(length = 100)
	private String landMark;

	@Column(length = 200)
	private String addressLine;


}
