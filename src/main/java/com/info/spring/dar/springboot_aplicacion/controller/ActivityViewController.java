package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;

@Controller
public class ActivityViewController {

    // Servicio de actividades
    private final ActivityService activityService;

    // Constructor
    public ActivityViewController(ActivityService activityService) {
        this.activityService = activityService;
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

        return "activityForm";
    }

    /*
     * Guardar actividad
     */
    @PostMapping("/saveActivity")
    public String saveActivity(Activity activity) {

        activityService.saveActivity(activity);

        return "redirect:/activitiesPage";
    }

    /*
     * Editar una actividad
     */
    @GetMapping("/editActivity/{id}")
    public String editActivity(@PathVariable Long id,
            Model model) {

        Activity activity =
                activityService.getActivityById(id);

        model.addAttribute(
                "activity",
                activity);

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
