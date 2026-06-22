package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Goal;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.GoalRepository;

/*
 * Esta clase contiene la lógica relacionada con las metas.
 *
 * Se comunica con GoalRepository para acceder a la base de datos.
 */
@Service
public class GoalService {

    // Objeto que permite acceder a la tabla goals
    private final GoalRepository goalRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    /*
     * Guarda una meta en la base de datos.
     */
    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    /*
     * Devuelve todas las metas registradas.
     */
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    /*
     * Busca una meta por su id.
     *
     * Si no existe, devuelve null.
     */
    public Goal getGoalById(Long id) {
        return goalRepository.findById(id).orElse(null);
    }

    /*
     * Devuelve todas las metas
     * pertenecientes a un usuario.
     */
    public List<Goal> getGoalsByUser(User user) {
        return goalRepository.findByUser(user);
    }

    /*
     * Busca metas por título.
     *
     * Ejemplos:
     * Estudiar 300 horas
     * Gym 3 veces por semana
     */
    public List<Goal> getGoalsByTitle(String title) {
        return goalRepository.findByTitle(title);
    }

    /*
     * Elimina una meta por su id.
     */
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

}