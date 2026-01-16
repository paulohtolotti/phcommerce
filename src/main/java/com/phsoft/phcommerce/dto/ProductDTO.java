package com.phsoft.phcommerce.dto;

import com.phsoft.phcommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ProductDTO {
    private Long id;
    @NotBlank(message = "Não é possível cadastrar um produto sem nome.")
    @Size(min = 3, max = 80, message = "Tamanho inválido.")
    private String name;
    @Size(min = 10, message = "Descrição precisa conter no mínimo 10 caracteres.")
    @NotBlank(message = "descrição não pode estar vazia")
    private String description;
    @Positive(message = "Preço deve ser um valor positivo.")
    private Double price;
    private String imgUrl;

    public ProductDTO() {

    }

    public ProductDTO(Product p) {
        id = p.getId();
        name = p.getName();
        description = p.getDescription();
        price = p.getPrice();
        imgUrl = p.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
