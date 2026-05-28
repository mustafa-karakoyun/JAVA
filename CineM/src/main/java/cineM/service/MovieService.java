package cineM.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cineM.dto.MovieRequestDTO;
import cineM.dto.MovieResponseDTO;
import cineM.model.Movie;
import cineM.repository.MovieRepository;

@Service 
public class MovieService {

	private final MovieRepository movieRepository;
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository=movieRepository;
	}
	
	// Requestten gelen bilgileri alıp db ye kayıt edecegim.
	public MovieResponseDTO create(MovieRequestDTO dto) {
		Movie movie = new Movie(); // YENİ BİR MOVİE NESNESİ OLUŞTURDUM.
		// Yeni oluşturdugum movienin titleına dışarıdan gelen dto nun titılını yazıyorum.
		movie.setTitle(dto.getTitle()); 
		movie.setCategory(dto.getCategory());
		movie.setDirector(dto.getDirector());
		movie.setRating(dto.getRating());
		
		Movie savedMovie = movieRepository.save(movie);
		// DÜZELTME: ID null gelmesin diye burayı da convertToDto'ya yönlendirdik.
		return convertToDto(savedMovie);
		
	}
	
	// Tüm filmleri veritabanından çekiyoruz. 
	public List<MovieResponseDTO> getMovies(){
		// DÜZELTME: İçerideki manuel set ameleliğini kaldırıp ortak metoda (this::convertToDto) bağladık. 
		// Böylece tüm listede ID'ler tıkır tıkır gelecek.
		return movieRepository.findAll().stream().map(this::convertToDto).toList();
	}
	
	// Bir movie nesnesi oluştur. --> movieRepotiory uzerinde id ile arama yap. ve movieye at.
	public MovieResponseDTO getMovieById(Long id) {
		Movie movie = movieRepository.findById(id).orElse(null);
		if (movie!= null) {
			return convertToDto(movie);
		}
		return null;
		 
	}
	private MovieResponseDTO convertToDto(Movie m) {
		MovieResponseDTO dto = new MovieResponseDTO();
		dto.setId(m.getId()); // Burası artık güvende!
		dto.setTitle(m.getTitle());
		dto.setCategory(m.getCategory());
		dto.setDirector(m.getDirector());
		dto.setRating(m.getRating());
		return dto;
	}
	
	public MovieResponseDTO update(Long id,MovieRequestDTO updatedDto) {
		Movie movie = movieRepository.findById(id).orElse(null);
		if (movie!= null) {
			movie.setCategory(updatedDto.getCategory());
			movie.setDirector(updatedDto.getDirector());
			movie.setTitle(updatedDto.getTitle());
			movie.setRating(updatedDto.getRating());
			
			Movie updatedMovie = movieRepository.save(movie);
			return convertToDto(updatedMovie);
		}
		return null;
	}
	
	public void delete(Long id) {
		movieRepository.deleteById(id);
	}
	
	// EXTRA METOTLAR
	
	public MovieResponseDTO getMovieByTitle(String title) {
		Movie movie= movieRepository.findByTitleIgnoreCase(title).orElse(null);
		if(movie!=null) {
			return convertToDto(movie);
		}
		return null;
	}
	public List<MovieResponseDTO> getMoviesByCategory(String category) {
	    return movieRepository.findByCategory(category)
	            .stream()
	            .map(this::convertToDto)
	            .toList();
	}

	// Ön yüzde sinematik bir "En Beğenilenler" vitrini oluşturmak için en yüksek puanlı 5 filmi getirir.
	public List<MovieResponseDTO> getTopRatedMovies() {
		return movieRepository.findTop5ByOrderByRatingDesc()
				.stream()
				.map(this::convertToDto)
				.toList();
	}

	// Kullanıcı arama çubuğuna (Search Bar) harf yazdıkça eşleşen filmleri süzmek için kullanılır.
	public List<MovieResponseDTO> searchMoviesByTitle(String title) {
		return movieRepository.findByTitleContainingIgnoreCase(title)
				.stream()
				.map(this::convertToDto)
				.toList();
	}
}