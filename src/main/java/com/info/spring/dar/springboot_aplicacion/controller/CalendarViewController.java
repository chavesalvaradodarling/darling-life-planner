package com.info.spring.dar.springboot_aplicacion.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that builds and displays the weekly calendar view.
 * Maps each non-completed activity to a day-and-hour slot on the calendar grid.
 */
@Controller
public class CalendarViewController {

    private final ActivityService activityService;
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param activityService the service used to retrieve activities
     * @param userService     the service used to get the current user
     */
    public CalendarViewController(
            ActivityService activityService,
            UserService userService) {

        this.activityService = activityService;
        this.userService = userService;
    }

    /**
     * Builds the weekly calendar and sends it to the view.
     * Filters out completed activities and maps the remaining ones
     * to a Map using the key format "DAYOFWEEK_HH:00" (e.g. "MONDAY_09:00").
     * GET /calendar
     *
     * @param model the Spring MVC model
     * @return the calendar view template
     */
    @GetMapping("/calendar")
    public String showCalendar(Model model) {

        User user = userService.getCurrentUser();

        List<Activity> activities = activityService.getActivitiesByUser(user);

        // Remove completed activities from the calendar
        activities.removeIf(activity -> "COMPLETED".equals(activity.getStatus()));

        // Build the list of hour slots from 05:00 to 24:00
        List<String> hours = new ArrayList<>();
        for (int hour = 5; hour <= 24; hour++) {
            if (hour == 24) {
                hours.add("24:00");
            } else {
                hours.add(String.format("%02d:00", hour));
            }
        }

        // Map each activity to its calendar cell key (e.g. "MONDAY_09:00")
        Map<String, Activity> calendarActivities = new HashMap<>();

        for (Activity activity : activities) {

            // Skip activities without a date or start time
            if (activity.getActivityDate() == null || activity.getActivityDate().isBlank()) {
                continue;
            }
            if (activity.getStartTime() == null || activity.getStartTime().isBlank()) {
                continue;
            }

            try {
                LocalDate date = LocalDate.parse(activity.getActivityDate());
                DayOfWeek day = date.getDayOfWeek();
                String hour = activity.getStartTime().substring(0, 2) + ":00";
                String key = day + "_" + hour;
                calendarActivities.put(key, activity);

            } catch (Exception e) {
                System.out.println("Error processing activity: " + activity.getTitle());
                e.printStackTrace();
            }
        }

        model.addAttribute("hours", hours);
        model.addAttribute("calendarActivities", calendarActivities);

        return "calendar";
    }
}