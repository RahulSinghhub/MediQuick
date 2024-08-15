
package com.mediQuick.medicineApp.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details")
public class OrderDetails extends BaseEntity {

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Medicines medicine;
	
	private int quantity;

	private double unitPrice;

	private double subtotal;

	private String productName;

}
