package com.info.spring.dar.springboot_aplicacion.controller;

// Importa las anotaciones necesarias de Spring Boot
import org.springframework.web.bind.annotation.*;

// Permite trabajar con listas
import java.util.List;

// Importa la entidad Category
import com.info.spring.dar.springboot_aplicacion.entity.Category;

// Importa el servicio
import com.info.spring.dar.springboot_aplicacion.service.CategoryService;

/*
 * Esta clase se encargará de recibir las peticiones HTTP
 * relacionadas con las categorías.
 */
@RestController

// Ruta principal para las categorías
@RequestMapping("/categories")
public class CategoryController {

    // Objeto que permitirá acceder a la lógica del servicio
    private final CategoryService categoryService;

    /*
     * Constructor con inyección de dependencias.
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
     * GET
     *
     * Devuelve todas las categorías.
     *
     * URL:
     * http://localhost:8080/categories
     */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /*
     * GET
     *
     * Devuelve una categoría según su id.
     *
     * Ejemplo:
     * http://localhost:8080/categories/1
     */
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    /*
     * GET
     *
     * Busca categorías por nombre.
     *
     * Ejemplo:
     * http://localhost:8080/categories/name/Ejercicio
     */
    @GetMapping("/name/{name}")
    public List<Category> getCategoriesByName(@PathVariable String name) {
        return categoryService.getCategoriesByName(name);
    }

    /*
     * POST
     *
     * Guarda una nueva categoría.
     */
    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    /*
     * DELETE
     *
     * Elimina una categoría según su id.
     *
     * Ejemplo:
     * http://localhost:8080/categories/1
     */
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}