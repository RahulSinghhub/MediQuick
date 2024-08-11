package com.mediQuick.medicineApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediQuick.medicineApp.entity.User;

public interface UserRepositories extends JpaRepository<User, Long> {

}
