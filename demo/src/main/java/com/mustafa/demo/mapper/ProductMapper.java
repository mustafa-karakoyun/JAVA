package com.mustafa.demo.mapper;

import org.springframework.stereotype.Component;

import com.mustafa.demo.dto.ProductRequestDTO;
import com.mustafa.demo.dto.ProductResponseDTO;
import com.mustafa.demo.model.Product;

@Component
public class ProductMapper {

	public Product toEntity(ProductRequestDTO dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		return product;
	}
	public ProductResponseDTO toResponseDTO(Product product) {
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setId(product.getId());
		productResponseDTO.setName(product.getName());
		productResponseDTO.setPrice(product.getPrice());
		return productResponseDTO;
	}
}
	
