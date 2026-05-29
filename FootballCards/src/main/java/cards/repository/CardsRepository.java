package cards.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cards.model.Card;

public interface  CardsRepository extends JpaRepository<Card, Long> {
	Optional<Card> findByfullNameIgnoreCase(String fullName);
	
	// Kulübe göre filtreleme
    List<Card> findByClubIgnoreCase(String club);
    
    // Millete göre filtreleme
    List<Card> findByNationIgnoreCase(String nation);
    
    // Belirli bir genel reyting (overall) ve üzerindeki oyuncuları getirme
    List<Card> findByOverallGreaterThanEqual(int overall);
    
    // İsim içinde arama yapma (Arama motoru mantığı - LIKE %keyword%)
    List<Card> findByFullNameContainingIgnoreCase(String keyword);
    
    // JPQL kullanarak bir kulübün yaş ortalamasını hesaplama
    @Query("SELECT AVG(c.age) FROM Card c WHERE LOWER(c.club) = LOWER(:club)")
    Double getAverageAgeByClub(@Param("club") String club);
}
