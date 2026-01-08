package com.phsoft.phcommerce.services;

import com.phsoft.phcommerce.dto.ProductDTO;
import com.phsoft.phcommerce.entities.Product;
import com.phsoft.phcommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    //Operação estritamente de leitura para recuperar 1 produto pelo ID.
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product p = repository.findById(id).get();
        return new ProductDTO(p);
    }

}
