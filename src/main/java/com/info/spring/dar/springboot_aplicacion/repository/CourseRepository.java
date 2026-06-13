package com.info.spring.dar.springboot_aplicacion.repository;

// Importa List para devolver varios cursos
import java.util.List;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/*
 * Esta interfaz se encargará de acceder a la tabla courses
 * de la base de datos.
 *
 * Long indica que la llave primaria (id)
 * es de tipo Long.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    /*
     * JpaRepository ya proporciona automáticamente:
     *
     * save()
     * findById()
     * findAll()
     * deleteById()
     * existsById()
     */

    /*
     * Devuelve todos los cursos pertenecientes
     * a un usuario.
     */
    List<Course> findByUser(User user);

    /*
     * Permite buscar cursos por nombre.
     *
     * Ejemplos:
     * Cálculo I
     * Programación III
     */
    List<Course> findByName(String name);

    /*
     * Permite buscar un curso por código.
     *
     * Ejemplo:
     * INF-101
     * MAT-201
     */
    Course findByCode(String code);

}
