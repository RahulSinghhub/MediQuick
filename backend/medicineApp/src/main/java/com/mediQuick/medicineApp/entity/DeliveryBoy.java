package com.mediQuick.medicineApp.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue(value = "deliveryBoy")
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "deliveryBoy")
@Entity
@Data
@NoArgsConstructor
public class DeliveryBoy extends User {

	@Column(length = 40)
	private String licenseNO;

}
