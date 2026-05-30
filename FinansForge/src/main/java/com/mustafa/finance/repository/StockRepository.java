package com.mustafa.finance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mustafa.finance.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
	Optional<Stock> findByTickerIgnoreCase(String ticker);
}
