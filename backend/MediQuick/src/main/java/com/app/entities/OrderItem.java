package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	
	@ManyToOne
	@JoinColumn(name="medicine_item_id")
	private MedicineItem medicineItemid;
	
	@Column(name="medicine_item_name")
	private String medicineItemName;
	
	@Column(name="medicine_item_price")
	private double medicineItemPrice;
	
	@Column
	private int quantity;

	public OrderItem(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	
}
