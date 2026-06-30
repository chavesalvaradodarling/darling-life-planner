package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;

/**
 * Entity representing a user goal stored in the 'goals' table.
 *
 * A goal tracks progress toward a target number of hours for a given category.
 * Progress is calculated automatically based on completed activities.
 * Each goal belongs to one user.
 */
@Entity
@Table(name = "goals")
public class Goal {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title or description of the goal.
     * Example: "Study 50 hours this semester"
     */
    private String title;

    /**
     * Name of the category this goal is linked to.
     * Must match an existing category name created by the user.
     * Example: "Bailar", "Estudiar"
     */
    private String categoryName;

    /** Target number of hours the user wants to reach. */
    private Double target;

    /**
     * Number of real hours completed so far.
     * Calculated automatically from completed or partially completed activities.
     */
    private Double progress;

    /**
     * Progress as a percentage (0–100).
     * Calculated as: (progress / target) * 100, capped at 100.
     */
    private Double progressPercentage;

    /**
     * The user this goal belongs to.
     * Many goals can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** No-argument constructor required by JPA. */
    public Goal() {
    }

    /**
     * Full constructor.
     *
     * @param id                 the goal ID
     * @param title              the goal title
     * @param categoryName       the category name to track
     * @param target             the target number of hours
     * @param progress           the real hours completed so far
     * @param progressPercentage the completion percentage
     * @param user               the owner of this goal
     */
    public Goal(Long id, String title, String categoryName,
            Double target, Double progress,
            Double progressPercentage, User user) {
        this.id = id;
        this.title = title;
        this.categoryName = categoryName;
        this.target = target;
        this.progress = progress;
        this.progressPercentage = progressPercentage;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Double getTarget() { return target; }
    public void setTarget(Double target) { this.target = target; }

    public Double getProgress() { return progress; }
    public void setProgress(Double progress) { this.progress = progress; }

    public Double getProgressPercentage() { return progressPercentage; }
    public void setProgressPercentage(Double progressPercentage) { this.progressPercentage = progressPercentage; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}