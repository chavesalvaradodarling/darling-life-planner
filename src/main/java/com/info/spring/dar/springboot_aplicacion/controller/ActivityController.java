package com.info.spring.dar.springboot_aplicacion.controller;

// Importa las anotaciones necesarias de Spring Boot
import org.springframework.web.bind.annotation.*;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;

// Importa el servicio
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;

/*
 * Esta clase se encargará de recibir las peticiones HTTP
 * relacionadas con las actividades.
 */
@RestController

// Ruta principal para las actividades
@RequestMapping("/activities")
public class ActivityController {

    // Objeto que permitirá acceder a la lógica del servicio
    private final ActivityService activityService;

    /*
     * Constructor con inyección de dependencias.
     */
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /*
     * GET
     *
     * Devuelve todas las actividades.
     *
     * URL:
     * http://localhost:8080/activities
     */
    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    /*
     * GET
     *
     * Devuelve una actividad según su id.
     *
     * Ejemplo:
     * http://localhost:8080/activities/1
     */
    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }

    /*
     * GET
     *
     * Devuelve todas las actividades
     * pertenecientes a una planificación.
     */
    @GetMapping("/planning")
    public List<Activity> getActivitiesByPlanning(@RequestBody Planning planning) {
        return activityService.getActivitiesByPlanning(planning);
    }

    /*
     * GET
     *
     * Devuelve todas las actividades
     * pertenecientes a una categoría.
     */
    @GetMapping("/category")
    public List<Activity> getActivitiesByCategory(@RequestBody Category category) {
        return activityService.getActivitiesByCategory(category);
    }

    /*
     * GET
     *
     * Devuelve todas las actividades
     * asociadas a un curso.
     */
    @GetMapping("/course")
    public List<Activity> getActivitiesByCourse(@RequestBody Course course) {
        return activityService.getActivitiesByCourse(course);
    }

    /*
     * GET
     *
     * Devuelve todas las actividades
     * según su estado.
     *
     * Ejemplo:
     * http://localhost:8080/activities/status/COMPLETED
     */
    @GetMapping("/status/{status}")
    public List<Activity> getActivitiesByStatus(@PathVariable String status) {
        return activityService.getActivitiesByStatus(status);
    }

    /*
     * POST
     *
     * Guarda una nueva actividad.
     */
    @PostMapping
    public Activity saveActivity(@RequestBody Activity activity) {
        return activityService.saveActivity(activity);
    }

    /*
     * DELETE
     *
     * Elimina una actividad según su id.
     *
     * Ejemplo:
     * http://localhost:8080/activities/1
     */
    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }

}