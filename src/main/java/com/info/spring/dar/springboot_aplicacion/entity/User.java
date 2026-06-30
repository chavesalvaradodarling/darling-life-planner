package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an authenticated user stored in the 'users' table.
 *
 * Each user has a unique email and an encoded password managed by Spring Security.
 * Users can own plannings, courses, movies, and trips.
 * The role field controls access level within the application.
 */
@Entity
@Table(name = "users")
public class User {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Display name of the user. */
    private String name;

    /**
     * Email address used for login.
     * Must be unique across all users.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * BCrypt-encoded password.
     * Never stored in plain text.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Role assigned to the user.
     * Possible values: ADMIN, USER
     */
    private String role;

    /**
     * List of plannings created by this user.
     * One user can have many plannings.
     */
    @OneToMany(mappedBy = "user")
    private List<Planning> plannings = new ArrayList<>();

    /**
     * List of courses created by this user.
     * One user can have many courses.
     */
    @OneToMany(mappedBy = "user")
    private List<Course> courses = new ArrayList<>();

    /**
     * List of movies added by this user.
     * One user can have many movies.
     */
    @OneToMany(mappedBy = "user")
    private List<Movie> movies = new ArrayList<>();

    /**
     * List of trips created by this user.
     * One user can have many trips.
     */
    @OneToMany(mappedBy = "user")
    private List<Trip> trips = new ArrayList<>();

    /** No-argument constructor required by JPA. */
    public User() {
    }

    /**
     * Constructor with main fields.
     *
     * @param id       the user ID
     * @param name     the display name
     * @param email    the login email
     * @param password the BCrypt-encoded password
     * @param role     the user role (ADMIN or USER)
     */
    public User(Long id, String name, String email,
            String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<Planning> getPlannings() { return plannings; }
    public void setPlannings(List<Planning> plannings) { this.plannings = plannings; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    public List<Movie> getMovies() { return movies; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }

    public List<Trip> getTrips() { return trips; }
    public void setTrips(List<Trip> trips) { this.trips = trips; }
}