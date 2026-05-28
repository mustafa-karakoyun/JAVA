package cineM;
 // Ya da projedeki paket yapına göre: cineM.config veya cineM.util

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cineM.model.Movie;
import cineM.repository.MovieRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;

    // Tek bir constructor olduğu için Spring Boot bunu otomatik enjekte eder (@Autowired gerekmez)
    public DataLoader(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // H2 veritabanına başlangıçta eklenecek atmosferik film arşivimiz:
        // Parametre sırası Movie entity sınıfının yapıcı metoduna (Constructor) göre olmalıdır.
        // Örnek constructor sırası: (Title, Category/Mood, Director, Rating)
    	movieRepository.save(new Movie("The Matrix", "Siberpunk", "Lana Wachowski", 8.7));
        movieRepository.save(new Movie("Blue Valentine", "Melankolik", "Derek Cianfrance", 7.3));
        movieRepository.save(new Movie("Interstellar", "Bilim Kurgu", "Christopher Nolan", 8.7));
        movieRepository.save(new Movie("Blade Runner 2049", "Siberpunk", "Denis Villeneuve", 8.0));
        movieRepository.save(new Movie("La La Land", "Romantik", "Damien Chazelle", 8.0));
    }
}
