package com.mustafa.finance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "portfolio_assets")
public class PortfolioAsset {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int quantity;
	private double avg_cost;
	
	@ManyToOne
	@JoinColumn(name="stock_id", nullable = false)
	private Stock stock;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	// Boş Parametreli Yapıcı Metot
	public PortfolioAsset() {
	}

	// Tüm Parametreli Yapıcı Metot
	public PortfolioAsset(Long id, int quantity, double avg_cost, Stock stock, User user) {
		this.id = id;
		this.quantity = quantity;
		this.avg_cost = avg_cost;
		this.stock = stock;
		this.user = user;
	}

	// Getter ve Setter Metotları
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAvg_cost() {
		return avg_cost;
	}

	public void setAvg_cost(double avg_cost) {
		this.avg_cost = avg_cost;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
