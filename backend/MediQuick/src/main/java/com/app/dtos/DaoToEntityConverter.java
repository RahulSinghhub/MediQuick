package com.app.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.app.entities.Customer;
import com.app.entities.DeliveryPerson;
import com.app.entities.MedicineType;
import com.app.entities.MedicineItem;
import com.app.entities.OrderItem;
import com.app.entities.Orders;
import com.app.entities.Pharmacy;
import com.app.entities.PharmacyManager;

@Component
public class DaoToEntityConverter {
	
	public static CustomerDto customerEntityToDto(Customer customer) {
		if(customer == null)
			return null;

		CustomerDto customerDto = new CustomerDto();
		// copyProperties(Object source, Object target, String... ignoreProperties)
		BeanUtils.copyProperties(customer, customerDto, "password");
		return customerDto;
		
	}
	
	public static DeliveryPersonHomePageDto toDeliveryPersonDTO(Orders entity) {
		if(entity == null)
			return null;
		DeliveryPersonHomePageDto dto = new DeliveryPersonHomePageDto();
		dto.setOrderId(entity.getId());
		dto.setPharmacyName(entity.getPharmacyId().getName());
		dto.setPharmacyAddress(entity.getPharmacyId().getAdressText());
		dto.setPharmacyPinCode(entity.getPharmacyId().getPinCode());
		dto.setCustomerName(entity.getCustomerId().getName());
		dto.setCustomerAddress(entity.getCustomerId().getAddressText());
		dto.setCustomerPinCode(entity.getCustomerId().getPinCode());
		
		return dto;
	}
	
	public static DeliveryPersonDto deliveryPersonEntityToDto(DeliveryPerson deliveryPerson) {
		if(deliveryPerson == null)
			return null;

		DeliveryPersonDto deliveryPersonDto = new DeliveryPersonDto();
		BeanUtils.copyProperties(deliveryPerson, deliveryPersonDto, "password");
		return deliveryPersonDto;
	}
	
	public static Customer customerSignUpDtoToCustomerEntity(CustomerSignUpDto customerSignUpDto) {
		Customer cust = new Customer();
		BeanUtils.copyProperties(customerSignUpDto, cust);
		return cust;
	}
	
	public static Customer customerSignIn(CustomerDto customerDto) {
		Customer cust = new Customer();
		BeanUtils.copyProperties(customerDto, cust);
		return cust;
	}
	
	public static DeliveryPerson DeliveryPersonSignIn(DeliveryPersonDto dpDto) {
		DeliveryPerson dp = new DeliveryPerson();
		BeanUtils.copyProperties(dpDto, dp);
		return dp;
	}
	
	public static PharmacyManager PharmacyManagerSignIn(PharmacyManagerDto pharmacyManagerDto) {
		PharmacyManager pharmacyManager = new PharmacyManager();
		BeanUtils.copyProperties(pharmacyManagerDto, pharmacyManager);
		return pharmacyManager;
	}
	
	public static PharmacyHomePageDto pharmacyEntityToPharmacyHomePageDto(Pharmacy rest) {
		PharmacyHomePageDto restHomePageDto = new PharmacyHomePageDto();
		BeanUtils.copyProperties(rest, restHomePageDto, "restaurantmanager", "foodItems", "orders");
		return restHomePageDto;
	}
	
	public static MedicineItemHomePageDto medicineItemEntityToMedicineItemHomePageDto(MedicineItem medicineItem) {
		MedicineItemHomePageDto medicineItemHomePageDto = new MedicineItemHomePageDto();
//		BeanUtils.copyProperties(medicineItem, medicineItemHomePageDto, "orderItem");
		medicineItemHomePageDto.setId(medicineItem.getId());
		medicineItemHomePageDto.setMedicineTypeId(medicineItem.getMedicineType().getId());
		medicineItemHomePageDto.setPharmacyId(medicineItem.getPharmacy().getId());
		medicineItemHomePageDto.setName(medicineItem.getName());
		medicineItemHomePageDto.setPrice(medicineItem.getPrice());
		medicineItemHomePageDto.setRequired(medicineItem.isRequired());
		medicineItemHomePageDto.setImagePath(medicineItem.getImagePath());
		return medicineItemHomePageDto;
	}
	
    public static PharmacyManagerDto PharmacyManagerEntityToDto(PharmacyManager pharmacyManager) {
		if(pharmacyManager==null)
			return null;
		PharmacyManagerDto pharmacyManagerDto=new PharmacyManagerDto();
		BeanUtils.copyProperties(pharmacyManager, pharmacyManagerDto,"password");
		return pharmacyManagerDto;		
	}
    
