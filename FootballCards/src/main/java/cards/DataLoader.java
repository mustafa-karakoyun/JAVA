package cards;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cards.model.Card;
import cards.repository.CardsRepository;


@Component
public class DataLoader implements CommandLineRunner {

    private final CardsRepository repository; // Güncellendi

    // Constructor Injection
    public DataLoader(CardsRepository repository) { // Güncellendi
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Mükerrer (duplicate) veri oluşumunu engelleme mantığınız çok doğru
        if (repository.count() == 0) {
            
            System.out.println(">> Futbolcu kartları veritabanına yükleniyor...");

            // 1. Real Madrid Oyuncuları
            repository.save(new Card(null, "Kylian Mbappé", 27, 91, "France", "Real Madrid"));
            repository.save(new Card(null, "Jude Bellingham", 22, 90, "England", "Real Madrid"));
            repository.save(new Card(null, "Arda Güler", 21, 83, "Turkey", "Real Madrid"));

            // 2. Barcelona Oyuncuları
            repository.save(new Card(null, "Robert Lewandowski", 37, 88, "Poland", "Barcelona"));
            repository.save(new Card(null, "Lamine Yamal", 18, 84, "Spain", "Barcelona"));

            // 3. Diğer Popüler Kartlar
            repository.save(new Card(null, "Lionel Messi", 38, 92, "Argentina", "Inter Miami"));
            repository.save(new Card(null, "Cristiano Ronaldo", 41, 89, "Portugal", "Al Nassr"));
            repository.save(new Card(null, "Erling Haaland", 25, 91, "Norway", "Manchester City"));
            repository.save(new Card(null, "Hakan Çalhanoğlu", 32, 86, "Turkey", "Inter"));

            System.out.println(">> " + repository.count() + " adet futbolcu kartı başarıyla yüklendi!");
        } else {
            System.out.println(">> Veritabanında zaten " + repository.count() + " adet kart var, yükleme atlandı.");
        }
    }
}