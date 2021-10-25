package org.zikas.ecommerce.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zikas.ecommerce.app.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
