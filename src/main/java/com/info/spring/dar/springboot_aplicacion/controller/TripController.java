package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.TripService;

/**
 * REST controller that exposes API endpoints for managing trips.
 * Base URL: /trips
 */
@RestController
@RequestMapping("/trips")
public class TripController {

    /** Service that handles all trip-related business logic. */
    private final TripService tripService;

    /**
     * Constructor with dependency injection.
     *
     * @param tripService the service used to manage trips
     */
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    /**
     * Returns all trips in the system.
     * GET /trips
     *
     * @return list of all trips
     */
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    /**
     * Returns a single trip by its ID.
     * GET /trips/{id}
     *
     * @param id the trip ID
     * @return the matching trip, or null if not found
     */
    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    /**
     * Returns all trips belonging to a specific user.
     * GET /trips/user
     *
     * @param user the user object sent in the request body
     * @return list of trips for that user
     */
    @GetMapping("/user")
    public List<Trip> getTripsByUser(@RequestBody User user) {
        return tripService.getTripsByUser(user);
    }

    /**
     * Returns all trips matching the given destination.
     * GET /trips/destination/{destination}
     *
     * @param destination the destination to search for
     * @return list of matching trips
     */
    @GetMapping("/destination/{destination}")
    public List<Trip> getTripsByDestination(@PathVariable String destination) {
        return tripService.getTripsByDestination(destination);
    }

    /**
     * Saves a new trip.
     * POST /trips
     *
     * @param trip the trip object received in the request body
     * @return the saved trip
     */
    @PostMapping
    public Trip saveTrip(@RequestBody Trip trip) {
        return tripService.saveTrip(trip);
    }

    /**
     * Deletes a trip by its ID.
     * DELETE /trips/{id}
     *
     * @param id the ID of the trip to delete
     */
    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }
}