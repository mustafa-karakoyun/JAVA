package com.mustafa.finance.controller;

import com.mustafa.finance.dto.PortfolioAssetRequestDTO;
import com.mustafa.finance.dto.PortfolioAssetResponseDTO;
import com.mustafa.finance.model.PortfolioAsset;
import com.mustafa.finance.service.PortfolioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "*") // Ön yüzün (React vb.) API'ye erişebilmesi için CORS izni
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    /**
     * Portföye yeni bir hisse varlığı ekler veya mevcut varlığın maliyetini günceller.
     * @Valid anotasyonu DTO üzerindeki kuralları tetikler.
     */
    @PostMapping("/add")
    public ResponseEntity<PortfolioAssetResponseDTO> addAsset(@Valid @RequestBody PortfolioAssetRequestDTO requestDTO) {
        PortfolioAsset asset = portfolioService.addAssetToPortfolio(
                requestDTO.getUserId(),
                requestDTO.getTicker(),
                requestDTO.getQuantity(),
                requestDTO.getPrice()
        );

        // Entity nesnesini temiz bir Response DTO'ya dönüştürüyoruz
        PortfolioAssetResponseDTO responseDTO = new PortfolioAssetResponseDTO(
                asset.getId(),
                asset.getStock().getTicker(),
                asset.getStock().getName(),
                asset.getQuantity(),
                asset.getAvg_cost(),
                asset.getStock().getCurrent_price()
        );

        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Belirli bir kullanıcının portföyündeki tüm hisse varlıklarını DTO formatında listeler.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PortfolioAssetResponseDTO>> getUserPortfolio(@PathVariable Long userId) {
        List<PortfolioAsset> assets = portfolioService.getUserPortfolio(userId);

        // Tüm entity listesini stream kullanarak Response DTO listesine map'liyoruz
        List<PortfolioAssetResponseDTO> responseDTOs = assets.stream().map(asset -> 
            new PortfolioAssetResponseDTO(
                    asset.getId(),
                    asset.getStock().getTicker(),
                    asset.getStock().getName(),
                    asset.getQuantity(),
                    asset.getAvg_cost(),
                    asset.getStock().getCurrent_price()
            )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }
    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<com.mustafa.finance.dto.PortfolioSummaryResponseDTO> getPortfolioSummary(@PathVariable Long userId) {
        com.mustafa.finance.dto.PortfolioSummaryResponseDTO summary = portfolioService.getPortfolioSummary(userId);
        return ResponseEntity.ok(summary);
    }
    /**
     * Kullanıcının portföyünden belirlenen miktarda hisse satışı yapar.
     * Eğer tüm hisseler satılırsa HTTP 204 (No Content) döner, kısmi satışta güncel varlık DTO'sunu döner.
     */
    @PostMapping("/sell")
    public ResponseEntity<?> sellAsset(
            @RequestParam Long userId,
            @RequestParam String ticker,
            @RequestParam int quantity) {
        
        PortfolioAsset updatedAsset = portfolioService.sellAssetFromPortfolio(userId, ticker, quantity);

        // Eğer varlık tamamen tükendiyse ve servis null döndüyse, silindiğini belirten 204 kodu fırlatıyoruz
        if (updatedAsset == null) {
            return ResponseEntity.noContent().build();
        }

        // Kısmi satış yapıldıysa, kalan miktarla birlikte güncel Response DTO'yu dönüyoruz
        PortfolioAssetResponseDTO responseDTO = new PortfolioAssetResponseDTO(
                updatedAsset.getId(),
                updatedAsset.getStock().getTicker(),
                updatedAsset.getStock().getName(),
                updatedAsset.getQuantity(),
                updatedAsset.getAvg_cost(),
                updatedAsset.getStock().getCurrent_price()
        );

        return ResponseEntity.ok(responseDTO);
    }
}