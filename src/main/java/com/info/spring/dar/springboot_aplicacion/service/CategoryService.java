package com.info.spring.dar.springboot_aplicacion.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.repository.ActivityRepository;
import com.info.spring.dar.springboot_aplicacion.repository.CategoryRepository;

/**
 * Service class that contains the business logic for managing categories.
 * Communicates with CategoryRepository and ActivityRepository to access the database.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ActivityRepository activityRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param categoryRepository the repository used to access the categories table
     * @param activityRepository the repository used to disassociate activities before deletion
     */
    public CategoryService(CategoryRepository categoryRepository,
            ActivityRepository activityRepository) {
        this.categoryRepository = categoryRepository;
        this.activityRepository = activityRepository;
    }

    /**
     * Saves a category to the database.
     *
     * @param category the category to save
     * @return the saved category
     */
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Returns all categories in the system.
     *
     * @return list of all categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Returns a single category by its ID, or null if not found.
     *
     * @param id the category ID
     * @return the matching category or null
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * Returns all categories matching the given name.
     *
     * @param name the name to search for
     * @return list of matching categories
     */
    public List<Category> getCategoriesByName(String name) {
        return categoryRepository.findByName(name);
    }

    /**
     * Deletes a category by its ID.
     * Before deleting, sets the category field to null on all associated activities
     * to avoid a foreign key constraint violation.
     *
     * @param id the ID of the category to delete
     */
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        if (category == null) {
            return;
        }
        List<Activity> activities = activityRepository.findByCategory(category);
        for (Activity activity : activities) {
            activity.setCategory(null);
        }
        activityRepository.saveAll(activities);
        categoryRepository.deleteById(id);
    }
}