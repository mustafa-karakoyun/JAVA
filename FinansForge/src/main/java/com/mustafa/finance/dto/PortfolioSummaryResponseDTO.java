package com.mustafa.finance.dto;

public class PortfolioSummaryResponseDTO {
    private double totalPortfolioValue;      // Portföyün anlık toplam piyasa değeri (Hisse Adedi * Anlık Fiyat)
    private double totalCost;                // Portföyün toplam maliyeti (Hisse Adedi * Ortalama Maliyet)
    private double netProfitLoss;            // Net Kâr / Zarar Tutarı (Toplam Değer - Toplam Maliyet)
    private double totalProfitLossPercentage; // Toplam Kâr / Zarar Yüzdesi

    public PortfolioSummaryResponseDTO() {
    }

    public PortfolioSummaryResponseDTO(double totalPortfolioValue, double totalCost) {
        this.totalPortfolioValue = totalPortfolioValue;
        this.totalCost = totalCost;
        this.netProfitLoss = totalPortfolioValue - totalCost;
        // Sıfıra bölünme hatasını (bölme sıfır) engellemek için kontrol
        this.totalProfitLossPercentage = totalCost > 0 ? (this.netProfitLoss / totalCost) * 100.0 : 0.0;
    }

    // Getter ve Setter Metotları
    public double getTotalPortfolioValue() { return totalPortfolioValue; }
    public void setTotalPortfolioValue(double totalPortfolioValue) { this.totalPortfolioValue = totalPortfolioValue; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public double getNetProfitLoss() { return netProfitLoss; }
    public void setNetProfitLoss(double netProfitLoss) { this.netProfitLoss = netProfitLoss; }

    public double getTotalProfitLossPercentage() { return totalProfitLossPercentage; }
    public void setTotalProfitLossPercentage(double totalProfitLossPercentage) { this.totalProfitLossPercentage = totalProfitLossPercentage; }
}