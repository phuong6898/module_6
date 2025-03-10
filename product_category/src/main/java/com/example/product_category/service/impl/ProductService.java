package com.example.product_category.service.impl;

import com.example.product_category.model.Product;
import com.example.product_category.repository.ProductRepository;
import com.example.product_category.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    public ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(int id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setImportDate(product.getImportDate());
            existingProduct.setCategory(product.getCategory());
            productRepository.save(existingProduct);
        }else {
            System.out.println("Product not found with id: "+ id);
        }
    }

    @Override
    public void remove(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }
    @Override
    public Page<Product> findByName(String name, Integer page) {
        return productRepository.findAllByNameContainingIgnoreCase(name, PageRequest.of(page,5));
    }
}
