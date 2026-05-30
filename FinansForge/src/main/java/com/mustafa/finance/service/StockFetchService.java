package com.mustafa.finance.service;

import com.mustafa.finance.model.Stock;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StockFetchService {

    private final WebClient webClient;

    // WebClient.Builder ile Yahoo Finance kök dizinini sisteme bağlıyoruz
    public StockFetchService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://query1.finance.yahoo.com").build();
    }

    /**
     * Verilen ticker koduna (Örn: THYAO.IS) göre Yahoo Finance API'sine istek atar,
     * gelen JSON verisini çözümler ve temiz bir Stock nesnesi üretir.
     */
    public Stock fetchStockData(String ticker) {
        try {
            // API'ye GET isteği fırlatılıyor ve gelen yanıt esnek Map yapısına dönüştürülüyor
            Map response = this.webClient.get()
                    .uri("/v8/finance/chart/{ticker}", ticker)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block(); // Akışı iş mantığına senkronize etmek için block() kullanıyoruz

            if (response == null || !response.containsKey("chart")) {
                return null;
            }

            // JSON basamaklarında adım adım aşağı iniyoruz: chart -> result (List) -> [0] -> meta
            Map chart = (Map) response.get("chart");
            List resultList = (List) chart.get("result");
            
            if (resultList == null || resultList.isEmpty()) {
                return null;
            }
            
            Map firstResult = (Map) resultList.get(0);
            Map meta = (Map) firstResult.get("meta");

            if (meta == null) {
                return null;
            }

            // Şirketin uzun unvanını alıyoruz, eğer boşsa kısa unvanına bakıyoruz
            String longName = (String) meta.get("longName");
            if (longName == null || longName.isEmpty()) {
                longName = (String) meta.get("shortName");
            }
            
            // Fiyat bilgisini güvenli bir şekilde double tipine dönüştürüyoruz
            Object priceObj = meta.get("regularMarketPrice");
            double price = 0.0;
            if (priceObj instanceof Integer) {
                price = ((Integer) priceObj).doubleValue();
            } else if (priceObj instanceof Double) {
                price = (Double) priceObj;
            }

            // Ayıkladığımız verilerle yepyeni bir Stock entity nesnesi ayağa kaldırıyoruz
            Stock stock = new Stock();
            stock.setTicker(ticker.toUpperCase());
            stock.setName(longName != null ? longName : ticker.toUpperCase());
            stock.setCurrent_price(price);
            stock.setLast_update(LocalDateTime.now()); // Zaman damgası vuruluyor

            return stock;

        } catch (Exception e) {
            // API bağlantı hatası veya JSON çözümleme hatası durumunda log basıp null dönüyoruz
            System.err.println("Yahoo Finance veri çekme hatası (" + ticker + "): " + e.getMessage());
            return null;
        }
    }
}