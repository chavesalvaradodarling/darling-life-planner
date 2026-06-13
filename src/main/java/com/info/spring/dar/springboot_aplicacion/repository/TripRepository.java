package com.info.spring.dar.springboot_aplicacion.repository;

// Importa List para devolver varios viajes
import java.util.List;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/*
 * Esta interfaz se encargará de acceder a la tabla trips
 * de la base de datos.
 *
 * Long indica que la llave primaria (id)
 * es de tipo Long.
 */
public interface TripRepository extends JpaRepository<Trip, Long> {

    /*
     * JpaRepository ya proporciona automáticamente:
     *
     * save()
     * findById()
     * findAll()
     * deleteById()
     * existsById()
     */

    /*
     * Devuelve todos los viajes
     * pertenecientes a un usuario.
     */
    List<Trip> findByUser(User user);

    /*
     * Permite buscar viajes por destino.
     *
     * Ejemplos:
     * París
     * Japón
     * Costa Rica
     */
    List<Trip> findByDestination(String destination);

}