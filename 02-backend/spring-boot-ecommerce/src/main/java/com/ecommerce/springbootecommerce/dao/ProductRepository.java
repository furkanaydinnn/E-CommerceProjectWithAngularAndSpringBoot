package com.ecommerce.springbootecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.springbootecommerce.entity.Product;

@Repository
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	
	
	Page<Product> findByNameContainingIgnoreCase(@RequestParam("name") String name, Pageable pageable);

}
