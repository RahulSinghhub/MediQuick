package com.mediQuick.medicineApp.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate do b;
	
	@ReadOnlyProperty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDate;
	
	@Enumerated(EnumType.STRING)
	private Role role; 
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "address")
	private Address address;
	
	
}
