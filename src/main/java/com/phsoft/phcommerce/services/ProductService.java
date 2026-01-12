package com.phsoft.phcommerce.services;

import com.phsoft.phcommerce.dto.ProductDTO;
import com.phsoft.phcommerce.entities.Product;
import com.phsoft.phcommerce.repositories.ProductRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

   /**
    * Busca um produto pelo seu ID. 
    * @param id id de um produto.
    * @return
    */
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product p = repository.findById(id).get();
        return new ProductDTO(p);
    }

    /**
     * Busca paginada de produtos. Utiliza o método map para transformar cada Product em um ProductDTO
     * @param pageable
     * @return página de produtos DTO, que são entregues ao Controller.
     */
    @Transactional(readOnly =  true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(x -> new ProductDTO(x));
    }

}
