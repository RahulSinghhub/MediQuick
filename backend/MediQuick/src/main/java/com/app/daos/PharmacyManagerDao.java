package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.PharmacyManager;

public interface PharmacyManagerDao extends JpaRepository<PharmacyManager, Integer> {
        
	PharmacyManager findByEmail(String email);
	
	
}
