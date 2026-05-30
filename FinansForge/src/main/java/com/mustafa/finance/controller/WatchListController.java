package com.mustafa.finance.controller;

import com.mustafa.finance.dto.WatchListResponseDTO;
import com.mustafa.finance.model.WatchList;
import com.mustafa.finance.service.WatchListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/watchlist")
@CrossOrigin(origins = "*")
public class WatchListController {

    private final WatchListService watchListService;

    public WatchListController(WatchListService watchListService) {
        this.watchListService = watchListService;
    }

    /**
     * Belirlenen takip listesine borsa kodu (ticker) ile yeni bir hisse ekler.
     */
    @PostMapping("/{watchlistId}/add-stock")
    public ResponseEntity<WatchListResponseDTO> addStockToWatchList(
            @PathVariable Long watchlistId,
            @RequestParam String ticker) {
        
        WatchList watchList = watchListService.addStockToWatchList(watchlistId, ticker);
        return ResponseEntity.ok(convertToResponseDTO(watchList));
    }

    /**
     * Belirlenen takip listesinden ilgili hisse senedini çıkarır (Ara tablodan siler).
     */
    @DeleteMapping("/{watchlistId}/remove-stock")
    public ResponseEntity<Void> removeStockFromWatchList(
            @PathVariable Long watchlistId,
            @RequestParam String ticker) {
        
        watchListService.removeStockFromWatchList(watchlistId, ticker);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content döner
    }

    /**
     * WatchList Entity modelini, döngüsel bağımlılıkları kırarak 
     * temiz bir WatchListResponseDTO paketine dönüştüren yardımcı metot.
     */
    private WatchListResponseDTO convertToResponseDTO(WatchList watchList) {
        WatchListResponseDTO responseDTO = new WatchListResponseDTO();
        responseDTO.setId(watchList.getId());
        responseDTO.setName(watchList.getName());

        if (watchList.getStocks() != null) {
            List<WatchListResponseDTO.StockDTO> stockDTOs = watchList.getStocks().stream().map(stock ->
                new WatchListResponseDTO.StockDTO(
                        stock.getId(),
                        stock.getTicker(),
                        stock.getName(),
                        stock.getCurrent_price()
                )
            ).collect(Collectors.toList());
            responseDTO.setStocks(stockDTOs);
        }
        return responseDTO;
    }
}