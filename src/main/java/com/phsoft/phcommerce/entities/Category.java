package com.phsoft.phcommerce.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name="tb_category_product",
        joinColumns=@JoinColumn(name="category_id"),
        inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products = new HashSet<>();

}
