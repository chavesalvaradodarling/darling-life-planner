package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;

/**
 * Entity representing a user activity stored in the 'activities' table.
 *
 * An activity is the core unit of the planner. It can optionally be linked
 * to a planning, category, course, movie, or trip.
 * Every activity belongs to exactly one user.
 */
@Entity
@Table(name = "activities")
public class Activity {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Title or name of the activity. */
    private String title;

    /** Optional description of what the activity involves. */
    private String description;

    /** Date the activity is scheduled for (format: yyyy-MM-dd). */
    private String activityDate;

    /** Time the activity starts (format: HH:mm). */
    private String startTime;

    /** Time the activity ends (format: HH:mm). */
    private String endTime;

    /** Number of hours the user planned to spend on this activity. */
    private Double plannedHours;

    /** Number of hours the user actually spent on this activity. */
    private Double realHours;

    /**
     * Current status of the activity.
     * Possible values: PENDING, COMPLETED, PARTIALLY_COMPLETED, NOT_COMPLETED.
     */
    private String status;

    /**
     * Priority level of the activity.
     * Possible values: LOW, MEDIUM, HIGH.
     */
    private String priority;

    /** Optional note describing why the activity was not completed. */
    private String pendingDescription;

    /**
     * The planning this activity belongs to (optional).
     * Many activities can belong to one planning.
     */
    @ManyToOne
    @JoinColumn(name = "planning_id")
    private Planning planning;

    /**
     * The category this activity belongs to (required).
     * Many activities can belong to one category.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * The course this activity is associated with (optional).
     * Many activities can be linked to one course.
     */
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     * The movie this activity is associated with (optional).
     * Many activities can be linked to one movie.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    /**
     * The trip this activity is associated with (optional).
     * Many activities can be linked to one trip.
     */
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    /**
     * The user this activity belongs to.
     * Many activities can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** No-argument constructor required by JPA. */
    public Activity() {
    }

    /**
     * Full constructor.
     *
     * @param id                 the activity ID
     * @param title              the activity title
     * @param description        optional description
     * @param activityDate       scheduled date (yyyy-MM-dd)
     * @param startTime          start time (HH:mm)
     * @param endTime            end time (HH:mm)
     * @param plannedHours       planned hours
     * @param realHours          actual hours spent
     * @param status             current status
     * @param priority           priority level
     * @param pendingDescription note if not completed
     * @param planning           associated planning
     * @param category           associated category
     * @param course             associated course
     * @param movie              associated movie
     * @param trip               associated trip
     * @param user               owner of this activity
     */
    public Activity(
            Long id, String title, String description,
            String activityDate, String startTime, String endTime,
            Double plannedHours, Double realHours,
            String status, String priority, String pendingDescription,
            Planning planning, Category category,
            Course course, Movie movie, Trip trip, User user) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.activityDate = activityDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.plannedHours = plannedHours;
        this.realHours = realHours;
        this.status = status;
        this.priority = priority;
        this.pendingDescription = pendingDescription;
        this.planning = planning;
        this.category = category;
        this.course = course;
        this.movie = movie;
        this.trip = trip;
        this.user = user;
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

    public String getPendingDescription() { return pendingDescription; }
    public void setPendingDescription(String pendingDescription) { this.pendingDescription = pendingDescription; }

    public Planning getPlanning() { return planning; }
    public void setPlanning(Planning planning) { this.planning = planning; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}