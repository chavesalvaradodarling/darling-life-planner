package com.info.spring.dar.springboot_aplicacion.dto;

/**
 * Data Transfer Object used to carry activity form data
 * between the view layer and the controller.
 *
 * Holds the basic activity fields along with the IDs of
 * any optional related entities (planning, category, course, movie, trip).
 */
public class ActivityFormDTO {

    /** Unique identifier of the activity (null for new activities). */
    private Long id;

    /** Title of the activity. */
    private String title;

    /** Optional description of the activity. */
    private String description;

    /** Date the activity is scheduled for (format: yyyy-MM-dd). */
    private String activityDate;

    /** Start time of the activity (format: HH:mm). */
    private String startTime;

    /** End time of the activity (format: HH:mm). */
    private String endTime;

    /** Number of hours the user planned to spend on this activity. */
    private Double plannedHours;

    /** Number of hours the user actually spent on this activity. */
    private Double realHours;

    /** Current status of the activity (e.g. PENDING, COMPLETED). */
    private String status;

    /** Priority level of the activity (e.g. LOW, MEDIUM, HIGH). */
    private String priority;

    /** ID of the associated planning (optional). */
    private Long planningId;

    /** ID of the associated category (required). */
    private Long categoryId;

    /** ID of the associated course (optional). */
    private Long courseId;

    /** ID of the associated movie (optional). */
    private Long movieId;

    /** ID of the associated trip (optional). */
    private Long tripId;

    /** No-argument constructor required for form binding. */
    public ActivityFormDTO() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getActivityDate() { return activityDate; }
    public void setActivityDate(String activityDate) { this.activityDate = activityDate; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public Double getPlannedHours() { return plannedHours; }
    public void setPlannedHours(Double plannedHours) { this.plannedHours = plannedHours; }

    public Double getRealHours() { return realHours; }
    public void setRealHours(Double realHours) { this.realHours = realHours; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public Long getPlanningId() { return planningId; }
    public void setPlanningId(Long planningId) { this.planningId = planningId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }

    public Long getTripId() { return tripId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }
}