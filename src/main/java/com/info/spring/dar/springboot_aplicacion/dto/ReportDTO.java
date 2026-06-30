package com.info.spring.dar.springboot_aplicacion.dto;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Data Transfer Object used to carry the activity report data
 * to the reports view.
 *
 * Contains totals, completion percentage, and a breakdown
 * of real hours logged per category.
 */
public class ReportDTO {

    /** Total number of activities for the current user. */
    private int totalActivities;

    /** Number of activities with status COMPLETED. */
    private int completedActivities;

    /** Number of activities not yet completed. */
    private int pendingActivities;

    /**
     * Map of category name to total real hours logged.
     * Only includes completed or partially completed activities.
     * Example: { "Estudiar" -> 10.0, "Música" -> 5.0 }
     */
    private Map<String, Double> hoursPerCategory = new LinkedHashMap<>();

    /** Percentage of activities that have been completed (0–100). */
    private double completionPercentage;

    /** No-argument constructor. */
    public ReportDTO() {
    }

    public int getTotalActivities() { return totalActivities; }
    public void setTotalActivities(int totalActivities) { this.totalActivities = totalActivities; }

    public int getCompletedActivities() { return completedActivities; }
    public void setCompletedActivities(int completedActivities) { this.completedActivities = completedActivities; }

    public int getPendingActivities() { return pendingActivities; }
    public void setPendingActivities(int pendingActivities) { this.pendingActivities = pendingActivities; }

    public Map<String, Double> getHoursPerCategory() { return hoursPerCategory; }
    public void setHoursPerCategory(Map<String, Double> hoursPerCategory) { this.hoursPerCategory = hoursPerCategory; }

    public double getCompletionPercentage() { return completionPercentage; }
    public void setCompletionPercentage(double completionPercentage) { this.completionPercentage = completionPercentage; }
}