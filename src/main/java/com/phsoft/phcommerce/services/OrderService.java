package com.phsoft.phcommerce.services;

import com.phsoft.phcommerce.dto.OrderDTO;
import com.phsoft.phcommerce.entities.Order;
import com.phsoft.phcommerce.repositories.OrderRepository;
import com.phsoft.phcommerce.services.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository orderRepository) {
        repository = orderRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) throws ResourceNotFoundException {
        Order entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido " + id +  " não encontrado")
        );
        return new OrderDTO(entity);
    }
}
