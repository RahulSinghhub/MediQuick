package com.app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.ApiResponse;
import com.app.dtos.Credentials;
import com.app.dtos.CustomerDto;
import com.app.dtos.CustomerSignUpDto;
import com.app.dtos.DaoToEntityConverter;
import com.app.dtos.ListOfMedicineItemIds;
import com.app.dtos.MedicineItemHomePageDto;
import com.app.dtos.OrdersDto;
import com.app.dtos.PharmacyHomePageDto;
import com.app.dtos.PlaceOrderDto;
import com.app.entities.Customer;
import com.app.services.CustomerService;
import com.app.services.EmailService;
import com.app.services.MedicineItemService;
import com.app.services.OrdersService;
import com.app.services.PharmacyService;

import PasswordEncrypt_Decrypt.PasswordHashing;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private MedicineItemService medicineItemService;
	
	@Autowired
	private OrdersService ordersService;
	
	// Method to add customer to database
	@PostMapping("/customers/signup")
	public ResponseEntity<ApiResponse> signUp(@RequestBody CustomerSignUpDto customerSignUpDto) {
		String password = customerSignUpDto.getPassword();
		String hashedPassword = PasswordHashing.hashPassword(password);
		Customer cust = DaoToEntityConverter.customerSignUpDtoToCustomerEntity(customerSignUpDto);
		cust.setPassword(hashedPassword);
		customerService.saveCustomer(cust);
		
		String recipient=customerSignUpDto.getEmail();
		String subject="Welcome To MediQuick!!";
		String body = "Thank you for signing up with MediQuick. We look forward to serving you!";
		EmailService.sendEmail(recipient, subject, body);
		return ApiResponse.success("Customer added!");
	}
	
	@PostMapping("/customers/signin")
	public ResponseEntity<ApiResponse> signIn(@RequestBody Credentials cred) {
		String password = cred.getPassword();
		CustomerDto customerDto = customerService.findCustomerByEmail(cred);
		if(customerDto == null)
			return ApiResponse.error("Couldn't find Customer with that credentials");
		
		Customer customer=DaoToEntityConverter.customerSignIn(customerDto);
		System.out.println(customer.getPassword());
		String hashedPassword=customer.getPassword();
		if (PasswordHashing.checkPassword(password, hashedPassword)) {
		    return ApiResponse.success(customerDto);
		} else {
		    return ApiResponse.error("Invalid email or password");
		}
	}
	
	@GetMapping("/pharmacies")
	public ResponseEntity<ApiResponse> findAllPharmacies() {
		List<PharmacyHomePageDto> pharmaDtoList = pharmacyService.findAllPharmacyHomePageDtos();
		return ApiResponse.success(pharmaDtoList);
	}
	
	@GetMapping("/medicineitems/pharmacy/{id}")
	public ResponseEntity<ApiResponse> findMedicineItemsByPharmacyId(@PathVariable("id") int pharmacyId) {
		List<MedicineItemHomePageDto> MedicineItemsDtos = medicineItemService.findAllMedicineItemsFromPharmacy(pharmacyId);
		if (MedicineItemsDtos == null) {
			return ApiResponse.error("Could not find medicine items with that pharmacy id");
		}
		return ApiResponse.success(MedicineItemsDtos);
	}
	
	@PostMapping("/medicineitems/cart")
	public ResponseEntity<ApiResponse> getCartItems(@RequestBody ListOfMedicineItemIds listOfMedicineItemIds) {
		System.out.println(listOfMedicineItemIds);
		List<MedicineItemHomePageDto> MedicineItemsDtos = medicineItemService.findAllMedicineItemsByIds(listOfMedicineItemIds.getItemIds());
		System.out.println(MedicineItemsDtos);
		return ApiResponse.success(MedicineItemsDtos);
	}
	
	@PutMapping("/customers/{id}/address")
	public ResponseEntity<ApiResponse> updateAddress(@PathVariable("id") int id, @RequestBody CustomerDto customerDto) {
		boolean status = customerService.updateAddressByCustomerId(id, customerDto.getAddressText(), customerDto.getPinCode());
		if(!status)
			return ApiResponse.error("Couldn't update address");
		return ApiResponse.success("Ok");
	}
	
	@PostMapping("/orders/place")
	public ResponseEntity<ApiResponse> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
		System.out.println(placeOrderDto);
		OrdersDto ordersDto = ordersService.addOrder(placeOrderDto);
		if(ordersDto == null)
			return ApiResponse.error("Couldn't add order");
		return ApiResponse.success(ordersDto);
	}
	
	@GetMapping("/orders/customer/{id}")
	public ResponseEntity<ApiResponse> getAllOrdersbyCustomerId(@PathVariable("id") int customerId) {
		List<OrdersDto> ordersDtoList = ordersService.findAllOrdersByCustomerId(customerId);
		if(ordersDtoList == null || ordersDtoList.isEmpty())
			return ApiResponse.error("List empty!");
		return ApiResponse.success(ordersDtoList);
	}
	
	@PostMapping("/customers/forgot-password")
	public ResponseEntity<ApiResponse> forgotPassword(@RequestBody String email) {
	    if (email == null || email.isEmpty()) {
	        return ApiResponse.error("Email address is required.");
	    }

	    try {
	        // Check if the email exists in the database
	        CustomerDto customerDto = customerService.findCustomerByEmail(email);
	        if (customerDto == null) {
	            return ApiResponse.error("Could not find customer with that email address.");
	        }

	        // Generate a password reset token and save it to the database
	        String token = UUID.randomUUID().toString();
	        customerService.savePasswordResetToken(customerDto.getId(), token);

	        // Send an email to the customer with a link to reset their password
	        String recipient = customerDto.getEmail();
	        String subject = "Reset your password for MediQuick";
	        String body = "Please click the following link to reset your password: http://localhost:3000/reset-password/" + token;
	        EmailService.sendEmail(recipient, subject, body);

	        return ApiResponse.success("Password reset token generated and sent to customer.");
	    } catch (Exception e) {
	        // Log the exception
	     
	        return ApiResponse.error("An error occurred while processing the request.");
	    }
	}

}
