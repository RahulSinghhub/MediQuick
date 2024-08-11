package com.mediQuick.medicineApp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediQuick.medicineApp.dto.ShopOwnerDto;
import com.mediQuick.medicineApp.entity.User;
import com.mediQuick.medicineApp.repositories.UserRepositories;

@Service
@Transactional
public class ShopOwnerServiceImpl implements ShopOwnerService {

    @Autowired
    private UserRepositories userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ShopOwnerDto> getAllVendors() {
        return userRepo.findAll()
                .stream()
                .map(user -> modelMapper.map(user, ShopOwnerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShopOwnerDto getVendor(Long shopId) {
        Optional<User> shopOwner = userRepo.findById(shopId);

        if (shopOwner.isPresent()) {
            return modelMapper.map(shopOwner.get(), ShopOwnerDto.class);
        } else {
            throw new RuntimeException("Vendor with ID " + shopId + " not found");
        }
    }

    @Override
    public ShopOwnerDto addVendor(ShopOwnerDto shopDto) {
        User shopEntity = modelMapper.map(shopDto, User.class);
        User savedShop = userRepo.save(shopEntity);
        return modelMapper.map(savedShop, ShopOwnerDto.class);
    }

    @Override
    public ShopOwnerDto updateVendor(Long shopId, ShopOwnerDto shopDto) {
        Optional<User> existingShop = userRepo.findById(shopId);

        if (existingShop.isPresent()) {
            User shopEntity = existingShop.get();
            modelMapper.map(shopDto, shopEntity);
            User updatedShop = userRepo.save(shopEntity);
            return modelMapper.map(updatedShop, ShopOwnerDto.class);
        } else {
            throw new RuntimeException("Vendor with ID " + shopId + " not found");
        }
    }

    @Override
    public ShopOwnerDto delVendor(Long delId) {
        Optional<User> shopOwner = userRepo.findById(delId);

        if (shopOwner.isPresent()) {
            userRepo.delete(shopOwner.get());
            return modelMapper.map(shopOwner.get(), ShopOwnerDto.class);
        } else {
            throw new RuntimeException("Vendor with ID " + delId + " not found");
        }
    }
}
