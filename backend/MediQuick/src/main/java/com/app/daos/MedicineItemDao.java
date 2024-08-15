package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.MedicineItem;
import com.app.entities.Pharmacy;

@Repository
public interface MedicineItemDao extends JpaRepository<MedicineItem, Integer> {

	List<MedicineItem> findMedicinesItemsByPharmacyId(Pharmacy pharmacyId);
}
