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
	private PharmacyManagerDao restaurantManagerDao;
	
	@Autowired
	private PharmacyDao restaurantDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	public List<PharmacyManager> findAllRestaurantManagers() {
		return restaurantManagerDao.findAll();
	}
	
	public Optional<PharmacyManager> getRestaurantManagerById(int id)
	{
		return restaurantManagerDao.findById(id);
	}
	
	public PharmacyManagerDto getRestaurantManagerDtoById(int id)
	{
		Optional<PharmacyManager> rest=getRestaurantManagerById(id);
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
	
     public boolean saveRestaurantManager(PharmacyManager rest)
     {
    	 restaurantManagerDao.save(rest);
    	 return true;
     }

     public PharmacyManagerDto findRestaurantManagerByEmailAndPassword(Credentials cred) {
    	 PharmacyManager rest= restaurantManagerDao.findByEmail(cred.getEmail());
 		if(rest == null || !rest.getPassword().equals(cred.getPassword()))
 			return null;
 		PharmacyManagerDto pharmacyManagerDto = DaoToEntityConverter.PharmacyManagerToPharmacymanagerDto(rest);
 		return pharmacyManagerDto;
 	}
     
     
     public PharmacyManagerDto findRestaurantManagerByEmail(Credentials cred) {
 		PharmacyManager restaurantManager = restaurantManagerDao.findByEmail(cred.getEmail());
 		if(restaurantManager == null) {
 			return null;
 		}
 		PharmacyManagerDto restaurantManagerDto = new PharmacyManagerDto();
 		BeanUtils.copyProperties(restaurantManager,restaurantManagerDto);
 		return restaurantManagerDto;
 	}
     
//     public List<RestaurantManagerHomePageDto> getAllArrivedOrdersFromRestaurant(int restaurantId,String status)
//     {
//    	 Restaurant restId=null;
//		    try {
//		    	restId=restaurantDao.findById(restaurantId).get();
//		    }
//		    catch(Exception e)
//		    {
//		    	return null;
//		    }
//		    
//		    List<Orders> orders=ordersDao.findOrdersByRestaurantId(restaurantId, status);
//		    
//		    List<RestaurantManagerHomePageDto> restDto= new ArrayList<RestaurantManagerHomePageDto>();
//		    
//		  //  orders.forEach(order->restDto.add(DaoToEntityConverter.toRestaurantManagerHomePageDto(order)));
//		    
//			return null;
//     }
     
}
