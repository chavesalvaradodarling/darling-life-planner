package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the Course entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 *
 * Custom query methods are derived automatically by Spring Data JPA.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Returns all courses belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of courses for that user
     */
    List<Course> findByUser(User user);

    /**
     * Returns all courses matching the given name.
     * Examples: "Cálculo I", "Programación III"
     *
     * @param name the course name to search for
     * @return list of matching courses
     */
    List<Course> findByName(String name);

    /**
     * Returns a single course matching the given code.
     * Examples: "INF-101", "MAT-201"
     *
     * @param code the course code to search for
     * @return the matching course, or null if not found
     */
    Course findByCode(String code);
}