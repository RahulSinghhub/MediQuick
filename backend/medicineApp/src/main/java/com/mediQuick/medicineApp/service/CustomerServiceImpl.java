package com.mediQuick.medicineApp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediQuick.medicineApp.dto.CustomerDto;
import com.mediQuick.medicineApp.entity.User;
import com.mediQuick.medicineApp.repositories.UserRepositories;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private UserRepositories userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CustomerDto> getAllcust() {
		return userRepo.findAll()
		.stream()
		.map(cust ->
		modelMapper.map(cust, CustomerDto.class))
		.collect(Collectors.toList());
	}

	@Override
	public CustomerDto getcust(Long custId) {
		 Optional<User> custEntity = userRepo.findById(custId);

	        // Handling the case where the user is not found
	        if (custEntity.isPresent()) {
	            return modelMapper.map(custEntity.get(), CustomerDto.class);
	        } else {
	            // Throw an appropriate exception or handle the case when the user is not found
	            throw new RuntimeException("Customer with ID " + custId + " not found");
	        }
	    }
	
	
	@Override
    public CustomerDto addcust(CustomerDto custDto) {
        User custEntity = modelMapper.map(custDto, User.class);
        User savedCust = userRepo.save(custEntity);
        return modelMapper.map(savedCust, CustomerDto.class);
    }

    @Override
    public CustomerDto updatecust(Long custId, CustomerDto custDto) {
        Optional<User> existingCust = userRepo.findById(custId);

        if (existingCust.isPresent()) {
            User custEntity = existingCust.get();
            // Update the entity with the DTO data
            modelMapper.map(custDto, custEntity);
            User updatedCust = userRepo.save(custEntity);
            return modelMapper.map(updatedCust, CustomerDto.class);
        } else {
            throw new RuntimeException("Customer with ID " + custId + " not found");
        }
    }

    @Override
    public CustomerDto delcust(Long delId) {
        Optional<User> custEntity = userRepo.findById(delId);

        if (custEntity.isPresent()) {
            userRepo.delete(custEntity.get());
            return modelMapper.map(custEntity.get(), CustomerDto.class);
        } else {
            throw new RuntimeException("Customer with ID " + delId + " not found");
        }
    }
}
