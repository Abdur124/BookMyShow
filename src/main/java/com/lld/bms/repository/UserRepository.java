package com.lld.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
