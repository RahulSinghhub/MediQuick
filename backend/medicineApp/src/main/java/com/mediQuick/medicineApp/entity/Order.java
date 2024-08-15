package com.mediQuick.medicineApp.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "orders") // Optional: specify the table name, if different from the class name
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    private double totalAmount;

    private String razorPayOrderId;

    // One user can place many orders
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    // Many orders can be assigned to one delivery boy
    @OneToOne
    @JoinColumn(name = "delivery_boy_id")
    private DeliveryBoy deliveryBoy;

    // One order can have one delivery address
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_add_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetails> orderDetails = new HashSet<>();

    
}
