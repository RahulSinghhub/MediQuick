package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Customer;
import com.app.entities.DeliveryPerson;
import com.app.entities.MedicineItem;
import com.app.entities.OrderItem;
import com.app.entities.Orders;
import com.app.entities.Payment;
import com.app.entities.Pharmacy;
import com.app.entities.PharmacyManager;
import com.app.services.CustomerService;
import com.app.services.DeliveryPersonService;
import com.app.services.MedicineItemService;
import com.app.services.MedicineTypeService;
import com.app.services.OrderItemService;
import com.app.services.OrdersService;
import com.app.services.PaymentService;
import com.app.services.PharmacyManagerService;
import com.app.services.PharmacyService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/test/")
public class TestControllerDemo {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private MedicineItemService medicineItemService;
	
	@Autowired
	private MedicineTypeService medicineTypeService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PharmacyManagerService pharmacyManagerService;
	
	@Autowired
	private PharmacyService restaurantService;
	
	@GetMapping("/customers")
	public List<Customer> findAllCustomers() {
		return customerService.findAllCustomers();
	}
	
	@GetMapping("/deliverypersons")
	public List<DeliveryPerson> findDeliveryPerson() {
		return deliveryPersonService.findAllDeliveryPerson();
	}
	
	@GetMapping("/medicineitems")
	public List<MedicineItem> findAllMedicineItems() {
		return medicineItemService.findAllMedicineItems();
	}
	

	
	@GetMapping("/orderitems")
	public List<OrderItem> findAllOrderItems() {
		return orderItemService.findAllOrderItems();
	}
	
	@GetMapping("/orders")
	public List<Orders> findAllOrders() {
		return ordersService.findAllOders();
	}

	@GetMapping("/payments")
	public List<Payment> findAllPayments() {
		return paymentService.findAllPayments();
	}
	
	@GetMapping("/pharmacymanagers")
	public List<PharmacyManager> findAllRestaurantManagers() {
		return pharmacyManagerService.findAllPharmacyManagers();
	}
	
	@GetMapping("/pharmacy")
	public List<Pharmacy> findAllRestaurants() {
		return restaurantService.findAllRestaurants();
	}
}
