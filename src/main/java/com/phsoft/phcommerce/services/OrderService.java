package com.phsoft.phcommerce.services;

import com.phsoft.phcommerce.dto.OrderDTO;
import com.phsoft.phcommerce.dto.OrderItemDTO;
import com.phsoft.phcommerce.entities.*;
import com.phsoft.phcommerce.repositories.OrderItemRepository;
import com.phsoft.phcommerce.repositories.OrderRepository;
import com.phsoft.phcommerce.repositories.ProductRepository;
import com.phsoft.phcommerce.services.exception.ForbiddenException;
import com.phsoft.phcommerce.services.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final AuthService authService;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository,
                        OrderItemRepository orderItemRepository, AuthService authService) {
        repository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.authService = authService;
    }
    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) throws ResourceNotFoundException {
        Order entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido " + id +  " não encontrado")
        );

        authService.validateSelfOrAdmin(entity.getClient().getId());
        return new OrderDTO(entity);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order order = createOrderUsingDto(dto);

        order = repository.save(order);

        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }

    @Transactional
    private Order createOrderUsingDto(OrderDTO dto) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.getAuthenticatedUserByEmail();
        order.setClient(user);


        for(OrderItemDTO orderItemDTO : dto.getItems()) {

            Product product = productRepository.getReferenceById(orderItemDTO.getProductId());

            System.out.println("================");
            System.out.println("Product buscado: " + product);
            OrderItem orderItem = new OrderItem(order, product, orderItemDTO.getQuantity(), product.getPrice());

            order.getItems().add(orderItem);
        }

        return order;
    }
}
