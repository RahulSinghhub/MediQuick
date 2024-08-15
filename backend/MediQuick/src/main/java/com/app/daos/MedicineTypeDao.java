package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.MedicineType;

public interface MedicineTypeDao extends JpaRepository<MedicineType, Integer> {

}
