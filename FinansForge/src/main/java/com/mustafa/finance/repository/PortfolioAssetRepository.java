package com.mustafa.finance.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mustafa.finance.model.PortfolioAsset;

public interface PortfolioAssetRepository extends JpaRepository<PortfolioAsset, Long> {
    // Bir kullanıcının portföyündeki tüm varlıkları listelemek için
    List<PortfolioAsset> findByUserId(Long userId);
    
    // Kullanıcının portföyünde belirli bir hisseden olup olmadığını kontrol etmek için
    Optional<PortfolioAsset> findByUserIdAndStockId(Long userId, Long stockId);
}
