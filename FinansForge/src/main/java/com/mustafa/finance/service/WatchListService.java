package com.mustafa.finance.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mustafa.finance.model.Stock;
import com.mustafa.finance.model.WatchList;
import com.mustafa.finance.repository.StockRepository;
import com.mustafa.finance.repository.WatchListRepository;

@Service
public class WatchListService {
	private final WatchListRepository watchListRepository;
	private final StockRepository stockRepository;
	private final StockFetchService stockFetchService;

	// Constructor Injection (Tavsiye edilen güvenli yapı)
	public WatchListService(WatchListRepository watchListRepository, 
	                        StockRepository stockRepository,
	                        StockFetchService stockFetchService) {
		this.watchListRepository = watchListRepository;
		this.stockRepository = stockRepository;
		this.stockFetchService = stockFetchService;
	}
	
	 public WatchList addStockToWatchList(Long watchlistId, String ticker) {

		 WatchList watchList = watchListRepository.findById(watchlistId)
					.orElseThrow(() -> new RuntimeException("Takip listesi bulunamadı! ID: " + watchlistId));
		 
		 Optional<Stock> stockOptional = stockRepository.findByTickerIgnoreCase(ticker);
		 Stock stock;
		 
		 if(stockOptional.isPresent()) {
			 stock = stockOptional.get();
		 }else {
			 stock= stockFetchService.fetchStockData(ticker);
			 stock = stockRepository.save(stock);
			 
		 }
		 
		 boolean isAlreadyExist = watchList.getStocks().stream()
				 .anyMatch(s->s.getTicker().equalsIgnoreCase(ticker));
		 
		 if (isAlreadyExist) {
			 throw new RuntimeException("Bu hisse zaten mevcut");
		 }
		 
		 
		 watchList.getStocks().add(stock);
		 return watchListRepository.save(watchList);
		 
	 }
	 
	 public void removeStockFromWatchList(Long watchlistId,String ticker) {
		 WatchList watchList = watchListRepository.findById(watchlistId)
				 			.orElseThrow(() -> new RuntimeException("Takip listesi bulunamadı! ID: " + watchlistId));
		 
		 Optional<Stock> stockOptional = stockRepository.findByTickerIgnoreCase(ticker);
		 if(stockOptional.isPresent()) {
			 Stock stock = stockOptional.get();
			 watchList.getStocks().remove(stock);
			 watchListRepository.save(watchList);
		    } else {
		        throw new RuntimeException("Sistemde böyle bir hisse bulunamadı: " + ticker);
		    }
	 }

}
