package com.mediQuick.medicineApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediQuick.medicineApp.entity.CartItem;


@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

}
