package cards.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import cards.dto.Request;
import cards.dto.Response;
import cards.service.CardService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cards")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CardController {
	private final CardService cardService;
	public CardController(CardService cardService) {
		this.cardService=cardService;
	}
	
	@GetMapping
	public ResponseEntity<List<Response>> getAllCards(){
		return ResponseEntity.ok(cardService.getCards());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getCardById(@PathVariable Long id){
		Response response = cardService.getCardById(id);
	    
	    if (response != null) {
	        return ResponseEntity.ok(response); // Kart bulunduysa 200 OK ve veriyi dön
	    } else {
	        return ResponseEntity.notFound().build(); // Kart bulunamadıysa 404 Not Found dön
	    }	
	}
	
	@PostMapping
	public ResponseEntity<Response> create(@Valid @RequestBody Request card){
		Response dto = cardService.create(card);
		return ResponseEntity.status(201).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> update(@PathVariable Long id,
			@Valid @RequestBody Request dto){
		Response card = cardService.update(id, dto);
		return ResponseEntity.ok(card);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id){
		Response response = cardService.getCardById(id);
		
		if (response != null) {
			cardService.delete(id);
			return ResponseEntity.noContent().build(); // 204 No Content
		} else {
			return ResponseEntity.notFound().build(); // 404 Not Found
		}
	}
	
	
	    // ==========================================
		//   EKSTRA METOTLAR  (Filtreleme & Arama)
		// ==========================================

		// 1. Kulübe Göre Filtreleme -> GET /api/v1/cards/club/Real Madrid
		@GetMapping("/club/{clubName}")
		public ResponseEntity<List<Response>> getCardsByClub(@PathVariable String clubName) {
			return ResponseEntity.ok(cardService.getCardsByClub(clubName));
		}

		// 2. Ülkeye Göre Filtreleme -> GET /api/v1/cards/nation/Turkey
		@GetMapping("/nation/{nationName}")
		public ResponseEntity<List<Response>> getCardsByNation(@PathVariable String nationName) {
			return ResponseEntity.ok(cardService.getCardsByNation(nationName));
		}

		// 3. Reytinge Göre Filtreleme (Belirtilen değer ve üzeri) -> GET /api/v1/cards/top?min=85
		@GetMapping("/top")
		public ResponseEntity<List<Response>> getTopRatedCards(@RequestParam(defaultValue = "85") int min) {
			return ResponseEntity.ok(cardService.getTopRatedCards(min));
		}

		// 4. İsim ile Arama (Arama Çubuğu) -> GET /api/v1/cards/search?name=Ronaldo
		@GetMapping("/search")
		public ResponseEntity<List<Response>> searchCardsByName(@RequestParam String name) {
			return ResponseEntity.ok(cardService.searchCardsByName(name));
		}

		// 5. Kulübün Yaş Ortalaması (İstatistik) -> GET /api/v1/cards/club/Barcelona/average-age
		@GetMapping("/club/{clubName}/average-age")
		public ResponseEntity<Double> getClubAverageAge(@PathVariable String clubName) {
			return ResponseEntity.ok(cardService.getClubAverageAge(clubName));
		}
	
	
}
