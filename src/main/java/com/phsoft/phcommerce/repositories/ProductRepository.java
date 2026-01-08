package com.phsoft.phcommerce.repositories;

import com.phsoft.phcommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
