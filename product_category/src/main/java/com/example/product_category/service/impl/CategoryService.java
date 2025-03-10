package com.example.product_category.service.impl;

import com.example.product_category.model.Category;
import com.example.product_category.repository.CategoryRepository;
import com.example.product_category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

}
