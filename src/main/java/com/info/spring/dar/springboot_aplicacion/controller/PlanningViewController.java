package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.service.PlanningService;

@Controller
public class PlanningViewController {

    // Servicio de planificaciones
    private final PlanningService planningService;

    // Constructor
    public PlanningViewController(PlanningService planningService) {
        this.planningService = planningService;
    }

    /*
     * Muestra todas las planificaciones
     */
    @GetMapping("/planningsPage")
    public String showPlannings(Model model) {

        model.addAttribute(
                "plannings",
                planningService.getAllPlannings());

        return "plannings";
    }

    /*
     * Muestra el formulario para crear una nueva planificación
     */
    @GetMapping("/newPlanning")
    public String showPlanningForm(Model model) {

        model.addAttribute(
                "planning",
                new Planning());

        return "planningForm";
    }

    /*
     * Guarda una planificación en MySQL
     */
    @PostMapping("/savePlanning")
    public String savePlanning(Planning planning) {

        planningService.savePlanning(planning);

        return "redirect:/planningsPage";
    }

    /*
     * Editar una planificación
     */
    @GetMapping("/editPlanning/{id}")
    public String editPlanning(
            @PathVariable Long id,
            Model model) {

        Planning planning =
                planningService.getPlanningById(id);

        model.addAttribute(
                "planning",
                planning);

        return "planningForm";
    }

    /*
     * Eliminar una planificación
     */
    @GetMapping("/deletePlanning/{id}")
    public String deletePlanning(
            @PathVariable Long id) {

        planningService.deletePlanning(id);

        return "redirect:/planningsPage";
    }

}