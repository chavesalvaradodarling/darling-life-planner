package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.CourseRepository;

/*
 * Esta clase contiene la lógica relacionada con los cursos.
 *
 * Se comunica con CourseRepository para acceder a la base de datos.
 */
@Service
public class CourseService {

    // Objeto que permite acceder a la tabla courses
    private final CourseRepository courseRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /*
     * Guarda un curso en la base de datos.
     */
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    /*
     * Devuelve todos los cursos registrados.
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /*
     * Busca un curso por su id.
     *
     * Si no existe, devuelve null.
     */
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    /*
     * Devuelve todos los cursos
     * pertenecientes a un usuario.
     */
    public List<Course> getCoursesByUser(User user) {
        return courseRepository.findByUser(user);
    }

    /*
     * Busca cursos por nombre.
     *
     * Ejemplo:
     * Cálculo I
     * Programación III
     */
    public List<Course> getCoursesByName(String name) {
        return courseRepository.findByName(name);
    }

    /*
     * Busca un curso por código.
     *
     * Ejemplo:
     * INF-101
     * MAT-201
     */
    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code);
    }

    /*
     * Elimina un curso por su id.
     */
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}