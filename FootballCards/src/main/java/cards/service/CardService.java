package cards.service;

import java.util.List;

import org.springframework.stereotype.Service;
import cards.dto.Request;
import cards.dto.Response;
import cards.model.Card;
import cards.repository.CardsRepository;

@Service
public class CardService {
	
	private final CardsRepository repository;
	public CardService(CardsRepository repository) {
		this.repository=repository;
	}
	
	public Response create(Request dto) {
		Card card = new Card();
		card.setFullName(dto.getFullName());
		card.setAge(dto.getAge());
		card.setOverall(dto.getOverall());
		card.setNation(dto.getNation());
		card.setClub(dto.getClub());
		
		Card savedCard = repository.save(card);
		return convertToDTO(savedCard);
		
	}

	private Response convertToDTO(Card c) {
		Response dto = new Response();
		dto.setId(c.getId());
		dto.setFullName(c.getFullName());
		dto.setAge(c.getAge());
		dto.setOverall(c.getOverall());
		dto.setNation(c.getNation());
		dto.setClub(c.getClub());
		
		return dto;
		
	}
	
	public List<Response> getCards(){
		//List<Response> list ;
		//list=repository.findAll().stream().map(this::convertToDTO).toList();
		//return list;
		return repository.findAll().stream().map(this::convertToDTO).toList();
	}
	
	public Response getCardById(Long id) {
		Card card = repository.findById(id).orElse(null);
		if(card != null) {
			return convertToDTO(card);
		}
		return null;
	}
	 public Response update (Long id,Request dto) {
		 Card card = repository.findById(id).orElse(null);
		 if (card!=null) {
			card.setFullName(dto.getFullName());
			card.setAge(dto.getAge());
			card.setOverall(dto.getOverall());
			card.setNation(dto.getNation());
			card.setClub(dto.getClub());
			Card updatedCard = repository.save(card);
			return convertToDTO(updatedCard);
		 }
		 return null;
	 }
	 
	 public void delete(Long id) {
		 repository.deleteById(id);
	 }
	 
	 // ============================================
	 //           EKSTRA METOTLAR
	 //=============================================
	 
	// 1. Kulübe Göre Kartları Filtreleme
		public List<Response> getCardsByClub(String club) {
			return repository.findByClubIgnoreCase(club)
					.stream()
					.map(this::convertToDTO)
					.toList();
		}

		// 2. Ülkeye Göre Kartları Filtreleme
		public List<Response> getCardsByNation(String nation) {
			return repository.findByNationIgnoreCase(nation)
					.stream()
					.map(this::convertToDTO)
					.toList();
		}

		// 3. Yüksek Reytingli "Yıldız Oyuncuları" Getirme (Örn: En az 85 overall)
		public List<Response> getTopRatedCards(int minOverall) {
			return repository.findByOverallGreaterThanEqual(minOverall)
					.stream()
					.map(this::convertToDTO)
					.toList();
		}

		// 4. İsim ile Arama Yapma (Arama çubuğu için)
		public List<Response> searchCardsByName(String keyword) {
			if (keyword == null || keyword.trim().isEmpty()) {
				return List.of(); // Boş aramalarda doğrudan boş liste dönerek SQL'i yormuyoruz
			}
			return repository.findByFullNameContainingIgnoreCase(keyword.trim())
					.stream()
					.map(this::convertToDTO)
					.toList();
		}

		// 5. İstatistik Metodu: Bir Kulübün Yaş Ortalamasını Getirme
		public Double getClubAverageAge(String club) {
			Double averageAge = repository.getAverageAgeByClub(club);
			// Eğer o kulübe ait hiç kart yoksa null döner, onu 0.0'a eşitliyoruz
			return averageAge != null ? averageAge : 0.0;
		}
	
}
