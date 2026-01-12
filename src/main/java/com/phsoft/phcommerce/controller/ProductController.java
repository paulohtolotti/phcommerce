package com.phsoft.phcommerce.controller;

import com.phsoft.phcommerce.dto.ProductDTO;
import com.phsoft.phcommerce.services.ProductService;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


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
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Método que faz a busca paginada de todos os produtos cadastrados.
     * @param none 
     * @return List<Page>: listagem paginada
     */
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable page) {
        Page<ProductDTO> dto =  service.findAll(page);
        return ResponseEntity.ok(dto);
    }

    /**
     * Método que realiza a inserção de um novo produto no banco de dados.
     * Insere a URI do recurso criado no cabeçalho da requisição, usando o objeto URI.
     * @param dto objeto dto instanciado a partir do corpo da requisição 
     * @return objeto dto atualizado após ser inserido no BD
     */
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = service.insert(dto);

        //Boa prática para a resposta da criação.
        // Insere no cabeçalho da respsota a URI do recurso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


}
