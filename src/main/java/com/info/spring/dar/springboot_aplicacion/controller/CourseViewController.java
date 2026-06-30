package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.CourseService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that handles the course views and form submissions.
 * Manages the course list, creation, editing, and deletion for the logged-in user.
 */
@Controller
public class CourseViewController {

    private final CourseService courseService;
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param courseService the service used to manage courses
     * @param userService   the service used to get the current user
     */
    public CourseViewController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    /**
     * Displays all courses belonging to the currently logged-in user.
     * GET /coursesPage
     *
     * @param model the Spring MVC model
     * @return the courses list view template
     */
    @GetMapping("/coursesPage")
    public String showCourses(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("courses", courseService.getCoursesByUser(user));
        return "courses";
    }

    /**
     * Displays the form to create a new course.
     * GET /newCourse
     *
     * @param model the Spring MVC model
     * @return the course form template
     */
    @GetMapping("/newCourse")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "courseForm";
    }

    /**
     * Saves a new course and assigns it to the current user.
     * POST /saveCourse
     *
     * @param course the course object bound from the form
     * @return redirect to the courses page
     */
    @PostMapping("/saveCourse")
    public String saveCourse(Course course) {
        User user = userService.getCurrentUser();
        course.setUser(user);
        courseService.saveCourse(course);
        return "redirect:/coursesPage";
    }

    /**
     * Displays the form pre-filled with an existing course's data for editing.
     * GET /editCourse/{id}
     *
     * @param id    the ID of the course to edit
     * @param model the Spring MVC model
     * @return the course form template
     */
    @GetMapping("/editCourse/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "courseForm";
    }

    /**
     * Deletes a course by its ID.
     * GET /deleteCourse/{id}
     *
     * @param id the ID of the course to delete
     * @return redirect to the courses page
     */
    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/coursesPage";
    }
}