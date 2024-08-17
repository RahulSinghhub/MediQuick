package com.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pharmacy")
public class Pharmacy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column(name="address_text")
	private String adressText;
	@Column(name="pin_code")
	private int pinCode;
	
	@OneToOne(mappedBy = "pharmacyId")
	private PharmacyManager pharmacymanager;
	
	@OneToMany(mappedBy = "pharmacy")
	private List<MedicineItem> medicineItems;
	
	@OneToMany(mappedBy = "pharmacyId")
	private List<Orders> orders;
	
	
	public Pharmacy(int id, String name, String adressText, int pinCode) {
		super();
		this.id = id;
		this.name = name;
		this.adressText = adressText;
		this.pinCode = pinCode;
	}
	
	 @Override
	    public String toString() {
	        return "Pharmacy{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", addressText='" + adressText + '\'' +
	                ", pinCode=" + pinCode +
	                '}';
	    }
	
	
	
}
