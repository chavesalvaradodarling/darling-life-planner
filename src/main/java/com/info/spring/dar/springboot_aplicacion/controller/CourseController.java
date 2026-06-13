package com.info.spring.dar.springboot_aplicacion.controller;

// Importa las anotaciones necesarias de Spring Boot
import org.springframework.web.bind.annotation.*;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el servicio
import com.info.spring.dar.springboot_aplicacion.service.CourseService;

/*
 * Esta clase se encargará de recibir las peticiones HTTP
 * relacionadas con los cursos.
 */
@RestController

// Ruta principal para los cursos
@RequestMapping("/courses")
public class CourseController {

    // Objeto que permitirá acceder a la lógica del servicio
    private final CourseService courseService;

    /*
     * Constructor con inyección de dependencias.
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /*
     * GET
     *
     * Devuelve todos los cursos.
     *
     * URL:
     * http://localhost:8080/courses
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    /*
     * GET
     *
     * Devuelve un curso según su id.
     *
     * Ejemplo:
     * http://localhost:8080/courses/1
     */
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    /*
     * GET
     *
     * Devuelve todos los cursos
     * pertenecientes a un usuario.
     */
    @GetMapping("/user")
    public List<Course> getCoursesByUser(@RequestBody User user) {
        return courseService.getCoursesByUser(user);
    }

    /*
     * GET
     *
     * Busca cursos por nombre.
     *
     * Ejemplo:
     * http://localhost:8080/courses/name/Cálculo I
     */
    @GetMapping("/name/{name}")
    public List<Course> getCoursesByName(@PathVariable String name) {
        return courseService.getCoursesByName(name);
    }

    /*
     * GET
     *
     * Busca un curso por código.
     *
     * Ejemplo:
     * http://localhost:8080/courses/code/INF-101
     */
    @GetMapping("/code/{code}")
    public Course getCourseByCode(@PathVariable String code) {
        return courseService.getCourseByCode(code);
    }

    /*
     * POST
     *
     * Guarda un nuevo curso.
     */
    @PostMapping
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    /*
     * DELETE
     *
     * Elimina un curso según su id.
     *
     * Ejemplo:
     * http://localhost:8080/courses/1
     */
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

}