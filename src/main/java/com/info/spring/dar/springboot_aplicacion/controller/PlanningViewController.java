package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.PlanningService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that handles the planning views and form submissions.
 * Manages the planning list, creation, editing, and deletion for the logged-in user.
 */
@Controller
public class PlanningViewController {

    private final PlanningService planningService;
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param planningService the service used to manage plannings
     * @param userService     the service used to get the current user
     */
    public PlanningViewController(PlanningService planningService, UserService userService) {
        this.planningService = planningService;
        this.userService = userService;
    }

    /**
     * Displays all plannings belonging to the currently logged-in user.
     * GET /planningsPage
     *
     * @param model the Spring MVC model
     * @return the plannings list view template
     */
    @GetMapping("/planningsPage")
    public String showPlannings(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("plannings", planningService.getPlanningsByUser(user));
        return "plannings";
    }

    /**
     * Displays the form to create a new planning.
     * GET /newPlanning
     *
     * @param model the Spring MVC model
     * @return the planning form template
     */
    @GetMapping("/newPlanning")
    public String showPlanningForm(Model model) {
        model.addAttribute("planning", new Planning());
        return "planningForm";
    }

    /**
     * Saves a new planning and assigns it to the current user.
     * POST /savePlanning
     *
     * @param planning the planning object bound from the form
     * @return redirect to the plannings page
     */
    @PostMapping("/savePlanning")
    public String savePlanning(Planning planning) {
        User user = userService.getCurrentUser();
        planning.setUser(user);
        planningService.savePlanning(planning);
        return "redirect:/planningsPage";
    }

    /**
     * Displays the form pre-filled with an existing planning's data for editing.
     * GET /editPlanning/{id}
     *
     * @param id    the ID of the planning to edit
     * @param model the Spring MVC model
     * @return the planning form template
     */
    @GetMapping("/editPlanning/{id}")
    public String editPlanning(@PathVariable Long id, Model model) {
        Planning planning = planningService.getPlanningById(id);
        model.addAttribute("planning", planning);
        return "planningForm";
    }

    /**
     * Deletes a planning by its ID.
     * GET /deletePlanning/{id}
     *
     * @param id the ID of the planning to delete
     * @return redirect to the plannings page
     */
    @GetMapping("/deletePlanning/{id}")
    public String deletePlanning(@PathVariable Long id) {
        planningService.deletePlanning(id);
        return "redirect:/planningsPage";
    }
}