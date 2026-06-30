package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.CourseService;

/**
 * REST controller that exposes API endpoints for managing courses.
 * Base URL: /courses
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    /** Service that handles all course-related business logic. */
    private final CourseService courseService;

    /**
     * Constructor with dependency injection.
     *
     * @param courseService the service used to manage courses
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Returns all courses in the system.
     * GET /courses
     *
     * @return list of all courses
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    /**
     * Returns a single course by its ID.
     * GET /courses/{id}
     *
     * @param id the course ID
     * @return the matching course, or null if not found
     */
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    /**
     * Returns all courses belonging to a specific user.
     * GET /courses/user
     *
     * @param user the user object sent in the request body
     * @return list of courses for that user
     */
    @GetMapping("/user")
    public List<Course> getCoursesByUser(@RequestBody User user) {
        return courseService.getCoursesByUser(user);
    }

    /**
     * Returns all courses matching the given name.
     * GET /courses/name/{name}
     *
     * @param name the course name to search for
     * @return list of matching courses
     */
    @GetMapping("/name/{name}")
    public List<Course> getCoursesByName(@PathVariable String name) {
        return courseService.getCoursesByName(name);
    }

    /**
     * Returns a single course matching the given code.
     * GET /courses/code/{code}
     *
     * @param code the course code (e.g. INF-101)
     * @return the matching course, or null if not found
     */
    @GetMapping("/code/{code}")
    public Course getCourseByCode(@PathVariable String code) {
        return courseService.getCourseByCode(code);
    }

    /**
     * Saves a new course.
     * POST /courses
     *
     * @param course the course object received in the request body
     * @return the saved course
     */
    @PostMapping
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    /**
     * Deletes a course by its ID.
     * DELETE /courses/{id}
     *
     * @param id the ID of the course to delete
     */
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}