package com.mediQuick.medicineApp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediQuick.medicineApp.dto.CartAddDto;
import com.mediQuick.medicineApp.dto.CustomerDto;
import com.mediQuick.medicineApp.entity.CartItem;
import com.mediQuick.medicineApp.entity.User;
import com.mediQuick.medicineApp.repositories.CartRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepo;
	
	private ModelMapper modelMapper;
	
	@Override
	public CartAddDto getAllcart() {
		List<CartItem> allCart = cartRepo.findAll();
		CartAddDto cartfind =modelMapper.map(allCart, CartAddDto.class);
		return cartfind;
	}

	@Override
	public CartAddDto getcust(Long cartId) {
			 Optional<CartItem> custEntity = cartRepo.findById(cartId);
		        // Handling the case where the user is not found
		        if (custEntity.isPresent()) {
		            return modelMapper.map(custEntity.get(), CartAddDto.class);
		        } else {
		            // Throw an appropriate exception or handle the case when the user is not found
		            throw new RuntimeException("Customer with ID " + cartId + " not found");
		        }
		    }
		
		


	@Override
	public CartAddDto addcust(CartAddDto cart) {
		CartItem custEntity = modelMapper.map(cart, CartItem.class);
		CartItem savedCust = cartRepo.save(custEntity);
        return modelMapper.map(savedCust, CartAddDto.class);
		
	}

	@Override
	public CartAddDto updatecust(Long cartId, CartAddDto cart) {
		 Optional<CartItem> existingCust = cartRepo.findById(cartId);

	        if (existingCust.isPresent()) {
	        	CartItem custEntity = existingCust.get();
	            // Update the entity with the DTO data
	            modelMapper.map(cart, custEntity);
	            CartItem updatedCust = cartRepo.save(custEntity);
	            return modelMapper.map(updatedCust, CartAddDto.class);
	        } else {
	            throw new RuntimeException("Customer with ID " + cartId + " not found");
	        }
	}

	@Override
	public CartAddDto delcust(Long delId) {
		 Optional<CartItem> custEntity = cartRepo.findById(delId);

	        if (custEntity.isPresent()) {
	        	cartRepo.delete(custEntity.get());
	            return modelMapper.map(custEntity.get(), CartAddDto.class);
	        } else {
	            throw new RuntimeException("Customer with ID " + delId + " not found");
	        }
	    }
	
}
