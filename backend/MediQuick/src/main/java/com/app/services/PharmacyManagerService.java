package com.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.OrdersDao;
import com.app.daos.PharmacyDao;
import com.app.daos.PharmacyManagerDao;
import com.app.dtos.Credentials;
import com.app.dtos.CustomerDto;
import com.app.dtos.DaoToEntityConverter;
import com.app.dtos.DeliveryPersonDto;
import com.app.dtos.PharmacyManagerDto;
import com.app.dtos.PharmacyManagerHomePageDto;
import com.app.entities.Customer;
import com.app.entities.OrderItem;
import com.app.entities.Orders;
import com.app.entities.Pharmacy;
import com.app.entities.PharmacyManager;

@Service
public class PharmacyManagerService {

	@Autowired
	private PharmacyManagerDao pharmacyManagerDao;
	
	@Autowired
	private PharmacyDao pharmacyDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	public List<PharmacyManager> findAllPharmacyManagers() {
		return pharmacyManagerDao.findAll();
	}
	
	public Optional<PharmacyManager> getPharmacyManagerById(int id)
	{
		return pharmacyManagerDao.findById(id);
	}
	
	
	
	public PharmacyManagerDto getPharmacyManagerDtoById(int id)
	{
		Optional<PharmacyManager> rest=getPharmacyManagerById(id);
		PharmacyManager r =null;
		try {
			r=rest.get();
		}
		catch(Exception e){
			r=null;
			return null;
		}
		PharmacyManagerDto restDto=DaoToEntityConverter.PharmacyManagerEntityToDto(r);
		return restDto;
		
	}
	
     public boolean savePharmacyManager(PharmacyManager rest)
     {
    	 pharmacyManagerDao.save(rest);
    	 return true;
     }

     public PharmacyManagerDto findPharmacyManagerByEmailAndPassword(Credentials cred) {
    	 PharmacyManager rest= pharmacyManagerDao.findByEmail(cred.getEmail());
 		if(rest == null || !rest.getPassword().equals(cred.getPassword()))
 			return null;
 		PharmacyManagerDto pharmacyManagerDto = DaoToEntityConverter.PharmacyManagerToPharmacymanagerDto(rest);
 		return pharmacyManagerDto;
 	}
     
     
     public PharmacyManagerDto findPharmacyManagerByEmail(Credentials cred) {
 		PharmacyManager pharmacyManager = pharmacyManagerDao.findByEmail(cred.getEmail());
 		if(pharmacyManager == null) {
 			return null;
 		}
 		PharmacyManagerDto pharmacyManagerDto = new PharmacyManagerDto();
 		BeanUtils.copyProperties(pharmacyManager,pharmacyManagerDto);
 		return pharmacyManagerDto;
 	}

    
    
}
