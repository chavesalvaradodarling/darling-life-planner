package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.service.CategoryService;

/**
 * MVC controller that handles the category views and form submissions.
 * Manages the category list, creation, editing, and deletion.
 */
@Controller
public class CategoryViewController {

    /** Service that handles all category-related business logic. */
    private final CategoryService categoryService;

    /**
     * Constructor with dependency injection.
     *
     * @param categoryService the service used to manage categories
     */
    public CategoryViewController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Displays all categories in the system.
     * GET /categoriesPage
     *
     * @param model the Spring MVC model
     * @return the categories list view template
     */
    @GetMapping("/categoriesPage")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    /**
     * Displays the form to create a new category.
     * GET /newCategory
     *
     * @param model the Spring MVC model
     * @return the category form template
     */
    @GetMapping("/newCategory")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    /**
     * Saves a new or edited category.
     * POST /saveCategory
     *
     * @param category the category object bound from the form
     * @return redirect to the categories page
     */
    @PostMapping("/saveCategory")
    public String saveCategory(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categoriesPage";
    }

    /**
     * Displays the form pre-filled with an existing category's data for editing.
     * GET /editCategory/{id}
     *
     * @param id    the ID of the category to edit
     * @param model the Spring MVC model
     * @return the category form template
     */
    @GetMapping("/editCategory/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categoryForm";
    }

    /**
     * Deletes a category by its ID.
     * GET /deleteCategory/{id}
     *
     * @param id the ID of the category to delete
     * @return redirect to the categories page
     */
    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categoriesPage";
    }
}