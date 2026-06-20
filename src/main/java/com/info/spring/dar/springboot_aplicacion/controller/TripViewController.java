package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.service.TripService;

@Controller
public class TripViewController {

    // Servicio de viajes
    private final TripService tripService;

    // Constructor
    public TripViewController(TripService tripService) {
        this.tripService = tripService;
    }

    /*
     * Muestra todos los viajes
     */
    @GetMapping("/tripsPage")
    public String showTrips(Model model) {

        model.addAttribute(
                "trips",
                tripService.getAllTrips());

        return "trips";
    }

    /*
     * Muestra el formulario para crear un nuevo viaje
     */
    @GetMapping("/newTrip")
    public String showTripForm(Model model) {

        model.addAttribute(
                "trip",
                new Trip());

        return "tripForm";
    }

    /*
     * Guarda un viaje en MySQL
     */
    @PostMapping("/saveTrip")
    public String saveTrip(Trip trip) {

        tripService.saveTrip(trip);

        return "redirect:/tripsPage";
    }

    /*
     * Editar un viaje
     */
    @GetMapping("/editTrip/{id}")
    public String editTrip(
            @PathVariable Long id,
            Model model) {

        Trip trip =
                tripService.getTripById(id);

        model.addAttribute(
                "trip",
                trip);

        return "tripForm";
    }

    /*
     * Eliminar un viaje
     */
    @GetMapping("/deleteTrip/{id}")
    public String deleteTrip(
            @PathVariable Long id) {

        tripService.deleteTrip(id);

        return "redirect:/tripsPage";
    }

}