package com.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.MedicineTypeDao;
import com.app.dtos.DaoToEntityConverter;
import com.app.dtos.MedicineTypeDto;
import com.app.entities.MedicineType;

@Service
public class MedicineTypeService {

	@Autowired
	private MedicineTypeDao medicineTypeDao;
	
	public List<MedicineTypeDto> findAllMedicineTypes(){
		List<MedicineType> medicineTypeList = medicineTypeDao.findAll();
		List<MedicineTypeDto> MedicinetypeDtolist = new ArrayList<MedicineTypeDto>();
				
		medicineTypeList.forEach(medicineType -> MedicinetypeDtolist.add(DaoToEntityConverter.MedicineTypeToMedicineTypeDto(medicineType)));
		return MedicinetypeDtolist;
	}
}
