package com.phsoft.phcommerce.repositories;

import com.phsoft.phcommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
