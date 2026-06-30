package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.PlanningService;

/**
 * REST controller that exposes API endpoints for managing plannings.
 * Base URL: /plannings
 */
@RestController
@RequestMapping("/plannings")
public class PlanningController {

    /** Service that handles all planning-related business logic. */
    private final PlanningService planningService;

    /**
     * Constructor with dependency injection.
     *
     * @param planningService the service used to manage plannings
     */
    public PlanningController(PlanningService planningService) {
        this.planningService = planningService;
    }

    /**
     * Returns all plannings in the system.
     * GET /plannings
     *
     * @return list of all plannings
     */
    @GetMapping
    public List<Planning> getAllPlannings() {
        return planningService.getAllPlannings();
    }

    /**
     * Returns a single planning by its ID.
     * GET /plannings/{id}
     *
     * @param id the planning ID
     * @return the matching planning, or null if not found
     */
    @GetMapping("/{id}")
    public Planning getPlanningById(@PathVariable Long id) {
        return planningService.getPlanningById(id);
    }

    /**
     * Returns all plannings belonging to a specific user.
     * GET /plannings/user
     *
     * @param user the user object sent in the request body
     * @return list of plannings for that user
     */
    @GetMapping("/user")
    public List<Planning> getPlanningsByUser(@RequestBody User user) {
        return planningService.getPlanningsByUser(user);
    }

    /**
     * Saves a new planning.
     * POST /plannings
     *
     * @param planning the planning object received in the request body
     * @return the saved planning
     */
    @PostMapping
    public Planning savePlanning(@RequestBody Planning planning) {
        return planningService.savePlanning(planning);
    }

    /**
     * Deletes a planning by its ID.
     * DELETE /plannings/{id}
     *
     * @param id the ID of the planning to delete
     */
    @DeleteMapping("/{id}")
    public void deletePlanning(@PathVariable Long id) {
        planningService.deletePlanning(id);
    }
}