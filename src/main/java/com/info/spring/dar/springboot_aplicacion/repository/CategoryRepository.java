package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Category;

/**
 * Repository interface for the Category entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Returns all categories matching the given name.
     *
     * @param name the category name to search for
     * @return list of matching categories
     */
    List<Category> findByName(String name);
}