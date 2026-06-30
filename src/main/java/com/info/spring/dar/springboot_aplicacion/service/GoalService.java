package com.info.spring.dar.springboot_aplicacion.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Goal;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.GoalRepository;

/**
 * Service class that contains the business logic for managing goals.
 * Also provides automatic progress calculation based on completed activities.
 */
@Service
public class GoalService {

    /** Repository used to perform database operations on goals. */
    private final GoalRepository goalRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param goalRepository the repository used to access the goals table
     */
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    /**
     * Saves a goal to the database.
     *
     * @param goal the goal to save
     * @return the saved goal
     */
    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    /**
     * Returns all goals in the system.
     *
     * @return list of all goals
     */
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    /**
     * Returns a single goal by its ID, or null if not found.
     *
     * @param id the goal ID
     * @return the matching goal or null
     */
    public Goal getGoalById(Long id) {
        return goalRepository.findById(id).orElse(null);
    }

    /**
     * Returns all goals belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of goals for that user
     */
    public List<Goal> getGoalsByUser(User user) {
        return goalRepository.findByUser(user);
    }

    /**
     * Returns all goals matching the given title.
     *
     * @param title the title to search for
     * @return list of matching goals
     */
    public List<Goal> getGoalsByTitle(String title) {
        return goalRepository.findByTitle(title);
    }

    /**
     * Deletes a goal by its ID.
     *
     * @param id the ID of the goal to delete
     */
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

    /**
     * Calculates the progress percentage for a goal based on the user's activities.
     * Only counts activities with status COMPLETED or PARTIALLY_COMPLETED
     * whose category name matches the goal's categoryName (case-insensitive).
     * Updates the goal's progress (real hours) and progressPercentage fields.
     * Caps the percentage at 100 and rounds to one decimal place.
     *
     * @param goal       the goal to calculate progress for
     * @param activities the full list of the user's activities
     * @return the calculated progress percentage (0–100)
     */
    public double calculateProgress(Goal goal, List<Activity> activities) {

        if (goal == null) {
            return 0;
        }

        if (goal.getTarget() == null || goal.getTarget() <= 0) {
            goal.setProgress(0.0);
            goal.setProgressPercentage(0.0);
            return 0;
        }

        double completedHours = 0;

        for (Activity activity : activities) {

            if (activity.getCategory() == null) continue;
            if (activity.getCategory().getName() == null) continue;
            if (goal.getCategoryName() == null) continue;

            boolean completed = "COMPLETED".equals(activity.getStatus())
                    || "PARTIALLY_COMPLETED".equals(activity.getStatus());

            if (!completed) continue;

            if (!activity.getCategory().getName()
                    .equalsIgnoreCase(goal.getCategoryName())) continue;

            completedHours += activity.getRealHours() != null
                    ? activity.getRealHours() : 0;
        }

        double percentage = (completedHours / goal.getTarget()) * 100;

        if (percentage > 100) {
            percentage = 100;
        }

        percentage = Math.round(percentage * 10.0) / 10.0;

        goal.setProgress(completedHours);
        goal.setProgressPercentage(percentage);

        return percentage;
    }
}