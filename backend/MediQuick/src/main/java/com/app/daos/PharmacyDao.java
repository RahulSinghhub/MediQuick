package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Pharmacy;

public interface PharmacyDao extends JpaRepository<Pharmacy, Integer>{

}
