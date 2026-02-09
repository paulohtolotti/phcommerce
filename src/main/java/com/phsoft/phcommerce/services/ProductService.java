package com.phsoft.phcommerce.services;

import com.phsoft.phcommerce.dto.ProductDTO;
import com.phsoft.phcommerce.dto.ProductMinDTO;
import com.phsoft.phcommerce.entities.Product;
import com.phsoft.phcommerce.repositories.ProductRepository;
import com.phsoft.phcommerce.services.exception.DatabaseException;
import com.phsoft.phcommerce.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    public ProductDTO findById(Long id) throws ResourceNotFoundException {

        //Tenta acessar a entidade Produto, se não acessar lança uma exceção
        Product p = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Produto não encontrado.")
        );
        return new ProductDTO(p);
    }

    /**
     * Busca paginada de produtos. Utiliza o método map para transformar cada Product em um ProductDTO
     * @param pageable
     * @return página de produtos DTO, que são entregues ao Controller.
     */
    @Transactional(readOnly =  true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        return repository.searchByName(name, pageable).map(ProductMinDTO::new);
    }

    /**
     * Insere um novo produto no banco de dados.
     * Cria um novo Product usando os dados do ProductDTO, instanciado a partir do JSON.
     * @param dto ProductDTO instanciado a partir do JSON do corpo da requisição.
     * @return ProductDTO atualizado
     */
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product p = new Product();
        copyDtoToEntity(dto, p);

        // Salva a entidade no banco de dados e atualiza a referência
        p = repository.save(p);
        return new ProductDTO(p);
    }

    /**
     * Atualiza de maneira idempotente um produto.
     * @param id 
     * @return
     */
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) throws ResourceNotFoundException {
        //Instancia um objeto monitorado pela JPA, sem acessar o BD, apenas pega a referência.
        try {
            Product p = repository.getReferenceById(id);
            copyDtoToEntity(dto, p);
            p = repository.save(p);
            return new ProductDTO(p);
        } catch (EntityNotFoundException err) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

    }

    /**
     * Delete um produto a partir de seu di
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        //Checa se o recurso não existe e lança uma exceção de recurso inexistente
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ID inexistente");
        }

        //Bloco try catch para capturar exceções de violação de integridade
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException err) {
            throw new DatabaseException("Não é possível deletar pois viola integridade referencial.");
        }
    }

    /**
     * Método auxiliar que copia os atributos de um DTO para uma entidade.
     * @param dto Objeto DTO com os dados recebidos do corpo da requisição.
     * @param p Entidade do tipo produto
     * @return
     */
    private static void copyDtoToEntity (ProductDTO dto, Product p) {
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setImgUrl(dto.getImgUrl());
        p.setDescription(dto.getDescription());
    }
}
