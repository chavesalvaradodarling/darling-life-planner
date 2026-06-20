package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.service.CourseService;

@Controller
public class CourseViewController {

    // Servicio de cursos
    private final CourseService courseService;

    // Constructor
    public CourseViewController(CourseService courseService) {
        this.courseService = courseService;
    }

    /*
     * Muestra todos los cursos
     */
    @GetMapping("/coursesPage")
    public String showCourses(Model model) {

        model.addAttribute(
                "courses",
                courseService.getAllCourses());

        return "courses";
    }

    /*
     * Muestra el formulario para crear un nuevo curso
     */
    @GetMapping("/newCourse")
    public String showCourseForm(Model model) {

        model.addAttribute(
                "course",
                new Course());

        return "courseForm";
    }

    /*
     * Guarda un curso en MySQL
     */
    @PostMapping("/saveCourse")
    public String saveCourse(Course course) {

        courseService.saveCourse(course);

        return "redirect:/coursesPage";
    }

    /*
     * Editar un curso
     */
    @GetMapping("/editCourse/{id}")
    public String editCourse(
            @PathVariable Long id,
            Model model) {

        Course course =
                courseService.getCourseById(id);

        model.addAttribute(
                "course",
                course);

        return "courseForm";
    }

    /*
     * Eliminar un curso
     */
    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return "redirect:/coursesPage";
    }

}