package com.phsoft.phcommerce.dto;

import com.phsoft.phcommerce.entities.Category;

public class CategoryDTO {

    private Long id;
    private String name;

    public CategoryDTO() {

    }

    public CategoryDTO(Long id) {
        this.id = id;
    }

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
