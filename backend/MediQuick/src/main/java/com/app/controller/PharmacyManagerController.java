package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.ApiResponse;
import com.app.dtos.Credentials;
import com.app.dtos.DaoToEntityConverter;
import com.app.dtos.DeliveryPersonDto;
import com.app.dtos.MedicineItemHomePageDto;
import com.app.dtos.MedicineTypeDto;
import com.app.dtos.OrdersDto;
import com.app.dtos.PharmaManAndpharmaSignUpDto;
import com.app.dtos.PharmacyManagerDto;
import com.app.entities.Orders;
import com.app.entities.PharmacyManager;
import com.app.services.DeliveryPersonService;
import com.app.services.MedicineItemService;
import com.app.services.MedicineTypeService;
import com.app.services.OrdersService;
import com.app.services.PharmacyManagerService;
import com.app.services.PharmacyService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PharmacyManagerController {
	
	@Autowired
	private PharmacyManagerService pharmacyManagerService;
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private MedicineItemService medicineItemService;
	
	@Autowired
	private MedicineTypeService medicineTypeService;
	
	@Autowired
	private PharmacyService pharmacyService;

	
	@GetMapping("/pharmacymanager/{id}")
	public ResponseEntity<ApiResponse> getPharmacyManagerById(@PathVariable("id") int id) {
	    Optional<PharmacyManager> r = pharmacyManagerService.getPharmacyManagerById(id);
	    
	    // Check if the PharmacyManager is present
	    if (r.isPresent()) {
	        return ApiResponse.success(r.get());
	    } else {
	        return ApiResponse.error("Pharmacy Manager not found");
	    }
	}

	@PostMapping("/pharmacymanager/signin")
	public ResponseEntity<ApiResponse> signIn(@RequestBody Credentials cred)
	{
		PharmacyManagerDto pharmacyManagerDto =pharmacyManagerService.findPharmacyManagerByEmailAndPassword(cred);
		if(pharmacyManagerDto==null)
			return ApiResponse.error("not found");
		
		return ApiResponse.success(pharmacyManagerDto);
		
	}
	



	@GetMapping("/pharmacymanager/availabledeliveryperson/{status}")
	public ResponseEntity<ApiResponse> getDeliveryPersonByAvailable(@PathVariable("status") boolean status)
	{
		List<DeliveryPersonDto> dto = deliveryPersonService.findDeliveryPersonByIsAvailable(status);
		if(dto == null)
			return ApiResponse.error("not available");
		
		return ApiResponse.success(dto);

	}
	

	@PostMapping("/pharmacymanager/arrivedorders/{pharmacyId}")
	public ResponseEntity<ApiResponse> getArrivedOrders(@PathVariable("pharmacyId") int pharmacyId) {
		
		String status = "arrived";
		List<Orders> orders = ordersService.findArrivedOrdersByPharmacyIdAndStatus(pharmacyId, status);
		
		if(orders == null || orders.isEmpty())
			return ApiResponse.error("List Empty!");
		
		// orders is full
		System.out.println("The code reached here !!!!");
		List<OrdersDto> ordersDtoList = DaoToEntityConverter.ordersToOrdersDto(orders);
		
//		System.out.println(ordersDtoList);
		return ApiResponse.success(ordersDtoList);
	}
	
	@PostMapping("/pharmacymanager/allorders/{pharmacyId}")
	public ResponseEntity<ApiResponse> getAllOrdersByPharmacy(@PathVariable("pharmacyId") int pharmacyId) {
		List<Orders> orders = ordersService.findAllOrdersByPharmacyid(pharmacyId);
		if(orders == null || orders.isEmpty())
			return ApiResponse.error("List Empty!");
		List<OrdersDto> ordersDtoList = DaoToEntityConverter.ordersToOrdersDto(orders);
		return ApiResponse.success(ordersDtoList);
	}
	

	@PostMapping("/pharmacymanager/addmedicineitem")
	public ResponseEntity<ApiResponse> addMedicineItem(@RequestBody MedicineItemHomePageDto medicineItemHomePageDto) {

		boolean status = medicineItemService.saveMedicineItemDto(medicineItemHomePageDto);
		if(!status)
			return ApiResponse.error("Couldn't add Medicine item");
		
		return ApiResponse.success("Medicine item added");
	}

	@GetMapping("/medicinetypes")
	public ResponseEntity<ApiResponse> getAllMedicineTypes() {
		List<MedicineTypeDto> medicineTypes = medicineTypeService.findAllMedicineTypes();
		return ApiResponse.success(medicineTypes);
	}
	
	@GetMapping("/medicineTypes/edit/{medicineItemId}")
	public ResponseEntity<ApiResponse> getMedicineItemDetails(@PathVariable("medicineItemId") int medicineItemId) {
		MedicineItemHomePageDto medicineItemHomePageDto = medicineItemService.getDtoById(medicineItemId);
		List<MedicineTypeDto> medicineTypes = medicineTypeService.findAllMedicineTypes();
		
		List<Object> resultData = new ArrayList<Object>();
		resultData.add(medicineItemHomePageDto);
		resultData.add(medicineTypes);
		
		return ApiResponse.success(resultData);
	}
	
	@PostMapping("/medicineTypes/edit/{medicineItemId}")
	public ResponseEntity<ApiResponse> updateMedicineItemDetails(@RequestBody MedicineItemHomePageDto medicineItemHomePageDto) {
		boolean status = medicineItemService.updateMedicineItem(medicineItemHomePageDto);
		if(!status)
			return ApiResponse.error("Couldn't update medicine item");
		
		return ApiResponse.success("medicine item updated");
	}
	
	@PostMapping("/orders/assign/{orderId}/{deliveryPersonId}")
	public ResponseEntity<ApiResponse> assignDeliveryPersonToOrder
		(@PathVariable("orderId") int orderId, @PathVariable("deliveryPersonId") int deliveryPersonId) {
		boolean status = ordersService.assignDeliveryPersonToOrder(orderId, deliveryPersonId);
		if(status == false)
			ApiResponse.error("Order not assigned");
		
		return ApiResponse.success("Order assigned successfully");
	}
	
	@GetMapping("/medicineitem/pharmacy/{pharmacyId}")
	public ResponseEntity<ApiResponse> getAllMedicineItemsByPharmacyId(@PathVariable("pharmacyId") int pharmacyId) {
		List<MedicineItemHomePageDto> medicineItemDtos = medicineItemService.findAllMedicineItemsFromPharmacy(pharmacyId);
		if(medicineItemDtos == null || medicineItemDtos.isEmpty())
			return ApiResponse.error("No medicine items found, please add medicine items.");
		return ApiResponse.success(medicineItemDtos);
	}
	
	@PostMapping("/pharmacymanager/signup")
	public ResponseEntity<ApiResponse> restManagerAndRestSignUp(@RequestBody PharmaManAndpharmaSignUpDto dto) {
		boolean status = pharmacyService.restManagerAndRestSignUp(dto);
		if(status)
			return ApiResponse.success("Added pharmacy and pharmacy Manager");
		return ApiResponse.error("Could not add pharmacy and pharmacy Manager");
	}

}





