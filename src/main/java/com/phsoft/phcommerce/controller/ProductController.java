package com.phsoft.phcommerce.controller;

import com.phsoft.phcommerce.dto.ProductDTO;
import com.phsoft.phcommerce.entities.Product;
import com.phsoft.phcommerce.services.ProductService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Método que faz a busca paginada de todos os produtos cadastrados.
     * @param none 
     * @return List<Page>: listagem paginada
     */
    @GetMapping
    public Page<ProductDTO> findAll(Pageable page) {
        return service.findAll(page);
    }

    /**
     * Método que realiza a inserção de um novo produto no banco de dados.
     * 
     * @param dto objeto dto instanciado a partir do corpo da requisição 
     * @return objeto dto atualizado após ser inserido no BD
     */
    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto) {
        return service.insert(dto);
    }


}
