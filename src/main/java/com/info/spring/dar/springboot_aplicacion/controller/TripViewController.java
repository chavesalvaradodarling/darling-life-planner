package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.TripService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that handles the trip views and form submissions.
 * Manages the trip list, creation, editing, and deletion for the logged-in user.
 */
@Controller
public class TripViewController {

    private final TripService tripService;
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param tripService the service used to manage trips
     * @param userService the service used to get the current user
     */
    public TripViewController(TripService tripService, UserService userService) {
        this.tripService = tripService;
        this.userService = userService;
    }

    /**
     * Displays all trips belonging to the currently logged-in user.
     * GET /tripsPage
     *
     * @param model the Spring MVC model
     * @return the trips list view template
     */
    @GetMapping("/tripsPage")
    public String showTrips(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("trips", tripService.getTripsByUser(user));
        return "trips";
    }

    /**
     * Displays the form to create a new trip.
     * GET /newTrip
     *
     * @param model the Spring MVC model
     * @return the trip form template
     */
    @GetMapping("/newTrip")
    public String showTripForm(Model model) {
        model.addAttribute("trip", new Trip());
        return "tripForm";
    }

    /**
     * Saves a new trip and assigns it to the current user.
     * POST /saveTrip
     *
     * @param trip the trip object bound from the form
     * @return redirect to the trips page
     */
    @PostMapping("/saveTrip")
    public String saveTrip(Trip trip) {
        User user = userService.getCurrentUser();
        trip.setUser(user);
        tripService.saveTrip(trip);
        return "redirect:/tripsPage";
    }

    /**
     * Displays the form pre-filled with an existing trip's data for editing.
     * GET /editTrip/{id}
     *
     * @param id    the ID of the trip to edit
     * @param model the Spring MVC model
     * @return the trip form template
     */
    @GetMapping("/editTrip/{id}")
    public String editTrip(@PathVariable Long id, Model model) {
        Trip trip = tripService.getTripById(id);
        model.addAttribute("trip", trip);
        return "tripForm";
    }

    /**
     * Deletes a trip by its ID.
     * GET /deleteTrip/{id}
     *
     * @param id the ID of the trip to delete
     * @return redirect to the trips page
     */
    @GetMapping("/deleteTrip/{id}")
    public String deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return "redirect:/tripsPage";
    }
}