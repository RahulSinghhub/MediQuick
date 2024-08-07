package com.mediQuick.medicineApp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediQuick.medicineApp.entity.Medicines;
import com.mediQuick.medicineApp.repositories.MedicineRepositories;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepositories medRepo;
	@Override
	public List<Medicines> getAllMed() {
		return medRepo.findAll();
	}

	@Override
	public Medicines addMed(Medicines med) {
		return medRepo.save(med);
	}

	@Override
	public Optional<Medicines> getMed(Long medId) {
		return medRepo.findById(medId);
	}

//	@Override
//	public Medicines updateMed(Long medId) {
//		return medRepo.save();
//	}

	@Override
	public String delMed(Long medId) {
		 if(medRepo.existsById(medId)) {
			 medRepo.deleteById(medId);
			 return "Item deleted";
		 }
		 return "item not deleted as not found";
	}

	@Override
	public String updateMed(Long medId, Medicines med) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String updateMed(Long medId, Medicines med) {
//		if(medRepo.existsById(medId)) {
//			medRepo.save(med);
//			return "updated";
//		}
//		return "not updated";	
//		}
//	
	

}
