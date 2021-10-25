package org.zikas.ecommerce.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zikas.ecommerce.app.entities.Product;
import org.zikas.ecommerce.app.repositories.ProductRepository;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null,"Computer",7600));
        productRepository.save(new Product(null,"Printer",1600));
        productRepository.save(new Product(null,"Smartphone",1600));
    }
}
