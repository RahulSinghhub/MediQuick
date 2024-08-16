package com.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Medicine_type")
public class MedicineType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "medicineType")
	private List<MedicineItem> MedicineItem;

	
	public MedicineType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	 @Override
	    public String toString() {
	        return "MedicineType{" +
	               "id=" + id +
	               ", name='" + name + '\'' +
	               '}';
	    }
	
}
