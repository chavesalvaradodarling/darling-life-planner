package com.info.spring.dar.springboot_aplicacion.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Movie;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.ActivityRepository;
import com.info.spring.dar.springboot_aplicacion.repository.MovieRepository;

/**
 * Service class that contains the business logic for managing movies.
 * Communicates with MovieRepository and ActivityRepository to access the database.
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActivityRepository activityRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param movieRepository    the repository used to access the movies table
     * @param activityRepository the repository used to disassociate activities before deletion
     */
    public MovieService(MovieRepository movieRepository,
            ActivityRepository activityRepository) {
        this.movieRepository = movieRepository;
        this.activityRepository = activityRepository;
    }

    /**
     * Saves a movie to the database.
     *
     * @param movie the movie to save
     * @return the saved movie
     */
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Returns all movies in the system.
     *
     * @return list of all movies
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Returns all movies belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of movies for that user
     */
    public List<Movie> getMoviesByUser(User user) {
        return movieRepository.findByUser(user);
    }

    /**
     * Returns a single movie by its ID, or null if not found.
     *
     * @param id the movie ID
     * @return the matching movie or null
     */
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    /**
     * Returns all movies matching the given title.
     *
     * @param title the title to search for
     * @return list of matching movies
     */
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    /**
     * Returns all movies matching the given genre.
     *
     * @param genre the genre to filter by
     * @return list of matching movies
     */
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    /**
     * Deletes a movie by its ID.
     * Before deleting, sets the movie field to null on all associated activities
     * to avoid a foreign key constraint violation.
     *
     * @param id the ID of the movie to delete
     */
    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        if (movie == null) {
            return;
        }
        List<Activity> activities = activityRepository.findByMovie(movie);
        for (Activity activity : activities) {
            activity.setMovie(null);
        }
        activityRepository.saveAll(activities);
        movieRepository.deleteById(id);
    }
}