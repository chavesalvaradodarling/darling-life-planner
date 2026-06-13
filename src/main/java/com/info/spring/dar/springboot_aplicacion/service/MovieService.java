package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa la entidad Movie
import com.info.spring.dar.springboot_aplicacion.entity.Movie;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.MovieRepository;

/*
 * Esta clase contiene la lógica relacionada con las películas.
 *
 * Se comunica con MovieRepository para acceder a la base de datos.
 */
@Service
public class MovieService {

    // Objeto que permite acceder a la tabla movies
    private final MovieRepository movieRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /*
     * Guarda una película en la base de datos.
     */
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /*
     * Devuelve todas las películas registradas.
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /*
     * Busca una película por su id.
     *
     * Si no existe, devuelve null.
     */
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    /*
     * Busca películas por título.
     *
     * Ejemplo:
     * Interstellar
     * Inception
     */
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    /*
     * Busca películas por género.
     *
     * Ejemplo:
     * Acción
     * Drama
     * Ciencia ficción
     */
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    /*
     * Elimina una película por su id.
     */
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

}