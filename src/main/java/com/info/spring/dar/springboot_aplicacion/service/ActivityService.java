package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.ActivityRepository;

/*
 * Esta clase contiene la lógica relacionada con las actividades.
 *
 * Se comunica con ActivityRepository para acceder a la base de datos.
 */
@Service
public class ActivityService {

    // Objeto que permite acceder a la tabla activities
    private final ActivityRepository activityRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /*
     * Guarda una actividad en la base de datos.
     */
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    /*
     * Devuelve todas las actividades registradas.
     */
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    /*
     * Busca una actividad por su id.
     *
     * Si no existe, devuelve null.
     */
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    /*
     * Devuelve todas las actividades
     * pertenecientes a una planificación.
     */
    public List<Activity> getActivitiesByPlanning(Planning planning) {
        return activityRepository.findByPlanning(planning);
    }

    /*
     * Devuelve todas las actividades
     * pertenecientes a una categoría.
     */
    public List<Activity> getActivitiesByCategory(Category category) {
        return activityRepository.findByCategory(category);
    }

    /*
     * Devuelve todas las actividades
     * asociadas a un curso.
     */
    public List<Activity> getActivitiesByCourse(Course course) {
        return activityRepository.findByCourse(course);
    }

    /*
     * Devuelve todas las actividades
     * según su estado.
     *
     * COMPLETED
     * PARTIALLY_COMPLETED
     * NOT_COMPLETED
     */
    public List<Activity> getActivitiesByStatus(String status) {
        return activityRepository.findByStatus(status);
    }

    /*
     * Elimina una actividad por su id.
     */
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

}