package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Goal;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the Goal entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 */
public interface GoalRepository extends JpaRepository<Goal, Long> {

    /**
     * Returns all goals belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of goals for that user
     */
    List<Goal> findByUser(User user);

    /**
     * Returns all goals matching the given title.
     * Examples: "Study 300 hours", "Go to the gym 3 times a week"
     *
     * @param title the goal title to search for
     * @return list of matching goals
     */
    List<Goal> findByTitle(String title);
}