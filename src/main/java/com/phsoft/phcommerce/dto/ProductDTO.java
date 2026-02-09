package com.phsoft.phcommerce.dto;

import com.phsoft.phcommerce.entities.Category;
import com.phsoft.phcommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.ArrayList;

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

    @NotEmpty(message = "Produto deve conter, no mínimo, 1 categoria")
    List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO() {

    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product p) {
        id = p.getId();
        name = p.getName();
        description = p.getDescription();
        price = p.getPrice();
        imgUrl = p.getImgUrl();

        for(Category c : p.getCategories()) {
            categories.add(new CategoryDTO(c));
        }
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

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void addCategory(CategoryDTO categoryDTO) {
        categories.add(categoryDTO);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", categories=" + categories +
                '}';
    }


}
