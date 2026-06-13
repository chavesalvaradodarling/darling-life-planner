package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa la entidad Category
import com.info.spring.dar.springboot_aplicacion.entity.Category;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.CategoryRepository;

/*
 * Esta clase contiene la lógica relacionada con las categorías.
 *
 * Se comunica con CategoryRepository para acceder a la base de datos.
 */
@Service
public class CategoryService {

    // Objeto que permite acceder a la tabla categories
    private final CategoryRepository categoryRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /*
     * Guarda una categoría en la base de datos.
     */
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    /*
     * Devuelve todas las categorías registradas.
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /*
     * Busca una categoría por su id.
     *
     * Si no existe, devuelve null.
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /*
     * Permite buscar categorías por nombre.
     */
    public List<Category> getCategoriesByName(String name) {
        return categoryRepository.findByName(name);
    }

    /*
     * Elimina una categoría por su id.
     */
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}