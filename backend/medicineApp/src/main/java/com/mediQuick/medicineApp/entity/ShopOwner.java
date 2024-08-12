package com.mediQuick.medicineApp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@DiscriminatorValue(value = "shopOwner")
@PrimaryKeyJoinColumn(name = "userId")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopOwner")
public class ShopOwner extends User {
	private String shopName;
	private String gstNo;
}
