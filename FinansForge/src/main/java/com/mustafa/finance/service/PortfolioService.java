package com.mustafa.finance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mustafa.finance.model.PortfolioAsset;
import com.mustafa.finance.dto.PortfolioSummaryResponseDTO;
import com.mustafa.finance.model.*;
import com.mustafa.finance.repository.PortfolioAssetRepository;
import com.mustafa.finance.repository.StockRepository;
import com.mustafa.finance.repository.UserRepository;

@Service
public class PortfolioService {
	private final StockRepository stockRepository;
	private final UserRepository userRepository;
	private final PortfolioAssetRepository portfolioAssetRepository;
	private final StockFetchService stockFetchService;
	
	public PortfolioService(StockRepository stockRepository,
											UserRepository userRepository,
								            PortfolioAssetRepository portfolioAssetRepository, 
								            StockFetchService stockFetchService) {
		this.stockRepository=stockRepository;
		this.userRepository = userRepository;
		this.portfolioAssetRepository = portfolioAssetRepository;
		this.stockFetchService = stockFetchService;
	}
	
	public PortfolioAsset addAssetToPortfolio(Long userId, String ticker, int quantity, double price) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı"));
		
		Optional<Stock> stockOptional = stockRepository.findByTickerIgnoreCase(ticker);
		Stock stock;
		if(stockOptional.isPresent()) {
			stock=stockOptional.get();
		}else {
			stock=stockFetchService.fetchStockData(ticker);
			stock=stockRepository.save(stock);
		}
		
		//Kullanıcı daha önce almış mı
		Optional<PortfolioAsset> existingAssetOptional = portfolioAssetRepository.
									findByUserIdAndStockId(userId, stock.getId());
		
		PortfolioAsset asset;
		// eger yoksa oluşturuyorum.
		if(existingAssetOptional.isEmpty()) {
			asset = new PortfolioAsset();
			asset.setUser(user);
			asset.setStock(stock);
			asset.setQuantity(quantity);
			asset.setAvg_cost(price);
		} else {
            // Kullanıcı bu hisseden daha önce almış, mevcut varlığı çekiyoruz
            asset = existingAssetOptional.get();
            
            int oldQuantity = asset.getQuantity();
            double oldAvg = asset.getAvg_cost();
            
            // 1. Adım: Yeni toplam adedi hesaplıyoruz
            int newQuantity = oldQuantity + quantity;
            
            // 2. Adım: Doğru Ağırlıklı Ortalama Maliyet formülünü işletiyoruz
            // (Eski Toplam Tutar + Yeni Alış Tutarı) / Yeni Toplam Adet
            double newAvg = ((oldAvg * oldQuantity) + (quantity * price)) / newQuantity;
            
            // 3. Adım: Hesaplanan doğru değerleri varlık nesnesine set ediyoruz
            asset.setAvg_cost(newAvg);
            asset.setQuantity(newQuantity);
        } 
		
		return portfolioAssetRepository.save(asset);
	}
	
	public List<PortfolioAsset> getUserPortfolio(Long id){
		return portfolioAssetRepository.findByUserId(id);
	}
	
	/**
	 * Kullanıcının portföyündeki tüm varlıkları analiz ederek genel dashboard özetini hesaplar.
	 */
	public PortfolioSummaryResponseDTO getPortfolioSummary(Long userId) {
	    // Kullanıcının tüm portföy varlıklarını çekiyoruz
	    List<PortfolioAsset> assets = getUserPortfolio(userId);

	    double totalPortfolioValue = 0.0;
	    double totalCost = 0.0;

	    // Her bir varlık için toplam değerleri ve maliyetleri kümülatif olarak topluyoruz
	    for (PortfolioAsset asset : assets) {
	        double currentPrice = asset.getStock().getCurrent_price();
	        double avgCost = asset.getAvg_cost();
	        int quantity = asset.getQuantity();

	        totalPortfolioValue += (quantity * currentPrice);
	        totalCost += (quantity * avgCost);
	    }

	    // Hesaplanan kümülatif verileri DTO yapısına sarmalayıp dönüyoruz
	    return new PortfolioSummaryResponseDTO(totalPortfolioValue, totalCost);
	}
	/**
	 * Kullanıcının portföyünden belirlenen adette hisse satışı gerçekleştirir.
	 * Satış işlemi ortalama maliyeti değiştirmez, sadece kalan adet bilgisini azaltır.
	 */
	public PortfolioAsset sellAssetFromPortfolio(Long userId, String ticker, int quantityToSell) {
	    // 1. Kullanıcının varlığını kontrol ediyoruz
	    userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

	    // 2. Hisseyi sistemde arıyoruz
	    Stock stock = stockRepository.findByTickerIgnoreCase(ticker)
	            .orElseThrow(() -> new RuntimeException("Sistemde bu hisse koduna ait veri bulunamadı: " + ticker));

	    // 3. Kullanıcının portföyünde bu hisseden gerçekten var mı?
	    PortfolioAsset asset = portfolioAssetRepository.findByUserIdAndStockId(userId, stock.getId())
	            .orElseThrow(() -> new RuntimeException("Portföyünüzde bu hisseden bulunmamaktadır."));

	    int currentQuantity = asset.getQuantity();

	    // 4. MANTIK KONTROLÜ: Sahip olunandan daha fazla lot satılamaz
	    if (quantityToSell > currentQuantity) {
	        throw new RuntimeException("Yetersiz hisse adedi! Elinizdeki miktar: " + currentQuantity + ", Satılmak istenen: " + quantityToSell);
	    }

	    // 5. Eğer elindeki hisselerin TAMAMI satılıyorsa, o satırı veritabanından tamamen siliyoruz
	    if (quantityToSell == currentQuantity) {
	        portfolioAssetRepository.delete(asset);
	        return null; // Satır silindiği için geriye boş (null) dönüyoruz
	    }

	    // 6. Eğer KISMİ satış yapılıyorsa, maliyete dokunmadan sadece adedi azaltıp kaydediyoruz
	    int newQuantity = currentQuantity - quantityToSell;
	    asset.setQuantity(newQuantity);

	    return portfolioAssetRepository.save(asset);
	}
	
	
}
