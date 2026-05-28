package cineM.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cineM.model.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {
	Optional<Movie> findByTitleIgnoreCase(String title);
	
	List<Movie> findByCategory(String category);
	
	List<Movie> findByTitleContainingIgnoreCase(String title);
	
	List<Movie> findTop5ByOrderByRatingDesc();
	
}
