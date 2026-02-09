package com.phsoft.phcommerce.controller;

import com.phsoft.phcommerce.dto.ProductDTO;
import com.phsoft.phcommerce.services.ProductService;

import java.net.URI;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    // Inejeção de dependência no construtor do Controller
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Rota que recebe um id via requisição HTTP do cliente.
     * @param id: ID do produto. Valor recebido da requisição.
     * @return
     */
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Rota que faz a busca paginada de todos os produtos cadastrados.
     * @param
     * @return List<Page>: listagem paginada
     */
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(name = "name", defaultValue = "") String name, Pageable page) {
        Page<ProductDTO> dto =  service.findAll(name, page);
        return ResponseEntity.ok(dto);
    }

    /**
     * Rota que realiza a inserção de um novo produto no banco de dados.
     * Insere a URI do recurso criado no cabeçalho da requisição, usando o objeto URI.
     * @param dto objeto dto instanciado a partir do corpo da requisição 
     * @return objeto dto atualizado após ser inserido no BD
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = service.insert(dto);

        //Constrói a URI para ser inserida no cabeçalho da requisição
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /**
     * Rota que realiza o update de um produto.
     * @param id identificador único de um produto
     * @param dto conjunto de dados a serem atualizados
     * @return resposta para o cliente com os dados da entidade atualizados
     */
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    /**
     * Rota que deleta uma entidade
     * @param id identificador único do produto
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
