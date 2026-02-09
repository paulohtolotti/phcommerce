package com.phsoft.phcommerce.repositories;

import com.phsoft.phcommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
