package com.phsoft.phcommerce.repositories;

import com.phsoft.phcommerce.entities.OrderItem;
import com.phsoft.phcommerce.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
