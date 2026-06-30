package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a planning period stored in the 'planning' table.
 *
 * A planning groups activities over a defined time period.
 * It has a type (WEEKLY, MONTHLY, BIMONTHLY, SEMESTER), a start and end date,
 * and can contain many activities. Each planning belongs to one user.
 */
@Entity
@Table(name = "planning")
public class Planning {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the planning period.
     * Examples: "Semestre I 2027", "Semana de Exámenes"
     */
    private String name;

    /** Optional description of what this planning covers. */
    private String description;

    /** Main goal or objective for this planning period. */
    private String goal;

    /**
     * Type of planning period.
     * Possible values: WEEKLY, MONTHLY, BIMONTHLY, SEMESTER
     */
    private String type;

    /** Start date of the planning period (format: yyyy-MM-dd). */
    private String startDate;

    /** End date of the planning period (format: yyyy-MM-dd). */
    private String endDate;

    /**
     * The user this planning belongs to.
     * Many plannings can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * List of activities included in this planning.
     * One planning can contain many activities.
     */
    @OneToMany(mappedBy = "planning")
    private List<Activity> activities = new ArrayList<>();

    /** No-argument constructor required by JPA. */
    public Planning() {
    }

    /**
     * Constructor with main fields.
     *
     * @param id          the planning ID
     * @param name        the planning name
     * @param description optional description
     * @param goal        the main objective
     * @param type        the planning type (WEEKLY, MONTHLY, etc.)
     * @param startDate   the start date
     * @param endDate     the end date
     * @param user        the owner of this planning
     */
    public Planning(Long id, String name, String description,
            String goal, String type, String startDate,
            String endDate, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Activity> getActivities() { return activities; }
    public void setActivities(List<Activity> activities) { this.activities = activities; }
}
