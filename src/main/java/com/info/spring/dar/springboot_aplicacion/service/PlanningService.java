package com.info.spring.dar.springboot_aplicacion.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.PlanningRepository;

/**
 * Service class that contains the business logic for managing plannings.
 * Communicates with PlanningRepository to access the database.
 */
@Service
public class PlanningService {

    /** Repository used to perform database operations on plannings. */
    private final PlanningRepository planningRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param planningRepository the repository used to access the planning table
     */
    public PlanningService(PlanningRepository planningRepository) {
        this.planningRepository = planningRepository;
    }

    /**
     * Saves a planning to the database.
     *
     * @param planning the planning to save
     * @return the saved planning
     */
    public Planning savePlanning(Planning planning) {
        return planningRepository.save(planning);
    }

    /**
     * Returns all plannings in the system.
     *
     * @return list of all plannings
     */
    public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

    /**
     * Returns a single planning by its ID, or null if not found.
     *
     * @param id the planning ID
     * @return the matching planning or null
     */
    public Planning getPlanningById(Long id) {
        return planningRepository.findById(id).orElse(null);
    }

    /**
     * Returns all plannings belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of plannings for that user
     */
    public List<Planning> getPlanningsByUser(User user) {
        return planningRepository.findByUser(user);
    }

    /**
     * Deletes a planning by its ID.
     *
     * @param id the ID of the planning to delete
     */
    public void deletePlanning(Long id) {
        planningRepository.deleteById(id);
    }
}