package com.mediQuick.medicineApp.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class CartItem extends BaseEntity {
	
	private User user;
	private Medicines medicine;
	private int quantity;
	private double price;

}
