package com.phsoft.phcommerce.services;

import com.phsoft.phcommerce.dto.CategoryDTO;
import com.phsoft.phcommerce.entities.Category;
import com.phsoft.phcommerce.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> categoryPage = categoryRepository.findAll();

        return categoryPage.stream().map(CategoryDTO::new).toList();
    }
}
