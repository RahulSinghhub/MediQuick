package com.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicine_item")
public class MedicineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "medicine_type_id")
	private MedicineType medicineType;
    
	@ManyToOne
	@JoinColumn(name = "pharmacy_id")
	private Pharmacy pharmacy;
    
    @Column
	private String name;
    
    @Column
	private double price;
    
    @Column(name="required")
	private boolean required;
    
    @Column(name="image_path")
	private String imagePath;
    
    @OneToMany(mappedBy = "medicineItemid")
	private List<OrderItem> orderItem;

	public MedicineItem(int id, String name, double price, boolean required) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.required = required;
	}
	
	  @Override
	    public String toString() {
	        return "MedicineItem{" +
	               "id=" + id +
	               ", name='" + name + '\'' +
	               ", price=" + price +
	               '}';
	    }
	
	
	
}
