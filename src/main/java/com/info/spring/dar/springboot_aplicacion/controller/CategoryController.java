package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.service.CategoryService;

/**
 * REST controller that exposes API endpoints for managing categories.
 * Base URL: /categories
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    /** Service that handles all category-related business logic. */
    private final CategoryService categoryService;

    /**
     * Constructor with dependency injection.
     *
     * @param categoryService the service used to manage categories
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Returns all categories in the system.
     * GET /categories
     *
     * @return list of all categories
     */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Returns a single category by its ID.
     * GET /categories/{id}
     *
     * @param id the category ID
     * @return the matching category, or null if not found
     */
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    /**
     * Returns all categories matching the given name.
     * GET /categories/name/{name}
     *
     * @param name the category name to search for
     * @return list of matching categories
     */
    @GetMapping("/name/{name}")
    public List<Category> getCategoriesByName(@PathVariable String name) {
        return categoryService.getCategoriesByName(name);
    }

    /**
     * Saves a new category.
     * POST /categories
     *
     * @param category the category object received in the request body
     * @return the saved category
     */
    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    /**
     * Deletes a category by its ID.
     * DELETE /categories/{id}
     *
     * @param id the ID of the category to delete
     */
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}