package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Movie;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the Movie entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /**
     * Returns all movies belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of movies for that user
     */
    List<Movie> findByUser(User user);

    /**
     * Returns all movies matching the given title.
     *
     * @param title the movie title to search for
     * @return list of matching movies
     */
    List<Movie> findByTitle(String title);

    /**
     * Returns all movies matching the given genre.
     *
     * @param genre the genre to filter by
     * @return list of matching movies
     */
    List<Movie> findByGenre(String genre);
}