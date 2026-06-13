package com.info.spring.dar.springboot_aplicacion.repository;

// Importa List para devolver varias actividades
import java.util.List;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;

/*
 * Esta interfaz se encargará de acceder a la tabla activities
 * de la base de datos.
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {

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
     * Devuelve todas las actividades
     * pertenecientes a una planificación.
     */
    List<Activity> findByPlanning(Planning planning);

    /*
     * Devuelve todas las actividades
     * pertenecientes a una categoría.
     */
    List<Activity> findByCategory(Category category);

    /*
     * Devuelve todas las actividades
     * asociadas a un curso.
     */
    List<Activity> findByCourse(Course course);

    /*
     * Devuelve todas las actividades
     * con un estado determinado.
     *
     * Ejemplos:
     * COMPLETED
     * PARTIALLY_COMPLETED
     * NOT_COMPLETED
     */
    List<Activity> findByStatus(String status);

}
