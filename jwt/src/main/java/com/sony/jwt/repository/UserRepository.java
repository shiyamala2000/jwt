package com.sony.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sony.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
