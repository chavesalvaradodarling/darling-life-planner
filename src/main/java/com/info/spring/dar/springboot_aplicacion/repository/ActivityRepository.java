package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.Movie;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the Activity entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 *
 * Custom query methods are derived automatically by Spring Data JPA
 * from their method names.
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    /**
     * Returns all activities belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of activities for that user
     */
    List<Activity> findByUser(User user);

    /**
     * Returns all activities linked to a specific planning.
     *
     * @param planning the planning to filter by
     * @return list of activities for that planning
     */
    List<Activity> findByPlanning(Planning planning);

    /**
     * Returns all activities linked to a specific category.
     *
     * @param category the category to filter by
     * @return list of activities for that category
     */
    List<Activity> findByCategory(Category category);

    /**
     * Returns all activities linked to a specific course.
     *
     * @param course the course to filter by
     * @return list of activities for that course
     */
    List<Activity> findByCourse(Course course);

    /**
     * Returns all activities linked to a specific movie.
     *
     * @param movie the movie to filter by
     * @return list of activities for that movie
     */
    List<Activity> findByMovie(Movie movie);

    /**
     * Returns all activities with a specific status.
     *
     * @param status the status to filter by (e.g. PENDING, COMPLETED)
     * @return list of activities with that status
     */
    List<Activity> findByStatus(String status);

    /**
     * Returns all activities with a specific status, ordered by date ascending.
     *
     * @param status the status to filter by
     * @return list of activities ordered by activityDate ascending
     */
    List<Activity> findByStatusOrderByActivityDateAsc(String status);
}