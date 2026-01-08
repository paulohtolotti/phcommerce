package com.phsoft.phcommerce.controller;

import com.phsoft.phcommerce.dto.ProductDTO;
import com.phsoft.phcommerce.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    // Inejeção de dependência no construtor do Controller
    private ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Método que recebe um id via requisição HTTP do cliente.
     * @param id: ID do produto. Valor recebido da requisição.
     * @return
     */
    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }


}
