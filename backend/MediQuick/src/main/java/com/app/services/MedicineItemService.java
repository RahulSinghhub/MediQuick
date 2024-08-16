package com.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.MedicineItemDao;
import com.app.daos.MedicineTypeDao;
import com.app.daos.PharmacyDao;
import com.app.dtos.DaoToEntityConverter;
import com.app.dtos.MedicineItemHomePageDto;
import com.app.entities.MedicineItem;
import com.app.entities.MedicineType;
import com.app.entities.Pharmacy;

@Service
public class MedicineItemService {

	@Autowired
	private MedicineItemDao medicineItemDao;
	
	@Autowired
	private PharmacyDao pharmacyDao;
	
	@Autowired
	private MedicineTypeDao MedicineTypeDao;
	
	public List<MedicineItem> findAllMedicineItems() {
		return medicineItemDao.findAll();
	}
	
	public List<MedicineItemHomePageDto> findAllMedicineItemsFromPharmacy(int pharmacyId) {
	    // Fetch Pharmacy object from the database
	    Pharmacy pharmacy = null;
	    try {
	        pharmacy = pharmacyDao.findById(pharmacyId).orElse(null);
	    } catch (Exception e) {
	        return null;
	    }

	    // If pharmacy is null, return an empty list or handle accordingly
	    if (pharmacy == null) {
	        return null;
	    }

	    // Fetch MedicineItems by Pharmacy ID instead of Pharmacy object
	    List<MedicineItem> medicineItems = medicineItemDao.findMedicinesItemsByPharmacyId(pharmacyId);
	    List<MedicineItemHomePageDto> medicineItemsDtos = new ArrayList<>();

	    // Convert and add MedicineItems to DTOs
	    medicineItems.forEach(medicineItem -> 
	        medicineItemsDtos.add(DaoToEntityConverter.medicineItemEntityToMedicineItemHomePageDto(medicineItem))
	    );

	    return medicineItemsDtos;
	}

	
	
	
	
	public List<MedicineItemHomePageDto> findAllMedicineItemsByIds(List<Integer> listOfMedicineItems) {
		List<MedicineItemHomePageDto> listOfMedicineItemsDto = new ArrayList<MedicineItemHomePageDto>();
		MedicineItem MedicineItem = null;
		
		for (Integer MedicineItemId : listOfMedicineItems) {
			
			try {
				System.out.println("Medicine item id = "+MedicineItemId);
				// MedicineItem = MedicineItemDao.getById(MedicineItemId);		// This method is giving exception
				MedicineItem = medicineItemDao.findById(MedicineItemId).orElse(null);
				System.out.println("Medicine item = "+MedicineItem);
				listOfMedicineItemsDto.add(DaoToEntityConverter.medicineItemEntityToMedicineItemHomePageDto(MedicineItem));
			} catch (Exception e) {
				System.out.println("Exception e = \n"+e);
				continue;
			}
		}
		return listOfMedicineItemsDto;
	}
	
	public boolean saveMedicineItem(MedicineItem Medicine) {
		MedicineItem MedicineItem = medicineItemDao.save(Medicine);
		System.out.println(MedicineItem.getId());
		return true;
	}
	
	public boolean saveMedicineItemDto(MedicineItemHomePageDto MedicineItemHomePageDto) {
		try {
			MedicineItem MedicineItem = new MedicineItem();
			
			MedicineType Medicinetype = MedicineTypeDao.getById(MedicineItemHomePageDto.getMedicineTypeId());
			Pharmacy restaurant = pharmacyDao.getById(MedicineItemHomePageDto.getPharmacyId());
			
			MedicineItem.setMedicineType(Medicinetype);
			MedicineItem.setPharmacy(restaurant);
			MedicineItem.setName(MedicineItemHomePageDto.getName());
			MedicineItem.setPrice(MedicineItemHomePageDto.getPrice());
			MedicineItem.setRequired(MedicineItemHomePageDto.isRequired());
			MedicineItem.setImagePath(MedicineItemHomePageDto.getImagePath());
			
			medicineItemDao.save(MedicineItem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
	
	public MedicineItemHomePageDto getDtoById(int MedicineItemId) {
		
		MedicineItem Medicineitem = medicineItemDao.getById(MedicineItemId);
		MedicineItemHomePageDto MedicineItemHomePageDto = DaoToEntityConverter.medicineItemEntityToMedicineItemHomePageDto(Medicineitem);
		return MedicineItemHomePageDto;
		
	}
	
	public boolean updateMedicineItem(MedicineItemHomePageDto MedicineItemHomePageDto) {
		try {
			MedicineItem MedicineItem = medicineItemDao.getById(MedicineItemHomePageDto.getId());
			MedicineItem.setName(MedicineItemHomePageDto.getName());
			MedicineItem.setMedicineType(MedicineTypeDao.getById(MedicineItemHomePageDto.getMedicineTypeId()));
			MedicineItem.setPrice(MedicineItemHomePageDto.getPrice());
			MedicineItem.setImagePath(MedicineItemHomePageDto.getImagePath());
			MedicineItem.setRequired(MedicineItemHomePageDto.isRequired());
			
			medicineItemDao.save(MedicineItem);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}



}
