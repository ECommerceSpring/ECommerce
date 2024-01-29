package com.ecommerce.marketcore.controller;

import com.ecommerce.marketcore.model.Category;
import com.ecommerce.marketcore.service.Interface.ICategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping("/Category/Main")
    public List<Category> getMainCategories() {
        return categoryService.getAllMainCategories();
    }

    @GetMapping("/Category/ByParentCategoryId/{parentCategoryId}")
    public List<Category> getSubCategories(@PathVariable int parentCategoryId) {
        return categoryService.getAllByParentId(parentCategoryId);
    }

    @PostMapping("/Category")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/Category")
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.update(category);
    }
}
