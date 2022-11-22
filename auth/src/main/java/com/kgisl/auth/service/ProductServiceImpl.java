package com.kgisl.auth.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.auth.dto.ProductDto;
import com.kgisl.auth.entity.Product;
import com.kgisl.auth.repository.ProductRepository;

@Service
public class ProductServiceImpl  implements ProductService{
	@Autowired
	private ProductRepository productRepository;

	private ProductDto changeDto(Product p) {
		ProductDto productDto = new ProductDto();
		
		productDto.setId(p.getId());
		productDto.setName(p.getName());
		productDto.setPrice(p.getPrice());
		return productDto;
	}
	
	
	@Override
    public List<ProductDto> getAll() {
		return ((List<Product>) productRepository.findAll()).stream().map(this::changeDto).collect(Collectors.toList());
	}

	@Override
	public Optional<Product> getById(Integer id) {
		Optional<Product> l = productRepository.findById(id).stream().findAny();
		return l;
	}

	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product create(Product p) {
		return productRepository.save(p);
	}

	@Override
	public Product update(Integer id, Product p) {
		return productRepository.save(p);
	}
	

}
