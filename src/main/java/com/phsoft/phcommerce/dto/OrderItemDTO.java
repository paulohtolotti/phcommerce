package com.phsoft.phcommerce.dto;

import com.phsoft.phcommerce.entities.OrderItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class OrderItemDTO {

    private Long productId;
    private String name;
    @PositiveOrZero(message = "Preço não pode ser negativo")
    private Double price;
    @Positive(message = "Quantidade deve ser maior que zero.")
    private Integer quantity;
    private String imgUrl;

    public OrderItemDTO() {

    }
    public OrderItemDTO(Long productId, String name, Double price, Integer quantity, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public OrderItemDTO(OrderItem entity) {
        productId = entity.getProduct().getId();
        name = entity.getProduct().getName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
        imgUrl = entity.getProduct().getImgUrl();
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubTotal() {
        return quantity * price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
