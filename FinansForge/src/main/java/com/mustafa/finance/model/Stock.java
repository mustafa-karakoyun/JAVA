package com.mustafa.finance.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="stocks")
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ticker;
	private String name;
	private double current_price;
	private LocalDateTime last_update;
	
	// Boş Parametreli Yapıcı Metot
	public Stock() {
	}

	// Tüm Parametreli Yapıcı Metot
	public Stock(Long id, String ticker, String name, double current_price, LocalDateTime last_update) {
		this.id = id;
		this.ticker = ticker;
		this.name = name;
		this.current_price = current_price;
		this.last_update = last_update;
	}

	// Getter ve Setter Metotları
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}

	public LocalDateTime getLast_update() {
		return last_update;
	}

	public void setLast_update(LocalDateTime last_update) {
		this.last_update = last_update;
	}
}
