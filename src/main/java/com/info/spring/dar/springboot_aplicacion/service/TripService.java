package com.info.spring.dar.springboot_aplicacion.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.TripRepository;

/**
 * Service class that contains the business logic for managing trips.
 * Communicates with TripRepository to access the database.
 */
@Service
public class TripService {

    /** Repository used to perform database operations on trips. */
    private final TripRepository tripRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param tripRepository the repository used to access the trips table
     */
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    /**
     * Saves a trip to the database.
     *
     * @param trip the trip to save
     * @return the saved trip
     */
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    /**
     * Returns all trips in the system.
     *
     * @return list of all trips
     */
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    /**
     * Returns a single trip by its ID, or null if not found.
     *
     * @param id the trip ID
     * @return the matching trip or null
     */
    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    /**
     * Returns all trips belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of trips for that user
     */
    public List<Trip> getTripsByUser(User user) {
        return tripRepository.findByUser(user);
    }

    /**
     * Returns all trips matching the given destination.
     *
     * @param destination the destination to search for
     * @return list of matching trips
     */
    public List<Trip> getTripsByDestination(String destination) {
        return tripRepository.findByDestination(destination);
    }

    /**
     * Deletes a trip by its ID.
     *
     * @param id the ID of the trip to delete
     */
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
}