package com.info.spring.dar.springboot_aplicacion.controller;

// Importa las anotaciones necesarias de Spring Boot
import org.springframework.web.bind.annotation.*;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el servicio
import com.info.spring.dar.springboot_aplicacion.service.TripService;

/*
 * Esta clase se encargará de recibir las peticiones HTTP
 * relacionadas con los viajes.
 */
@RestController

// Ruta principal para los viajes
@RequestMapping("/trips")
public class TripController {

    // Objeto que permitirá acceder a la lógica del servicio
    private final TripService tripService;

    /*
     * Constructor con inyección de dependencias.
     */
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    /*
     * GET
     *
     * Devuelve todos los viajes.
     *
     * URL:
     * http://localhost:8080/trips
     */
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    /*
     * GET
     *
     * Devuelve un viaje según su id.
     *
     * Ejemplo:
     * http://localhost:8080/trips/1
     */
    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    /*
     * GET
     *
     * Devuelve todos los viajes pertenecientes
     * a un usuario.
     */
    @GetMapping("/user")
    public List<Trip> getTripsByUser(@RequestBody User user) {
        return tripService.getTripsByUser(user);
    }

    /*
     * GET
     *
     * Busca viajes por destino.
     *
     * Ejemplo:
     * http://localhost:8080/trips/destination/Japon
     */
    @GetMapping("/destination/{destination}")
    public List<Trip> getTripsByDestination(@PathVariable String destination) {
        return tripService.getTripsByDestination(destination);
    }

    /*
     * POST
     *
     * Guarda un nuevo viaje.
     */
    @PostMapping
    public Trip saveTrip(@RequestBody Trip trip) {
        return tripService.saveTrip(trip);
    }

    /*
     * DELETE
     *
     * Elimina un viaje según su id.
     *
     * Ejemplo:
     * http://localhost:8080/trips/1
     */
    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

}