package com.mustafa.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mustafa.demo.dto.ProductRequestDTO;
import com.mustafa.demo.dto.ProductResponseDTO;
import com.mustafa.demo.service.ProductService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Profesyonel Constructor (Yapıcı Metot) Bağımlılık Enjeksiyonu
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. Tüm ürünleri listeleme (GET /api/products)
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 2. ID değerine göre tek bir ürünü getirme (GET /api/products/{id})
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = productService.getProductById(id);
        return ResponseEntity.ok(productResponseDTO);
    }
    
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO product) {
        ProductResponseDTO savedProduct = productService.save(product);
        return ResponseEntity.status(201).body(savedProduct);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, 
    		@Valid @RequestBody ProductRequestDTO dto){
    	ProductResponseDTO existingProduct = productService.getProductById(id);
        
        if (existingProduct != null) {
        	ProductResponseDTO updatedProduct = productService.save(dto);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    	if(productService.getProductById(id)!=null) {
    		productService.delete(id);
    		return ResponseEntity.noContent().build();
    	}else {
            return ResponseEntity.notFound().build(); // Ürün yoksa HTTP 404
        }
    	
    }
 

}