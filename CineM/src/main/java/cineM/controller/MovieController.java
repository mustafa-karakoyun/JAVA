package cineM.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cineM.dto.MovieRequestDTO;
import cineM.dto.MovieResponseDTO;
import cineM.service.MovieService;
import jakarta.validation.Valid;

@RestController // 1. Bu sınıfın bir REST API olduğunu belirtiyoruz
@RequestMapping("/api/movies") // 2. Bu controller'ın ana giriş kapısı URL'i
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {

	private final MovieService movieService;
	public MovieController(MovieService movieService) {
		this.movieService=movieService;
	}
	
	@GetMapping
	public ResponseEntity<List<MovieResponseDTO>> getAllMovies(){
		return ResponseEntity.ok(movieService.getMovies());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> getMovie(@PathVariable Long id){
		MovieResponseDTO dto = movieService.getMovieById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<MovieResponseDTO> createMovie(@Valid @RequestBody MovieRequestDTO movie){
		MovieResponseDTO dto = movieService.create(movie);
		return ResponseEntity.status(201).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id,
			@Valid @RequestBody MovieRequestDTO movie ){
		MovieResponseDTO dto = movieService.update(id, movie);
		return  ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
		if(movieService.getMovieById(id)!=null) {
			movieService.delete(id);
    		return ResponseEntity.noContent().build();
    	}else {
            return ResponseEntity.notFound().build(); // Ürün yoksa HTTP 404
        }
	}
	
	// SERVICE DOSYASINA GÖRE EKLENEN EXTRA ENDPOINT'LER

		// Film adına göre tam eşleşme arar
		@GetMapping("/search-title")
		public ResponseEntity<MovieResponseDTO> getMovieByTitle(@RequestParam String title) {
			MovieResponseDTO dto = movieService.getMovieByTitle(title);
			if (dto != null) {
				return ResponseEntity.ok(dto);
			}
			return ResponseEntity.notFound().build();
		}

		// Belirli bir kategoriye (atmosfere) ait tüm filmleri listeler
		@GetMapping("/category/{category}")
		public ResponseEntity<List<MovieResponseDTO>> getMoviesByCategory(@PathVariable String category) {
			List<MovieResponseDTO> list = movieService.getMoviesByCategory(category);
			return ResponseEntity.ok(list);
		}

		// En yüksek puanlı ilk 5 filmi getirir (Vitrin için)
		@GetMapping("/top-rated")
		public ResponseEntity<List<MovieResponseDTO>> getTopRatedMovies() {
			List<MovieResponseDTO> list = movieService.getTopRatedMovies();
			return ResponseEntity.ok(list);
		}

		// Kullanıcı arama çubuğuna yazdıkça harf içeren filmleri filtreler
		@GetMapping("/search")
		public ResponseEntity<List<MovieResponseDTO>> searchMoviesByTitle(@RequestParam String title) {
			List<MovieResponseDTO> list = movieService.searchMoviesByTitle(title);
			return ResponseEntity.ok(list);
		}
}
