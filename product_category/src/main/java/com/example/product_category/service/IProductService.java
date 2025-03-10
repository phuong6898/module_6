package com.example.product_category.service;

import com.example.product_category.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    void save(Product product);
    void update(int id, Product product);
    void remove(int id);
    Product findById(int id);
    List<Product> findByName(String name);
    Page<Product> findByName(String name,Integer page);
}
