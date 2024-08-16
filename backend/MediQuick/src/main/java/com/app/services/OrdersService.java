package com.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.CustomerDao;
import com.app.daos.DeliveryPersonDao;
import com.app.daos.MedicineItemDao;
import com.app.daos.OrderItemDao;
import com.app.daos.OrdersDao;
import com.app.daos.PaymentDao;
import com.app.daos.PharmacyDao;
import com.app.dtos.DaoToEntityConverter;
import com.app.dtos.DeliveryPersonHomePageDto;
import com.app.dtos.OrdersDto;
import com.app.dtos.PlaceOrderDto;
import com.app.entities.Customer;
import com.app.entities.DeliveryPerson;
import com.app.entities.OrderItem;
import com.app.entities.Orders;
import com.app.entities.Payment;
import com.app.entities.Pharmacy;

@Service
@Transactional
public class OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private PharmacyDao pharmacyDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private MedicineItemDao MedicineItemDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private DeliveryPersonDao deliveryPersonDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Orders> findAllOders() {
		return ordersDao.findAll();
	}
	


	public OrdersDto addOrder(PlaceOrderDto placeOrderDto) {
		OrdersDto ordersDto = null;
		try {
			Orders order = new Orders();
			
			Customer cust = customerDao.getById(placeOrderDto.getCustomerId());
			Pharmacy rest = pharmacyDao.getById(placeOrderDto.getPharmacyIdId());
			
			order.setCustomerId(cust);
			order.setPharmacyId(rest);
			order.setAssignToDeliveryPersonId(null);
			order.setStatus("arrived");
			
			Orders ordersEntity = ordersDao.save(order);
			System.out.println(ordersEntity);
			
			placeOrderDto.getMedicineItemInOrder().forEach(orderItem -> orderItemDao.save(new OrderItem(
						0, ordersEntity,
						MedicineItemDao.getById(orderItem.getMedicineItemId()),
						orderItem.getMedicineName(),
						orderItem.getMedicinePrice()*orderItem.getMedicineQuantity(),
						orderItem.getMedicineQuantity()
					)));
			
			// save payment
			Payment payment = new Payment();
			payment.setCustomerId(cust);
			payment.setOrderId(ordersEntity);
			payment.setStatus("paid");
			paymentDao.save(payment);
			
			// construct orderDto to send back
			entityManager.refresh(ordersEntity);
			ordersDto = DaoToEntityConverter.orderToOrderDto(ordersEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return ordersDto;
	}



	public Optional<Orders> grtOrdersById(int id) {
		return ordersDao.findById(id);
	}
	
	public DeliveryPersonHomePageDto getdeliveryPersonHomePageDtoById (int id) {
		Optional<Orders> orders = grtOrdersById(id);
		Orders o = null;
		try {
			o = orders.get();
		} catch (Exception e) {
			o = null;
			return null;
		}
		
		DeliveryPersonHomePageDto Dto = DaoToEntityConverter.toDeliveryPersonDTO(o);
		return Dto;
		
	}
	
	public List<Orders> findArrivedOrdersByPharmacyIdAndStatus(int restId, String status) {
		Pharmacy rest = pharmacyDao.getById(restId);
		List<Orders> orders = new ArrayList<Orders>();
		orders = ordersDao.findByPharmacyIdAndStatus(rest, status);
//		System.out.println(orders);
		return orders;
	}
	
	public List<Orders> findAllOrdersByPharmacyid(int restId) {
		Pharmacy rest = pharmacyDao.getById(restId);
		List<Orders> ordersList = ordersDao.findByPharmacyId(rest);
		return ordersList;
	}
	
	public boolean setStatusForOrder(int orderId, String status) {
		try {
			Orders order = ordersDao.getById(orderId);
			order.setStatus(status);
			ordersDao.save(order);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public List<OrdersDto> findAllOrdersByCustomerId(int customerId) {
		List<OrdersDto> ordersDto = null;
		try {
			Customer cust = customerDao.getById(customerId);
			List<Orders> orderList = cust.getOrders();
			ordersDto = DaoToEntityConverter.ordersToOrdersDto(orderList);
		} catch (Exception e) {
			return null;
		}
		
		return ordersDto;
	}
	
	public List<DeliveryPersonHomePageDto> findAllOrdersByDeliveryPerson(int deliveryPersonId) {
		List<DeliveryPersonHomePageDto> dphpdtoList = new ArrayList<DeliveryPersonHomePageDto>();
		try {
			DeliveryPerson deliveryPerson = deliveryPersonDao.getById(deliveryPersonId);
			List<Orders> orders = deliveryPerson.getOrders();
			orders.forEach(order -> dphpdtoList.add(DaoToEntityConverter.toDeliveryPersonDTO(order)));
		} catch (Exception e) {
			return null;
		}
		return dphpdtoList;	
	}
	
	public boolean assignDeliveryPersonToOrder(int orderId, int deliveryPersonId) {
		try {
			Orders order = ordersDao.getById(orderId);
			DeliveryPerson deliveryPerson = deliveryPersonDao.getById(deliveryPersonId);
			order.setStatus("out for delivery");
			order.setAssignToDeliveryPersonId(deliveryPerson);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	public List<DeliveryPersonHomePageDto> findArrivedordersByDeliverypersonIdAndStatus(int deliverypersonId, String status) {
		DeliveryPerson deliveryperson = deliveryPersonDao.getById(deliverypersonId);
		List<Orders> orderslist = ordersDao.findByAssignToDeliveryPersonIdAndStatus(deliveryperson,status);
		List<DeliveryPersonHomePageDto> deliverypersonHomePageDtoList = new ArrayList<DeliveryPersonHomePageDto>();
		orderslist.forEach(order -> deliverypersonHomePageDtoList.add(DaoToEntityConverter.toDeliveryPersonDTO(order)));
		
		return deliverypersonHomePageDtoList;
	}




}
