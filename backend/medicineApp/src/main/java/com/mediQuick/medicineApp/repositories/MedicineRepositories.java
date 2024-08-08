package com.mediQuick.medicineApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediQuick.medicineApp.entity.Medicines;


@Repository
public interface MedicineRepositories extends JpaRepository<Medicines, Long> {
	

}
