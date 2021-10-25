package org.zikas.ecommerce.supplier.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.zikas.ecommerce.supplier.server.entities.Supplier;

@RepositoryRestResource
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
