package com.mustafa.finance.dto;

import jakarta.validation.constraints.*;

public class PortfolioAssetRequestDTO {

    @NotNull(message = "Kullanıcı ID boş olamaz.")
    private Long userId;

    @NotBlank(message = "Hisse kodu (ticker) boş olamaz.")
    private String ticker;

    @Min(value = 1, message = "En az 1 adet hisse eklemelisiniz.")
    private int quantity;

    @Positive(message = "Alış fiyatı 0'dan büyük olmalıdır.")
    private double price;

    public PortfolioAssetRequestDTO() {
    }

    // Getter ve Setter Metotları
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
