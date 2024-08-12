package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cart_items")

@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;

	@Transient
	public double getSubtotal() {
		return product.getUnitPrice() * quantity;
	}

}
