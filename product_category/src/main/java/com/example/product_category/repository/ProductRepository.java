package com.example.product_category.repository;

import com.example.product_category.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByNameContainingIgnoreCase(String name);

    Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable page);
}
