package com.mustafa.finance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mustafa.finance.model.WatchList;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {
    // Belirli bir kullanıcıya ait tüm izleme listelerini getirmek için
    List<WatchList> findByUserId(Long userId);
}
