package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an academic course stored in the 'courses' table.
 *
 * Courses are created by the user and can optionally be associated
 * with activities. Each course belongs to one user.
 */
@Entity
@Table(name = "courses")
public class Course {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the course.
     * Examples: "Cálculo I", "Programación III"
     */
    private String name;

    /**
     * Course code assigned by the institution.
     * Examples: "INF-101", "MAT-201"
     */
    private String code;

    /** Number of academic credits this course is worth. */
    private Integer credits;

    /** Name of the professor teaching this course. */
    private String teacher;

    /**
     * The user who created this course.
     * Many courses can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * List of activities associated with this course.
     * One course can be linked to many activities.
     */
    @OneToMany(mappedBy = "course")
    private List<Activity> activities = new ArrayList<>();

    /** No-argument constructor required by JPA. */
    public Course() {
    }

    /**
     * Constructor with main fields.
     *
     * @param id      the course ID
     * @param name    the course name
     * @param code    the course code
     * @param credits the number of credits
     * @param teacher the professor's name
     * @param user    the owner of this course
     */
    public Course(Long id, String name, String code,
            Integer credits, String teacher, User user) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.teacher = teacher;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Activity> getActivities() { return activities; }
    public void setActivities(List<Activity> activities) { this.activities = activities; }
}