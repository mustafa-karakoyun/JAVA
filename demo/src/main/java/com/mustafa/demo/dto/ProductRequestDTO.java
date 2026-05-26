package com.mustafa.demo.dto;

import jakarta.validation.constraints.*;

public class ProductRequestDTO {
	@NotBlank(message="Ürün adı boş olamaz.")
	private String name;
	
	@Positive(message="Fiyat 0 dan büyük olmalı.")
	private double price;
	
	public ProductRequestDTO() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price=price;
	}
	
}
