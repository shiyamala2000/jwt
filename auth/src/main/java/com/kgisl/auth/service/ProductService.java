package com.kgisl.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kgisl.auth.dto.ProductDto;
import com.kgisl.auth.entity.Product;

@Service
public interface ProductService {

	public List<ProductDto> getAll();

	public Optional<Product> getById(Integer id);

	public void delete(Integer id);

	public Product create(Product p);

	public Product update(Integer id, Product p);
}