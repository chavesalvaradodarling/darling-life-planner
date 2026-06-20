package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.service.CategoryService;

@Controller
public class CategoryViewController {

    private final CategoryService categoryService;

    public CategoryViewController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
     * Muestra todas las categorías
     */
    @GetMapping("/categoriesPage")
    public String showCategories(Model model) {

        model.addAttribute(
                "categories",
                categoryService.getAllCategories());

        return "categories";
    }

    /*
     * Muestra el formulario para crear una categoría
     */
    @GetMapping("/newCategory")
    public String showCategoryForm(Model model) {

        model.addAttribute(
                "category",
                new Category());

        return "categoryForm";
    }

    /*
     * Guarda una categoría
     */
    @PostMapping("/saveCategory")
    public String saveCategory(Category category) {

        categoryService.saveCategory(category);

        return "redirect:/categoriesPage";
    }

}