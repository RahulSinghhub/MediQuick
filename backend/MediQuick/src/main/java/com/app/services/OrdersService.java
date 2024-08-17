package com.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.customException.IllegealArgExcp;
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
	    try {
	        // Retrieve Customer and Pharmacy by ID
	        Customer customer = customerDao.findById(placeOrderDto.getCustomerId())
	                            .orElseThrow(() -> new RuntimeException("Customer not found"));
	        Pharmacy pharmacy = pharmacyDao.findById(placeOrderDto.getPharmacyIdId())
	                            .orElseThrow(() -> new RuntimeException("Pharmacy not found"));

	        // Create and save the order
	        Orders order = new Orders();
	        order.setCustomerId(customer);
	        order.setPharmacyId(pharmacy);
	        order.setAssignToDeliveryPersonId(null); // Initially not assigned
	        order.setStatus("arrived");

	        Orders savedOrder = ordersDao.save(order);

	        // Save each order item
	        placeOrderDto.getMedicineItemInOrder().forEach(orderItem -> {
	            OrderItem newItem = new OrderItem(
	                0,
	                savedOrder,
	                MedicineItemDao.findById(orderItem.getMedicineItemId())
	                               .orElseThrow(() -> new RuntimeException("Medicine item not found")),
	                orderItem.getMedicineName(),
	                orderItem.getMedicinePrice() * orderItem.getMedicineQuantity(),
	                orderItem.getMedicineQuantity()
	            );
	            orderItemDao.save(newItem);
	        });

	        // Save the payment details
	        Payment payment = new Payment();
	        payment.setCustomerId(customer);
	        payment.setOrderId(savedOrder);
	        payment.setStatus("paid");
	        paymentDao.save(payment);

	        // Refresh the order to get updated data and convert to DTO
	        entityManager.refresh(savedOrder);
	        return DaoToEntityConverter.orderToOrderDto(savedOrder);

	    } catch (RuntimeException e) {
	        e.printStackTrace();
	        return null;
	    }
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
	    Pharmacy rest = pharmacyDao.findById(restId).orElseThrow(() -> new RuntimeException("Pharmacy not found"));
	    return ordersDao.findByPharmacyIdAndStatus(rest, status);
	}

	
	public List<Orders> findAllOrdersByPharmacyid(int restId) {
	    Pharmacy rest = pharmacyDao.findById(restId)
	                               .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
	    return ordersDao.findByPharmacyId(rest);
	}

	
	public boolean setStatusForOrder(int orderId, String status) throws IllegealArgExcp {
        try {
            Orders order = ordersDao.findById(orderId)
                                    .orElseThrow(() -> new RuntimeException("Order not found"));

            // Validate status if necessary
            if (!isValidStatus(status)) {
                throw new IllegalArgumentException("Invalid status provided");
            }

            order.setStatus(status);
            ordersDao.save(order);
        } catch (RuntimeException e) {
            logger.error("Error updating status for order ID {}: {}", orderId, e.getMessage());
            return false;
        }

        return true;
    }
	 private boolean isValidStatus(String status) {
	     
	        return status != null && !status.trim().isEmpty();
	    }
	private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);
	public List<OrdersDto> findAllOrdersByCustomerId(int customerId) {
        List<OrdersDto> ordersDto = new ArrayList<>();
        try {
            Customer cust = customerDao.findById(customerId)
                                      .orElseThrow(() -> new RuntimeException("Customer not found"));
            List<Orders> orderList = cust.getOrders();
            ordersDto = DaoToEntityConverter.ordersToOrdersDto(orderList);
        } catch (Exception e) {
            logger.error("Error retrieving orders for customer ID {}: {}", customerId, e.getMessage());
        }
        
        return ordersDto;
    }
	
	public List<DeliveryPersonHomePageDto> findAllOrdersByDeliveryPerson(int deliveryPersonId) {
        List<DeliveryPersonHomePageDto> dphpdtoList = new ArrayList<>();
        try {
            DeliveryPerson deliveryPerson = deliveryPersonDao.findById(deliveryPersonId)
                    .orElseThrow(() -> new RuntimeException("Delivery Person not found"));

            List<Orders> orders = deliveryPerson.getOrders();
            if (orders != null) {
                dphpdtoList = orders.stream()
                        .map(DaoToEntityConverter::toDeliveryPersonDTO)
                        .collect(Collectors.toList());
            }
        } catch (RuntimeException e) {
            logger.error("Error fetching orders for delivery person ID {}: {}", deliveryPersonId, e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
        }
        return dphpdtoList;
    }
	
	  public boolean assignDeliveryPersonToOrder(int orderId, int deliveryPersonId) {
	        try {
	            Orders order = ordersDao.findById(orderId)
	                    .orElseThrow(() -> new RuntimeException("Order not found"));
	            DeliveryPerson deliveryPerson = deliveryPersonDao.findById(deliveryPersonId)
	                    .orElseThrow(() -> new RuntimeException("Delivery Person not found"));

	            order.setStatus("out for delivery");
	            order.setAssignToDeliveryPersonId(deliveryPerson);
	            ordersDao.save(order); // Ensure changes are saved

	        } catch (RuntimeException e) {
	            logger.error("Error assigning delivery person to order ID {}: {}", orderId, e.getMessage());
	            return false;
	        } catch (Exception e) {
	            logger.error("Unexpected error: {}", e.getMessage());
	            return false;
	        }
	        return true;
	    }
	
	
	
	  public List<DeliveryPersonHomePageDto> findArrivedordersByDeliverypersonIdAndStatus(int deliveryPersonId, String status) {
	        List<DeliveryPersonHomePageDto> deliveryPersonHomePageDtoList = new ArrayList<>();
	        try {
	            DeliveryPerson deliveryPerson = deliveryPersonDao.findById(deliveryPersonId)
	                    .orElseThrow(() -> new RuntimeException("Delivery Person not found"));

	            List<Orders> ordersList = ordersDao.findByAssignToDeliveryPersonIdAndStatus(deliveryPerson, status);
	            if (ordersList != null) {
	                deliveryPersonHomePageDtoList = ordersList.stream()
	                        .map(DaoToEntityConverter::toDeliveryPersonDTO)
	                        .collect(Collectors.toList());
	            }
	        } catch (RuntimeException e) {
	            logger.error("Error fetching orders for delivery person ID {}: {}", deliveryPersonId, e.getMessage());
	        } catch (Exception e) {
	            logger.error("Unexpected error: {}", e.getMessage());
	        }
	        return deliveryPersonHomePageDtoList;
	    }



}
