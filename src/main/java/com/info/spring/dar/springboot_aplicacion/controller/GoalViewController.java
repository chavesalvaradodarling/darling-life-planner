package com.info.spring.dar.springboot_aplicacion.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Goal;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;
import com.info.spring.dar.springboot_aplicacion.service.CategoryService;
import com.info.spring.dar.springboot_aplicacion.service.GoalService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that handles the goal views and form submissions.
 * Calculates progress for each goal based on the user's completed activities
 * and separates them into active and completed groups.
 */
@Controller
public class GoalViewController {

    private final GoalService goalService;
    private final UserService userService;
    private final ActivityService activityService;
    private final CategoryService categoryService;

    /**
     * Constructor with dependency injection.
     *
     * @param goalService     the service used to manage goals
     * @param userService     the service used to get the current user
     * @param activityService the service used to retrieve activities for progress calculation
     * @param categoryService the service used to load categories for the goal form
     */
    public GoalViewController(
            GoalService goalService,
            UserService userService,
            ActivityService activityService,
            CategoryService categoryService) {

        this.goalService = goalService;
        this.userService = userService;
        this.activityService = activityService;
        this.categoryService = categoryService;
    }

    /**
     * Displays the goals page for the current user.
     * Calculates the progress percentage for each goal based on completed activities,
     * then separates goals into active (under 100%) and completed (100% or more).
     * GET /goalsPage
     *
     * @param model the Spring MVC model
     * @return the goals page view template
     */
    @GetMapping("/goalsPage")
    public String showGoals(Model model) {

        User user = userService.getCurrentUser();
        List<Goal> goals = goalService.getGoalsByUser(user);
        List<Activity> activities = activityService.getActivitiesByUser(user);

        List<Goal> activeGoals = new ArrayList<>();
        List<Goal> completedGoals = new ArrayList<>();

        for (Goal goal : goals) {

            // Calculate and update progress based on real activity hours
            double percentage = goalService.calculateProgress(goal, activities);
            goalService.saveGoal(goal);

            if (percentage >= 100) {
                completedGoals.add(goal);
            } else {
                activeGoals.add(goal);
            }
        }

        model.addAttribute("activeGoals", activeGoals);
        model.addAttribute("completedGoals", completedGoals);

        return "goalsPage";
    }

    /**
     * Displays the form to create a new goal.
     * GET /newGoal
     *
     * @param model the Spring MVC model
     * @return the goal form template
     */
    @GetMapping("/newGoal")
    public String showGoalForm(Model model) {
        model.addAttribute("goal", new Goal());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "goalForm";
    }

    /**
     * Saves a new goal and assigns it to the current user.
     * Sets progress to 0.0 if not provided.
     * POST /saveGoal
     *
     * @param goal the goal object bound from the form
     * @return redirect to the goals page
     */
    @PostMapping("/saveGoal")
    public String saveGoal(Goal goal) {

        User user = userService.getCurrentUser();
        goal.setUser(user);

        if (goal.getProgress() == null) {
            goal.setProgress(0.0);
        }

        goalService.saveGoal(goal);

        return "redirect:/goalsPage";
    }

    /**
     * Displays the form pre-filled with an existing goal's data for editing.
     * GET /editGoal/{id}
     *
     * @param id    the ID of the goal to edit
     * @param model the Spring MVC model
     * @return the goal form template
     */
    @GetMapping("/editGoal/{id}")
    public String editGoal(@PathVariable Long id, Model model) {
        Goal goal = goalService.getGoalById(id);
        model.addAttribute("goal", goal);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "goalForm";
    }

    /**
     * Deletes a goal by its ID.
     * GET /deleteGoal/{id}
     *
     * @param id the ID of the goal to delete
     * @return redirect to the goals page
     */
    @GetMapping("/deleteGoal/{id}")
    public String deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return "redirect:/goalsPage";
    }
}