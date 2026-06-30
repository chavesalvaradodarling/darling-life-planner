package com.info.spring.dar.springboot_aplicacion.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.info.spring.dar.springboot_aplicacion.dto.ReportDTO;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Service class that generates the activity report for the current user.
 * Calculates totals, completion percentage, and hours grouped by category.
 */
@Service
public class ReportService {

    private final ActivityService activityService;
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param activityService the service used to retrieve user activities
     * @param userService     the service used to get the currently logged-in user
     */
    public ReportService(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    /**
     * Generates a report for the currently logged-in user.
     * Counts total, completed, and pending activities.
     * Sums real hours per category for completed or partially completed activities.
     * Calculates the overall completion percentage.
     *
     * @return a ReportDTO containing all report data
     */
    public ReportDTO generateReport() {

        User user = userService.getCurrentUser();

        // Retrieve only the current user's activities
        List<Activity> activities = activityService.getActivitiesByUser(user);

        ReportDTO report = new ReportDTO();

        int totalActivities = activities.size();
        int completedActivities = 0;
        int pendingActivities = 0;

        Map<String, Double> hoursPerCategory = new LinkedHashMap<>();

        for (Activity activity : activities) {

            // Count completed vs pending
            if ("COMPLETED".equals(activity.getStatus())) {
                completedActivities++;
            } else {
                pendingActivities++;
            }

            // Sum real hours only for completed or partially completed activities
            if (activity.getCategory() != null &&
                ("COMPLETED".equals(activity.getStatus())
                || "PARTIALLY_COMPLETED".equals(activity.getStatus()))) {

                String categoryName = activity.getCategory().getName();
                double hours = activity.getRealHours() != null ? activity.getRealHours() : 0.0;

                hoursPerCategory.put(
                        categoryName,
                        hoursPerCategory.getOrDefault(categoryName, 0.0) + hours);
            }
        }

        // Calculate completion percentage
        double completionPercentage = 0;
        if (totalActivities > 0) {
            completionPercentage = (completedActivities * 100.0) / totalActivities;
        }

        report.setTotalActivities(totalActivities);
        report.setCompletedActivities(completedActivities);
        report.setPendingActivities(pendingActivities);
        report.setHoursPerCategory(hoursPerCategory);
        report.setCompletionPercentage(completionPercentage);

        return report;
    }
}