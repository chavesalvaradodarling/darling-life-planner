package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.TripRepository;

/*
 * Esta clase contiene la lógica relacionada con los viajes.
 *
 * Se comunica con TripRepository para acceder a la base de datos.
 */
@Service
public class TripService {

    // Objeto que permite acceder a la tabla trips
    private final TripRepository tripRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    /*
     * Guarda un viaje en la base de datos.
     */
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    /*
     * Devuelve todos los viajes registrados.
     */
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    /*
     * Busca un viaje por su id.
     *
     * Si no existe, devuelve null.
     */
    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    /*
     * Devuelve todos los viajes
     * pertenecientes a un usuario.
     */
    public List<Trip> getTripsByUser(User user) {
        return tripRepository.findByUser(user);
    }

    /*
     * Busca viajes por destino.
     *
     * Ejemplos:
     * París
     * Japón
     * Costa Rica
     */
    public List<Trip> getTripsByDestination(String destination) {
        return tripRepository.findByDestination(destination);
    }

    /*
     * Elimina un viaje por su id.
     */
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

}