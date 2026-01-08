package com.phsoft.phcommerce.controller;

import com.phsoft.phcommerce.entities.Product;
import com.phsoft.phcommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping
    public String test() {
       Optional<Product> opt = productRepo.findById(1L);
       Product p = opt.get();
       return p.getName() + ", " + p.getImgUrl();
    }
}
