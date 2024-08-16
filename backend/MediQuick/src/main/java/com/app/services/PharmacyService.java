package com.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.PharmacyDao;
import com.app.daos.PharmacyManagerDao;
import com.app.dtos.DaoToEntityConverter;
import com.app.dtos.PharmaManAndpharmaSignUpDto;
import com.app.dtos.PharmacyHomePageDto;
import com.app.entities.Pharmacy;
import com.app.entities.PharmacyManager;

@Service
@Transactional
public class PharmacyService {

	@Autowired
	private PharmacyDao pharmacyDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PharmacyManagerDao pharmacyManagerDao;
	
	public List<Pharmacy> findAllRestaurants() {
		return pharmacyDao.findAll();
	}
	
	public List<PharmacyHomePageDto> findAllPharmacyHomePageDtos() {
		List<Pharmacy> restList = pharmacyDao.findAll();
		List<PharmacyHomePageDto> restDtoList = new ArrayList<PharmacyHomePageDto>();
		restList.forEach(rest -> restDtoList.add(DaoToEntityConverter.pharmacyEntityToPharmacyHomePageDto(rest)));
		return restDtoList;
	}
	
	public boolean restManagerAndRestSignUp(PharmaManAndpharmaSignUpDto dto) {
		try {
			Pharmacy rest = new Pharmacy();
			rest.setName(dto.getPharmacyIdName());
			rest.setAdressText(dto.getPharmacyIdAdressText());
			rest.setPinCode(dto.getPharmacyIdPinCode());
			
			rest = pharmacyDao.save(rest);
			entityManager.refresh(rest);
			
			PharmacyManager restMan = new PharmacyManager();
			restMan.setName(dto.getManagerName());
			restMan.setEmail(dto.getManagerEmail());
			restMan.setPassword(dto.getManagerPassword());
			restMan.setPharmacyId(rest);
			
			restMan = pharmacyManagerDao.save(restMan);
			entityManager.refresh(restMan);
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
}
