package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;

/**
 * REST controller that exposes API endpoints for managing activities.
 * Base URL: /activities
 */
@RestController
@RequestMapping("/activities")
public class ActivityController {

    /** Service that handles all activity-related business logic. */
    private final ActivityService activityService;

    /**
     * Constructor with dependency injection.
     *
     * @param activityService the service used to manage activities
     */
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * Returns all activities in the system.
     * GET /activities
     *
     * @return list of all activities
     */
    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    /**
     * Returns a single activity by its ID.
     * GET /activities/{id}
     *
     * @param id the activity ID
     * @return the matching activity, or null if not found
     */
    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }

    /**
     * Returns all activities associated with a given planning.
     * GET /activities/planning
     *
     * @param planning the planning object sent in the request body
     * @return list of matching activities
     */
    @GetMapping("/planning")
    public List<Activity> getActivitiesByPlanning(@RequestBody Planning planning) {
        return activityService.getActivitiesByPlanning(planning);
    }

    /**
     * Returns all activities associated with a given category.
     * GET /activities/category
     *
     * @param category the category object sent in the request body
     * @return list of matching activities
     */
    @GetMapping("/category")
    public List<Activity> getActivitiesByCategory(@RequestBody Category category) {
        return activityService.getActivitiesByCategory(category);
    }

    /**
     * Returns all activities associated with a given course.
     * GET /activities/course
     *
     * @param course the course object sent in the request body
     * @return list of matching activities
     */
    @GetMapping("/course")
    public List<Activity> getActivitiesByCourse(@RequestBody Course course) {
        return activityService.getActivitiesByCourse(course);
    }

    /**
     * Returns all activities with a specific status.
     * GET /activities/status/{status}
     *
     * @param status the status string (e.g. PENDING, COMPLETED)
     * @return list of activities with the given status
     */
    @GetMapping("/status/{status}")
    public List<Activity> getActivitiesByStatus(@PathVariable String status) {
        return activityService.getActivitiesByStatus(status);
    }

    /**
     * Returns all activities that are still pending (not completed).
     * GET /activities/pending
     *
     * @return list of pending activities
     */
    @GetMapping("/pending")
    public List<Activity> getPendingActivities() {
        return activityService.getPendingActivities();
    }

    /**
     * Saves a new activity.
     * POST /activities
     *
     * @param activity the activity object received in the request body
     * @return the saved activity
     */
    @PostMapping
    public Activity saveActivity(@RequestBody Activity activity) {
        return activityService.saveActivity(activity);
    }

    /**
     * Marks an activity as completed without deleting it.
     * PUT /activities/complete/{id}
     *
     * @param id the ID of the activity to complete
     */
    @PutMapping("/complete/{id}")
    public void completeActivity(@PathVariable Long id) {
        activityService.completeActivity(id);
    }

    /**
     * Deletes an activity by its ID.
     * DELETE /activities/{id}
     *
     * @param id the ID of the activity to delete
     */
    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
}