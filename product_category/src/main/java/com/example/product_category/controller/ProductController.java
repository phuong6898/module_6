package com.example.product_category.controller;

import com.example.product_category.model.Product;
import com.example.product_category.service.ICategoryService;
import com.example.product_category.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ModelAndView viewAllStudent(Model model, @RequestParam(defaultValue = "") String name, @RequestParam(name = "page", defaultValue = "0")Integer page) {
        model.addAttribute("name", name);
        return new ModelAndView("list", "products", productService.findByName(name, page));
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "add";
    }

    @PostMapping("/add")
    public String addProduct(
            @Validated @ModelAttribute("product") Product product,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "add";
        }

        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll()); // Thêm danh sách category
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") int id, @ModelAttribute("product") Product product) {
        productService.update(id, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.remove(id);
        return "redirect:/products";
    }
}
