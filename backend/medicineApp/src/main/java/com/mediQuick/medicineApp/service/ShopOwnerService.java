package com.mediQuick.medicineApp.service;

import java.util.List;

import com.mediQuick.medicineApp.dto.ShopOwnerDto;

public interface ShopOwnerService {

	List<ShopOwnerDto> getAllVendors();

	ShopOwnerDto getVendor(Long shopId);

	ShopOwnerDto addVendor(ShopOwnerDto shop);

	ShopOwnerDto updateVendor(Long shopId, ShopOwnerDto shop);

	ShopOwnerDto delVendor(Long delId);

}
