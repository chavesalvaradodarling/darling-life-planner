package com.info.spring.dar.springboot_aplicacion.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.info.spring.dar.springboot_aplicacion.dto.CategoryCardDTO;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that handles the home/dashboard page.
 * Calculates hours per category from completed or partially completed activities
 * and builds the data needed for the statistics cards and the pie chart.
 */
@Controller
public class IndexController {

    private final ActivityService activityService;
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param activityService the service used to retrieve the user's activities
     * @param userService     the service used to get the current user
     */
    public IndexController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    /**
     * Builds the home dashboard and sends all required data to the view.
     * Iterates over the current user's activities and groups real hours
     * by category, only counting completed or partially completed activities.
     * Also builds a list of CategoryCardDTO objects used to render the
     * colored category cards with their icon, name, and total hours.
     * GET /
     *
     * @param model the Spring MVC model
     * @return the index view template
     */
    @GetMapping("/")
    public String index(Model model) {

        User user = userService.getCurrentUser();
        List<Activity> activities = activityService.getActivitiesByUser(user);

        // Map of category name -> total real hours (used for the pie chart)
        Map<String, Double> hoursPerCategory = new LinkedHashMap<>();

        // Map of category name -> full card data (used for the colored stat cards)
        Map<String, CategoryCardDTO> categoryCardsMap = new LinkedHashMap<>();

        for (Activity activity : activities) {

            // Only count completed or partially completed activities
            if (!"COMPLETED".equals(activity.getStatus())
                    && !"PARTIALLY_COMPLETED".equals(activity.getStatus())) {
                continue;
            }

            if (activity.getCategory() == null) {
                continue;
            }

            double hours = activity.getRealHours() != null ? activity.getRealHours() : 0.0;
            String categoryName = activity.getCategory().getName();

            // Accumulate hours for the chart
            hoursPerCategory.put(
                    categoryName,
                    hoursPerCategory.getOrDefault(categoryName, 0.0) + hours);

            // Build or update the category card
            if (!categoryCardsMap.containsKey(categoryName)) {
                categoryCardsMap.put(
                        categoryName,
                        new CategoryCardDTO(
                                categoryName,
                                hours,
                                activity.getCategory().getColor(),
                                activity.getCategory().getIcon()));
            } else {
                CategoryCardDTO card = categoryCardsMap.get(categoryName);
                card.setHours(card.getHours() + hours);
            }
        }

        List<CategoryCardDTO> categoryCards = new ArrayList<>(categoryCardsMap.values());
        List<String> categoryLabels = new ArrayList<>(hoursPerCategory.keySet());
        List<Double> categoryValues = new ArrayList<>(hoursPerCategory.values());

        model.addAttribute("categoryLabels", categoryLabels);
        model.addAttribute("categoryValues", categoryValues);
        model.addAttribute("hoursPerCategory", hoursPerCategory);
        model.addAttribute("categoryCards", categoryCards);
        model.addAttribute("totalActivities", activities.size());
        model.addAttribute("activities", activities);

        return "index";
    }
}