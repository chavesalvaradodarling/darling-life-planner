package com.info.spring.dar.springboot_aplicacion.controller;

// Importa las anotaciones necesarias de Spring Boot
import org.springframework.web.bind.annotation.*;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el servicio
import com.info.spring.dar.springboot_aplicacion.service.PlanningService;

/*
 * Esta clase se encargará de recibir las peticiones HTTP
 * relacionadas con las planificaciones.
 */
@RestController

// Ruta principal para las planificaciones
@RequestMapping("/plannings")
public class PlanningController {

    // Objeto que permitirá acceder a la lógica del servicio
    private final PlanningService planningService;

    /*
     * Constructor con inyección de dependencias.
     */
    public PlanningController(PlanningService planningService) {
        this.planningService = planningService;
    }

    /*
     * GET
     *
     * Devuelve todas las planificaciones.
     *
     * URL:
     * http://localhost:8080/plannings
     */
    @GetMapping
    public List<Planning> getAllPlannings() {
        return planningService.getAllPlannings();
    }

    /*
     * GET
     *
     * Devuelve una planificación según su id.
     *
     * Ejemplo:
     * http://localhost:8080/plannings/1
     */
    @GetMapping("/{id}")
    public Planning getPlanningById(@PathVariable Long id) {
        return planningService.getPlanningById(id);
    }

    /*
     * GET
     *
     * Devuelve todas las planificaciones de un usuario.
     *
     * Ejemplo:
     * http://localhost:8080/plannings/user
     */
    @GetMapping("/user")
    public List<Planning> getPlanningsByUser(@RequestBody User user) {
        return planningService.getPlanningsByUser(user);
    }

    /*
     * POST
     *
     * Guarda una nueva planificación.
     */
    @PostMapping
    public Planning savePlanning(@RequestBody Planning planning) {
        return planningService.savePlanning(planning);
    }

    /*
     * DELETE
     *
     * Elimina una planificación según su id.
     *
     * Ejemplo:
     * http://localhost:8080/plannings/1
     */
    @DeleteMapping("/{id}")
    public void deletePlanning(@PathVariable Long id) {
        planningService.deletePlanning(id);
    }

}
