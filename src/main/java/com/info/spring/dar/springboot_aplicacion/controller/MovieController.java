package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Movie;
import com.info.spring.dar.springboot_aplicacion.service.MovieService;

/**
 * REST controller that exposes API endpoints for managing movies.
 * Base URL: /movies
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    /** Service that handles all movie-related business logic. */
    private final MovieService movieService;

    /**
     * Constructor with dependency injection.
     *
     * @param movieService the service used to manage movies
     */
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Returns all movies in the system.
     * GET /movies
     *
     * @return list of all movies
     */
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /**
     * Returns a single movie by its ID.
     * GET /movies/{id}
     *
     * @param id the movie ID
     * @return the matching movie, or null if not found
     */
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    /**
     * Returns all movies matching the given title.
     * GET /movies/title/{title}
     *
     * @param title the movie title to search for
     * @return list of matching movies
     */
    @GetMapping("/title/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable String title) {
        return movieService.getMoviesByTitle(title);
    }

    /**
     * Returns all movies matching the given genre.
     * GET /movies/genre/{genre}
     *
     * @param genre the genre to filter by
     * @return list of matching movies
     */
    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    /**
     * Saves a new movie.
     * POST /movies
     *
     * @param movie the movie object received in the request body
     * @return the saved movie
     */
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    /**
     * Deletes a movie by its ID.
     * DELETE /movies/{id}
     *
     * @param id the ID of the movie to delete
     */
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}