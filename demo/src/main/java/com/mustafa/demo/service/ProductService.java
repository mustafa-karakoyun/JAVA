package com.mustafa.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.mustafa.demo.dto.ProductRequestDTO;
import com.mustafa.demo.dto.ProductResponseDTO;
import com.mustafa.demo.mapper.ProductMapper;
import com.mustafa.demo.model.Product;
import com.mustafa.demo.repository.ProductRepository;

@Service // Bu bir servis sınıfıdır
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	// Constructor Injection (DI)
	public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}
	
	public List<ProductResponseDTO> getAllProducts() {
		return productRepository.findAll()
				.stream()
				.map(productMapper::toResponseDTO)
				.toList();
	}

	public ProductResponseDTO getProductById(Long id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product != null) {
			return productMapper.toResponseDTO(product);
		}
		return null;
	}

	public ProductResponseDTO save(ProductRequestDTO dto) {
		Product product = productMapper.toEntity(dto);
		Product savedProduct = productRepository.save(product);
		return productMapper.toResponseDTO(savedProduct);
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}