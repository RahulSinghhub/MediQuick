package com.mediQuick.medicineApp.service;

import java.util.List;

import com.mediQuick.medicineApp.dto.CustomerDto;

public interface CustomerService {

	List<CustomerDto> getAllcust();

	CustomerDto getcust(Long custId);

	CustomerDto addcust(CustomerDto med);

	CustomerDto updatecust(Long custId, CustomerDto med);

	CustomerDto delcust(Long delId);
	
}
