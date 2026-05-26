package com.mustafa.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mustafa.demo.model.Product;



public interface ProductRepository extends JpaRepository<Product, Long> {
	
    Optional<Product> findByNameIgnoreCase(String name);
}