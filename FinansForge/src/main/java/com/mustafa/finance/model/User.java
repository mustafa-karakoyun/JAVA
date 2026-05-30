package com.mustafa.finance.model;
import com.mustafa.finance.model.PortfolioAsset;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	
			@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		private List<WatchList> watchlists;

		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		private List<PortfolioAsset> assets;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Long id,String name,String email) {
		this.id=id;
		this.name=name;
		this.email=email;
		
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<WatchList> getWatchlists() {
		return watchlists;
	}
	public void setWatchlists(List<WatchList> watchlists) {
		this.watchlists = watchlists;
	}
	public List<PortfolioAsset> getAssets() {
		return assets;
	}
	public void setAssets(List<PortfolioAsset> assets) {
		this.assets = assets;
	}
	
	
	
}
