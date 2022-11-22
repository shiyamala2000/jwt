package com.kgisl.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kgisl.auth.entity.Product;
 
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
 
}
