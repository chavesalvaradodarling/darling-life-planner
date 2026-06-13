package com.info.spring.dar.springboot_aplicacion.controller;

// Importa las anotaciones necesarias de Spring Boot
import org.springframework.web.bind.annotation.*;

// Permite trabajar con listas
import java.util.List;

// Importa la entidad Movie
import com.info.spring.dar.springboot_aplicacion.entity.Movie;

// Importa el servicio
import com.info.spring.dar.springboot_aplicacion.service.MovieService;

/*
 * Esta clase se encargará de recibir las peticiones HTTP
 * relacionadas con las películas.
 */
@RestController

// Ruta principal para las películas
@RequestMapping("/movies")
public class MovieController {

    // Objeto que permitirá acceder a la lógica del servicio
    private final MovieService movieService;

    /*
     * Constructor con inyección de dependencias.
     */
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /*
     * GET
     *
     * Devuelve todas las películas.
     *
     * URL:
     * http://localhost:8080/movies
     */
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /*
     * GET
     *
     * Devuelve una película según su id.
     *
     * Ejemplo:
     * http://localhost:8080/movies/1
     */
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    /*
     * GET
     *
     * Busca películas por título.
     *
     * Ejemplo:
     * http://localhost:8080/movies/title/Interstellar
     */
    @GetMapping("/title/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable String title) {
        return movieService.getMoviesByTitle(title);
    }

    /*
     * GET
     *
     * Busca películas por género.
     *
     * Ejemplo:
     * http://localhost:8080/movies/genre/Ciencia ficcion
     */
    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    /*
     * POST
     *
     * Guarda una nueva película.
     */
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    /*
     * DELETE
     *
     * Elimina una película según su id.
     *
     * Ejemplo:
     * http://localhost:8080/movies/1
     */
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

}