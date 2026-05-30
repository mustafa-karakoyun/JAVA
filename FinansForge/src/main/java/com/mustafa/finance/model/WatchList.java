package com.mustafa.finance.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="watchList")
public class WatchList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user; 

	@ManyToMany
	@JoinTable(
		name = "watchlist_stocks", // Veritabanında otomatik oluşacak ara tablonun adı
		joinColumns = @JoinColumn(name = "watchlist_id"), // WatchList tablosunu bağlayan yabancı anahtar
		inverseJoinColumns = @JoinColumn(name = "stock_id") // Stock tablosunu bağlayan yabancı anahtar
	)
	private List<Stock> stocks;
	
	public WatchList() {
	}

	public WatchList(Long id, String name, User user) {
		this.id = id;
		this.name = name;
		this.user = user;
	}

	// Getter ve Setter Metotları
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
}
