package com.mustafa.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //JPA oldugunu belirttir.
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // id otomatik artar
	private Long id;
	private String name;
	private double price;
	
	public Product() {} // Zorunludur. Yapıcı metot jpa nın nesne yaratması için.
	public Product(String name,double price) {
		this.name=name;
		this.price=price;
		
	}
	// getter seter metotları
	
	public Long getId() {
		return id;
		}
	public void setId(Long id) {
		this.id=id;
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
