package com.mediQuick.medicineApp.service;

import java.util.List;
import java.util.Optional;

import com.mediQuick.medicineApp.entity.Medicines;

public interface MedicineService {
	List<Medicines> getAllMed();

	Medicines addMed(Medicines med);

	Optional<Medicines> getMed(Long medId);

	//Medicines updateMed(Long medId);

	String delMed(Long medId);

	String updateMed(Long medId, Medicines med);
	
	

}
