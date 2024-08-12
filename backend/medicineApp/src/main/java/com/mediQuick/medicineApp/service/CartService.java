package com.mediQuick.medicineApp.service;

import com.mediQuick.medicineApp.dto.CartAddDto;
import com.mediQuick.medicineApp.dto.CustomerDto;

public interface CartService {

	CartAddDto getAllcart();

	CartAddDto getcust(Long cartId);

	CartAddDto addcust(CartAddDto cart);

	CartAddDto updatecust(Long cartId, CartAddDto cart);

	CartAddDto delcust(Long delId);

	

	
}