   public static PharmacyManagerHomePageDto toPharmacyManagerHomePageDto(OrderItem entity) {
	   if(entity==null)
	     return null;
	   PharmacyManagerHomePageDto dto=new PharmacyManagerHomePageDto();
	   dto.setOrderId(entity.getOrder().getId());
	   dto.setMedicineItemId(entity.getMedicineItemid().getId());
	   dto.setMedicineItemName(entity.getMedicineItemName());
	   dto.setMedicineItemImagePath(entity.getMedicineItemid().getImagePath());
	   dto.setMedicineItemPrice(entity.getMedicineItemid().getPrice());
	   dto.setOrderItemQuantity(entity.getQuantity());
	   
	   return dto;
   }
   
   public static List<MedicineItemInOrderDto> orderItemListToFoodItemInOrderDtoList(List<OrderItem> orderItems) {
	   
	   List<MedicineItemInOrderDto> medicineItems = new ArrayList<MedicineItemInOrderDto>();
	   orderItems.forEach(order -> {
		   MedicineItemInOrderDto medicineItem = new MedicineItemInOrderDto();
		   medicineItem.setMedicineItemId(order.getMedicineItemid().getId());
		   medicineItem.setMedicineName(order.getMedicineItemName());
		   medicineItem.setMedicinePrice(order.getMedicineItemPrice());
		   medicineItem.setMedicineQuantity(order.getQuantity());
		   medicineItem.setMedicineItemUrl(order.getMedicineItemid().getImagePath());
		   medicineItems.add(medicineItem);
	   });
	   return medicineItems;
   }
   
   public static OrdersDto orderToOrderDto(Orders order) {
	   OrdersDto orderDto = new OrdersDto();
	   
	   orderDto.setOrderId(order.getId());
	   orderDto.setCustomer(customerEntityToDto(order.getCustomerId()));
	   orderDto.setPharmacy(pharmacyEntityToPharmacyHomePageDto(order.getPharmacyId()));
	   orderDto.setStatus(order.getStatus());
	   
	   
	   DeliveryPerson dp = null;
	   // set delivery person as null if not present
	   try {
		   dp = order.getAssignToDeliveryPersonId();
		} catch (Exception e) {
			dp = null;
		}
	   
	   if(dp != null) {
		   orderDto.setDeliveryPerson(deliveryPersonEntityToDto(dp));
	   }
//	   List<OrderItem> list = ;
//	   System.out.print("List = "+list);
//	   System.out.println("The code reached here 3!!!!");
	   orderDto.setMedicineItems(orderItemListToFoodItemInOrderDtoList(order.getOrderItems()));
	   return orderDto;
   }
   


public static List<OrdersDto> ordersToOrdersDto(List<Orders> orders) {
	   
	   List<OrdersDto> ordersDtoList = new ArrayList<OrdersDto>();
	   System.out.println("Orders = "+orders);
	   orders.forEach(order -> {
		   ordersDtoList.add(orderToOrderDto(order));
	   });
	   
	   return ordersDtoList;
   }
   
   public static MedicineItemHomePageDto foodItemAddEntityToDto(MedicineItem medicine) {
		if(medicine == null)
			return null;

		MedicineItemHomePageDto medicineItemDto = new MedicineItemHomePageDto();
		medicineItemDto.setId(medicine.getId());
		medicineItemDto.setMedicineTypeId(medicine.getMedicineType().getId());
		medicineItemDto.setPharmacyId(medicine.getPharmacy().getId());
		medicineItemDto.setName(medicine.getName());
		medicineItemDto.setPrice(medicine.getPrice());
		medicineItemDto.setRequired(medicine.isRequired());
		medicineItemDto.setImagePath(medicine.getImagePath());
		
		return medicineItemDto;
		
	}
  
   	public static MedicineTypeDto  MedicineTypeToMedicineTypeDto(MedicineType medicineType) {
   		MedicineTypeDto medicineTypeDto = new MedicineTypeDto();
   		BeanUtils.copyProperties(medicineType, medicineTypeDto, "medicineItems");
   		return medicineTypeDto;
   	}
   	
   	public static PharmacyManagerDto  PharmacyManagerToPharmacymanagerDto(PharmacyManager pharmacymanager) {
   		PharmacyManagerDto pharmacyManagerDto = new PharmacyManagerDto();
   		pharmacyManagerDto.setId(pharmacymanager.getId());
   		pharmacyManagerDto.setName(pharmacymanager.getName());
   		pharmacyManagerDto.setEmail(pharmacymanager.getEmail());
   		pharmacyManagerDto.setPharmacyId(pharmacymanager.getPharmacyId().getId());
   		pharmacyManagerDto.setPharmacyName(pharmacymanager.getPharmacyId().getName());
   		return pharmacyManagerDto;
   	}
   	
//   	public static RestaurantManager restaurantManagerSignUpDtoToRestaurantManagerEntity(RestaurantManagerSignUpDto restaurantManagerSignUpDto) {
//   		RestaurantManager rest = new RestaurantManager();
//		BeanUtils.copyProperties(restaurantManagerSignUpDto, rest);
//		return rest;
//	}
	
}
