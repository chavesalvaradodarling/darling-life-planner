package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.info.spring.dar.springboot_aplicacion.entity.Movie;
import com.info.spring.dar.springboot_aplicacion.service.MovieService;

@Controller
public class MovieViewController {

    // Servicio de películas
    private final MovieService movieService;

    // Constructor
    public MovieViewController(MovieService movieService) {
        this.movieService = movieService;
    }

    /*
     * Mostrar todas las películas
     */
    @GetMapping("/moviesPage")
    public String showMovies(Model model) {

        model.addAttribute(
                "movies",
                movieService.getAllMovies());

        return "movies";
    }

    /*
     * Formulario para nueva película
     */
    @GetMapping("/newMovie")
    public String showMovieForm(Model model) {

        model.addAttribute(
                "movie",
                new Movie());

        return "movieForm";
    }

    /*
     * Guardar película
     */
    @PostMapping("/saveMovie")
    public String saveMovie(Movie movie) {

        movieService.saveMovie(movie);

        return "redirect:/moviesPage";
    }

    /*
     * Editar película
     */
    @GetMapping("/editMovie/{id}")
    public String editMovie(@PathVariable Long id,
                            Model model) {

        Movie movie =
                movieService.getMovieById(id);

        model.addAttribute(
                "movie",
                movie);

        return "movieForm";
    }

    /*
     * Eliminar película
     */
    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);

        return "redirect:/moviesPage";
    }

}