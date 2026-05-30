package com.mustafa.finance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WatchListRequestDTO {

    @NotBlank(message = "Takip listesi adı boş bırakılamaz.")
    private String name;

    @NotNull(message = "Listenin bağlanacağı kullanıcı ID belirtilmelidir.")
    private Long userId;

    public WatchListRequestDTO() {
    }

    // Getter ve Setter Metotları
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}