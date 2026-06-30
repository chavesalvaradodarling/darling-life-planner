package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the Trip entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 */
public interface TripRepository extends JpaRepository<Trip, Long> {

    /**
     * Returns all trips belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of trips for that user
     */
    List<Trip> findByUser(User user);

    /**
     * Returns all trips matching the given destination.
     * Examples: "París", "Japón", "Costa Rica"
     *
     * @param destination the destination to search for
     * @return list of matching trips
     */
    List<Trip> findByDestination(String destination);
}