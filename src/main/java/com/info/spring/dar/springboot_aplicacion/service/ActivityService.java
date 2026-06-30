package com.info.spring.dar.springboot_aplicacion.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.ActivityRepository;

/**
 * Service class that contains the business logic for managing activities.
 * Communicates with ActivityRepository to read and write to the database.
 */
@Service
public class ActivityService {

    /** Repository used to perform database operations on activities. */
    private final ActivityRepository activityRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param activityRepository the repository used to access the activities table
     */
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * Saves an activity to the database.
     * Sets the status to PENDING if no status is provided.
     *
     * @param activity the activity to save
     * @return the saved activity
     */
    public Activity saveActivity(Activity activity) {
        if (activity.getStatus() == null || activity.getStatus().isBlank()) {
            activity.setStatus("PENDING");
        }
        return activityRepository.save(activity);
    }

    /**
     * Returns all activities in the system.
     *
     * @return list of all activities
     */
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    /**
     * Returns all activities belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of activities for that user
     */
    public List<Activity> getActivitiesByUser(User user) {
        return activityRepository.findByUser(user);
    }

    /**
     * Returns all activities that are not yet completed.
     * Removes any activity with status COMPLETED from the full list.
     *
     * @return list of non-completed activities
     */
    public List<Activity> getPendingActivities() {
        List<Activity> activities = activityRepository.findAll();
        activities.removeIf(activity -> "COMPLETED".equals(activity.getStatus()));
        return activities;
    }

    /**
     * Returns a single activity by its ID, or null if not found.
     *
     * @param id the activity ID
     * @return the matching activity or null
     */
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    /**
     * Returns all activities linked to a specific planning.
     *
     * @param planning the planning to filter by
     * @return list of activities for that planning
     */
    public List<Activity> getActivitiesByPlanning(Planning planning) {
        return activityRepository.findByPlanning(planning);
    }

    /**
     * Returns all activities linked to a specific category.
     *
     * @param category the category to filter by
     * @return list of activities for that category
     */
    public List<Activity> getActivitiesByCategory(Category category) {
        return activityRepository.findByCategory(category);
    }

    /**
     * Returns all activities linked to a specific course.
     *
     * @param course the course to filter by
     * @return list of activities for that course
     */
    public List<Activity> getActivitiesByCourse(Course course) {
        return activityRepository.findByCourse(course);
    }

    /**
     * Returns all activities with a specific status.
     *
     * @param status the status to filter by (e.g. PENDING, COMPLETED)
     * @return list of matching activities
     */
    public List<Activity> getActivitiesByStatus(String status) {
        return activityRepository.findByStatus(status);
    }

    /**
     * Marks an activity as COMPLETED without deleting it.
     *
     * @param id the ID of the activity to complete
     */
    public void completeActivity(Long id) {
        Activity activity = getActivityById(id);
        if (activity != null) {
            activity.setStatus("COMPLETED");
            activityRepository.save(activity);
        }
    }

    /**
     * Deletes an activity by its ID.
     *
     * @param id the ID of the activity to delete
     */
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    /**
     * Finds an activity scheduled on a given day of the week at a given hour.
     * Parses the activityDate to extract the day of week and compares it
     * with the startTime truncated to the hour.
     *
     * @param day  the day of week in uppercase English (e.g. "MONDAY")
     * @param hour the hour slot in HH:00 format (e.g. "09:00")
     * @return the first matching activity, or null if none found
     */
    public Activity getActivityByDayAndHour(String day, String hour) {
        List<Activity> activities = activityRepository.findAll();
        for (Activity activity : activities) {
            try {
                LocalDate date = LocalDate.parse(activity.getActivityDate());
                String activityDay = date.getDayOfWeek().toString();
                String activityHour = activity.getStartTime().substring(0, 2) + ":00";
                if (activityDay.equals(day) && activityHour.equals(hour)) {
                    return activity;
                }
            } catch (Exception e) {
                // Skip activities with invalid or missing dates
            }
        }
        return null;
    }
}