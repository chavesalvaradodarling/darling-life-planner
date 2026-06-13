package com.info.spring.dar.springboot_aplicacion.repository;

// Importa List para devolver varias películas
import java.util.List;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa la entidad Movie
import com.info.spring.dar.springboot_aplicacion.entity.Movie;

/*
 * Esta interfaz se encargará de acceder a la tabla movies
 * de la base de datos.
 *
 * Long indica que la llave primaria (id)
 * es de tipo Long.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

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
     * Permite buscar películas por título.
     *
     * Ejemplos:
     * Interstellar
     * Inception
     */
    List<Movie> findByTitle(String title);

    /*
     * Permite buscar películas por género.
     *
     * Ejemplos:
     * Ciencia ficción
     * Acción
     * Drama
     */
    List<Movie> findByGenre(String genre);

}