package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.MedicineItem;
import com.app.entities.Pharmacy;

@Repository
public interface MedicineItemDao extends JpaRepository<MedicineItem, Integer> {

	List<MedicineItem> findMedicinesItemsByPharmacyId(Pharmacy pharmacyId);

	@Query("SELECT mi FROM MedicineItem mi WHERE mi.pharmacy.id = :pharmacyId")
    List<MedicineItem> findMedicinesItemsByPharmacyId(@Param("pharmacyId") int pharmacyId);
	
}
