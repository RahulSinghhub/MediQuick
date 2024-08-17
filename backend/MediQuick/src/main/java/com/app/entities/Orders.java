package com.app.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	
	private Customer customerId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id" )
	private Pharmacy pharmacyId; 
	
	@Column
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "assigned_to_delivery_person_id")
	private DeliveryPerson assignToDeliveryPersonId;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems;
	
	@OneToOne(mappedBy = "orderId")
	@JsonBackReference
	private Payment payment;

	public Orders(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	
	
	public Orders(Customer customerId, Pharmacy pharmacyId, String status, DeliveryPerson assignToDeliveryPersonId) {
		super();
		this.customerId = customerId;
		this.pharmacyId = pharmacyId;
		this.status = status;
		this.assignToDeliveryPersonId = assignToDeliveryPersonId;
	}	
}
