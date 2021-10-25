package org.zikas.ecommerce.supplier.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.zikas.ecommerce.supplier.server.entities.Supplier;
import org.zikas.ecommerce.supplier.server.repositories.SupplierRepository;

import java.util.stream.Stream;

@SpringBootApplication
public class SupplierRestApplication {
    @Autowired
    SupplierRepository supplierRepository;
    public static void main(String[] args) {
        SpringApplication.run(SupplierRestApplication.class, args);
    }
    @Bean
    CommandLineRunner lineRunner(SupplierRepository supplierRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Supplier.class);
            Stream.of("JBOSS","ORACLE","IBM").forEach(n->{
                supplierRepository.save(new Supplier(null,n,n+"@"+n.toLowerCase()+".com"));
            });
        };


    }
}
