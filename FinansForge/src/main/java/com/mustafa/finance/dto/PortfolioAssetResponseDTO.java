package com.mustafa.finance.dto;

public class PortfolioAssetResponseDTO {
    private Long id;
    private String ticker;
    private String stockName;
    private int quantity;
    private double avgCost;
    private double currentPrice;
    private double totalValue;
    private double profitLossPercentage;

    public PortfolioAssetResponseDTO() {
    }

    // Parametreli yapıcı metot (Servis veya Mapper katmanında kolay dönüşüm için)
    public PortfolioAssetResponseDTO(Long id, String ticker, String stockName, int quantity, double avgCost, double currentPrice) {
        this.id = id;
        this.ticker = ticker;
        this.stockName = stockName;
        this.quantity = quantity;
        this.avgCost = avgCost;
        this.currentPrice = currentPrice;
        this.totalValue = quantity * currentPrice;
        // Kar-Zarar yüzdesi dinamik hesaplanıyor
        this.profitLossPercentage = avgCost > 0 ? ((currentPrice - avgCost) / avgCost) * 100.0 : 0.0;
    }

    // Getter ve Setter Metotları
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }

    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getAvgCost() { return avgCost; }
    public void setAvgCost(double avgCost) { this.avgCost = avgCost; }

    public double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    public double getTotalValue() { return totalValue; }
    public void setTotalValue(double totalValue) { this.totalValue = totalValue; }

    public double getProfitLossPercentage() { return profitLossPercentage; }
    public void setProfitLossPercentage(double profitLossPercentage) { this.profitLossPercentage = profitLossPercentage; }
}