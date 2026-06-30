package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a trip stored in the 'trips' table.
 *
 * Trips are created by the user and can optionally be associated
 * with activities. When an activity is linked to a trip and has no date set,
 * the trip's departure date is used automatically.
 * Each trip belongs to one user.
 */
@Entity
@Table(name = "trips")
public class Trip {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Destination of the trip.
     * Examples: "París", "Japón", "Costa Rica"
     */
    private String destination;

    /** Departure date of the trip (format: yyyy-MM-dd). */
    private String departureDate;

    /** Return date of the trip (format: yyyy-MM-dd). */
    private String returnDate;

    /**
     * The user who created this trip.
     * Many trips can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * List of activities associated with this trip.
     * One trip can be linked to many activities.
     */
    @OneToMany(mappedBy = "trip")
    private List<Activity> activities = new ArrayList<>();

    /** No-argument constructor required by JPA. */
    public Trip() {
    }

    /**
     * Full constructor.
     *
     * @param id            the trip ID
     * @param destination   the destination
     * @param departureDate the departure date (yyyy-MM-dd)
     * @param returnDate    the return date (yyyy-MM-dd)
     * @param user          the owner of this trip
     */
    public Trip(Long id, String destination,
            String departureDate, String returnDate, User user) {
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }

    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Activity> getActivities() { return activities; }
    public void setActivities(List<Activity> activities) { this.activities = activities; }
}