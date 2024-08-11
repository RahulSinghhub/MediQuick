package com.mediQuick.medicineApp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediQuick.medicineApp.customException.ServiceException;
import com.mediQuick.medicineApp.dto.MedicineDto;
import com.mediQuick.medicineApp.entity.Medicines;
import com.mediQuick.medicineApp.repositories.MedicineRepositories;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

	
	@Autowired
	private MedicineRepositories medRepo;
	
	@Autowired
	private ModelMapper modelMap;
	

	@Override
	public List<MedicineDto> getAllMed() {
		return medRepo.findAll()
				.stream()
				.map(med ->
				modelMap.map(med, MedicineDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public MedicineDto addMed(MedicineDto med) {
		Medicines medEntity = modelMap.map(med, Medicines.class);
	    Medicines savedMed = medRepo.save(medEntity);
	    return modelMap.map(savedMed, MedicineDto.class);
	}

	@Override
	public Optional<MedicineDto> getMed(Long medId) {
	    Optional<Medicines> savedMed = medRepo.findById(medId);

	    return savedMed.map(med -> modelMap.map(med, MedicineDto.class))
	                   .or(() -> {
	                       throw new ServiceException("Medicine with ID " + medId + " not found");
	                   });
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
	public String updateMed(Long medId, MedicineDto med) {
		Optional<Medicines> medEntity = medRepo.findById(medId);
		if(medEntity.isPresent()) {
			
		}
		
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
