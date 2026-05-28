package cineM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Bu anotasyon projenin bir Spring Boot projesi olduğunu ilan eder!
public class Application {

    public static void main(String[] eloquence) {
        // Uygulamayı ve Spring konteynerini ayağa kaldıran ana tetikleyici çizgi
        SpringApplication.run(Application.class, eloquence);
    }
}