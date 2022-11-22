package com.kgisl.auth.contoller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.auth.dto.ProductDto;
import com.kgisl.auth.entity.Product;
import com.kgisl.auth.service.ProductService;
 
@RestController
@RequestMapping("/products")
public class ProductController {
 
    @Autowired 
    ProductService productService;
     
    @PostMapping("/")
    public ResponseEntity<Product> create(@RequestBody @Validated Product p) {
        Product savedProduct = productService.create(p); //create(p)
        URI productURI = URI.create("/products/" + savedProduct.getId());
        return ResponseEntity.created(productURI).body(savedProduct);
    }
     
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
    	List<ProductDto> l = productService.getAll();
        return new ResponseEntity<>(l,HttpStatus.OK);
       
    }
    
    @GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable Integer id) {
		 Optional<Product> l = productService.getById(id);
		return new ResponseEntity<>(l, HttpStatus.FOUND);
	}
    
    @DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteProductrById(@PathVariable Integer id) {
	    productService.delete(id);
		return new ResponseEntity<>(HttpStatus.GONE);
	}
    @PutMapping(path = "/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product p) {
	    Product l = productService.update(id, p);
		return new ResponseEntity<>(l, HttpStatus.ACCEPTED);

	}
    
}