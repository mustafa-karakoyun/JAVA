package com.mustafa.finance.dto;

import java.util.List;

public class WatchListResponseDTO {
    private Long id;
    private String name;
    private List<StockDTO> stocks; // Listelerin içindeki hisseleri tutan mini gömülü DTO listesi

    public WatchListResponseDTO() {
    }

    // Getter ve Setter Metotları
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<StockDTO> getStocks() { return stocks; }
    public void setStocks(List<StockDTO> stocks) { this.stocks = stocks; }

    // İç içe döngüye girmemek için sadece hisse bilgilerini taşıyan statik bir inner class DTO
    public static class StockDTO {
        private Long id;
        private String ticker;
        private String name;
        private double currentPrice;

        public StockDTO() {}

        public StockDTO(Long id, String ticker, String name, double currentPrice) {
            this.id = id;
            this.ticker = ticker;
            this.name = name;
            this.currentPrice = currentPrice;
        }

        // Getter ve Setter Metotları
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTicker() { return ticker; }
        public void setTicker(String ticker) { this.ticker = ticker; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getCurrentPrice() { return currentPrice; }
        public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }
    }
}
