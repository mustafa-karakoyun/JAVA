package com.mustafa.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.mustafa.demo.model.Product;
import com.mustafa.demo.repository.ProductRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    // Tek bir constructor olduğu için @Autowired yazmamıza gerek yok
    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product("Laptop", 15000.0));
        productRepository.save(new Product("Telefon", 8000.0));
        productRepository.save(new Product("Klavye", 1200.0));
    }
}