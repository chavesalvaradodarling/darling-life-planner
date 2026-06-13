package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.PlanningRepository;

/*
 * Esta clase contiene la lógica relacionada con las planificaciones.
 *
 * Se comunica con PlanningRepository para acceder a la base de datos.
 */
@Service
public class PlanningService {

    // Objeto que permite acceder a la tabla planning
    private final PlanningRepository planningRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public PlanningService(PlanningRepository planningRepository) {
        this.planningRepository = planningRepository;
    }

    /*
     * Guarda una planificación en la base de datos.
     */
    public Planning savePlanning(Planning planning) {
        return planningRepository.save(planning);
    }

    /*
     * Devuelve todas las planificaciones registradas.
     */
    public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

    /*
     * Busca una planificación por su id.
     *
     * Si no existe, devuelve null.
     */
    public Planning getPlanningById(Long id) {
        return planningRepository.findById(id).orElse(null);
    }

    /*
     * Devuelve todas las planificaciones
     * pertenecientes a un usuario.
     */
    public List<Planning> getPlanningsByUser(User user) {
        return planningRepository.findByUser(user);
    }

    /*
     * Elimina una planificación por su id.
     */
    public void deletePlanning(Long id) {
        planningRepository.deleteById(id);
    }

}