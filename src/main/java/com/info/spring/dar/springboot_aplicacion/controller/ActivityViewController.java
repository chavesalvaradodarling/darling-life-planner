package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;
import com.info.spring.dar.springboot_aplicacion.service.CategoryService;

@Controller
public class ActivityViewController {

    // Servicio de actividades
    private final ActivityService activityService;

    // Servicio de categorías
    private final CategoryService categoryService;

    // Constructor
    public ActivityViewController(
            ActivityService activityService,
            CategoryService categoryService) {

        this.activityService = activityService;
        this.categoryService = categoryService;
    }

    /*
     * Muestra todas las actividades
     */
    @GetMapping("/activitiesPage")
    public String showActivities(Model model) {

        model.addAttribute(
                "activities",
                activityService.getAllActivities());

        return "activities";
    }

    /*
     * Formulario para una nueva actividad
     */
    @GetMapping("/newActivity")
    public String showActivityForm(Model model) {

        model.addAttribute(
                "activity",
                new Activity());

        model.addAttribute(
                "categories",
                categoryService.getAllCategories());

        return "activityForm";
    }

    /*
     * Guardar actividad
     */
    @PostMapping("/saveActivity")
    public String saveActivity(
            Activity activity,
            @RequestParam("category.id") Long categoryId) {

        Category category =
                categoryService.getCategoryById(categoryId);

        activity.setCategory(category);

        activityService.saveActivity(activity);

        return "redirect:/activitiesPage";
    }

    /*
     * Editar una actividad
     */
    @GetMapping("/editActivity/{id}")
    public String editActivity(
            @PathVariable Long id,
            Model model) {

        Activity activity =
                activityService.getActivityById(id);

        model.addAttribute(
                "activity",
                activity);

        model.addAttribute(
                "categories",
                categoryService.getAllCategories());

        return "activityForm";
    }

    /*
     * Eliminar una actividad
     */
    @GetMapping("/deleteActivity/{id}")
    public String deleteActivity(
            @PathVariable Long id) {

        activityService.deleteActivity(id);

        return "redirect:/activitiesPage";
    }

}