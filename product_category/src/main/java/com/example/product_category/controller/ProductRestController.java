package com.example.product_category.controller;

import com.example.product_category.model.Category;
import com.example.product_category.model.Product;
import com.example.product_category.service.ICategoryService;
import com.example.product_category.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductRestController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    // Lấy danh sách sản phẩm (với tìm kiếm theo tên và phân trang)
    @GetMapping("")
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return productService.findByName(name, page);
    }

    // Lấy thông tin sản phẩm theo id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.findById(id);
    }

    // Thêm sản phẩm mới
    @PostMapping("")
    public Product createProduct(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    // Cập nhật sản phẩm theo id
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        productService.update(id, product);
        return productService.findById(id);
    }

    // Xóa sản phẩm theo id
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.remove(id);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.getAll();

        return ResponseEntity.ok(Collections.singletonMap("data", categories));
    }
}
