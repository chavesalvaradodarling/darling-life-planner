package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.info.spring.dar.springboot_aplicacion.entity.Movie;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.MovieService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that handles the movie views and form submissions.
 * Manages the movie list, creation, editing, and deletion for the logged-in user.
 */
@Controller
public class MovieViewController {

    private final MovieService movieService;
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param movieService the service used to manage movies
     * @param userService  the service used to get the current user
     */
    public MovieViewController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    /**
     * Displays all movies belonging to the currently logged-in user.
     * GET /moviesPage
     *
     * @param model the Spring MVC model
     * @return the movies list view template
     */
    @GetMapping("/moviesPage")
    public String showMovies(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("movies", movieService.getMoviesByUser(user));
        return "movies";
    }

    /**
     * Displays the form to create a new movie.
     * GET /newMovie
     *
     * @param model the Spring MVC model
     * @return the movie form template
     */
    @GetMapping("/newMovie")
    public String showMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movieForm";
    }

    /**
     * Saves a new movie and assigns it to the current user.
     * POST /saveMovie
     *
     * @param movie the movie object bound from the form
     * @return redirect to the movies page
     */
    @PostMapping("/saveMovie")
    public String saveMovie(Movie movie) {
        User user = userService.getCurrentUser();
        movie.setUser(user);
        movieService.saveMovie(movie);
        return "redirect:/moviesPage";
    }

    /**
     * Displays the form pre-filled with an existing movie's data for editing.
     * GET /editMovie/{id}
     *
     * @param id    the ID of the movie to edit
     * @param model the Spring MVC model
     * @return the movie form template
     */
    @GetMapping("/editMovie/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "movieForm";
    }

    /**
     * Deletes a movie by its ID.
     * GET /deleteMovie/{id}
     *
     * @param id the ID of the movie to delete
     * @return redirect to the movies page
     */
    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/moviesPage";
    }
}