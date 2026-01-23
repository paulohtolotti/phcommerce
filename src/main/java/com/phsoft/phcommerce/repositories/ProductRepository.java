package com.phsoft.phcommerce.repositories;

import com.phsoft.phcommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Busca entidades no banco de dados que contenham parte do parâmetro nome passado.
     * Desconsidera letras maiúsculas e minúsculas na busca.
     * @param name  nome da entidade a ser buscada
     * @return  todas as entidades que contenham name em seu nome
     */
    @Query("SELECT obj " +
            "FROM Product obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pageable);
}
