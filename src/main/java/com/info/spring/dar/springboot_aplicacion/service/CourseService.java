package com.info.spring.dar.springboot_aplicacion.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.ActivityRepository;
import com.info.spring.dar.springboot_aplicacion.repository.CourseRepository;

/**
 * Service class that contains the business logic for managing courses.
 * Communicates with CourseRepository and ActivityRepository to access the database.
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ActivityRepository activityRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param courseRepository   the repository used to access the courses table
     * @param activityRepository the repository used to disassociate activities before deletion
     */
    public CourseService(CourseRepository courseRepository,
            ActivityRepository activityRepository) {
        this.courseRepository = courseRepository;
        this.activityRepository = activityRepository;
    }

    /**
     * Saves a course to the database.
     *
     * @param course the course to save
     * @return the saved course
     */
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Returns all courses in the system.
     *
     * @return list of all courses
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Returns a single course by its ID, or null if not found.
     *
     * @param id the course ID
     * @return the matching course or null
     */
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    /**
     * Returns all courses belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of courses for that user
     */
    public List<Course> getCoursesByUser(User user) {
        return courseRepository.findByUser(user);
    }

    /**
     * Returns all courses matching the given name.
     *
     * @param name the course name to search for
     * @return list of matching courses
     */
    public List<Course> getCoursesByName(String name) {
        return courseRepository.findByName(name);
    }

    /**
     * Returns a single course matching the given code.
     *
     * @param code the course code (e.g. "INF-101")
     * @return the matching course or null
     */
    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code);
    }

    /**
     * Deletes a course by its ID.
     * Before deleting, sets the course field to null on all associated activities
     * to avoid a foreign key constraint violation.
     *
     * @param id the ID of the course to delete
     */
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        if (course == null) {
            return;
        }
        List<Activity> activities = activityRepository.findByCourse(course);
        for (Activity activity : activities) {
            activity.setCourse(null);
        }
        activityRepository.saveAll(activities);
        courseRepository.deleteById(id);
    }
}