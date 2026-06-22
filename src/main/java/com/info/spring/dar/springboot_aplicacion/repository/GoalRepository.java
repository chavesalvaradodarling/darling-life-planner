package com.info.spring.dar.springboot_aplicacion.repository;

// Importa List para devolver varias metas
import java.util.List;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Goal;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/*
 * Esta interfaz se encargará de acceder
 * a la tabla goals de la base de datos.
 *
 * Long indica que la llave primaria (id)
 * es de tipo Long.
 */
public interface GoalRepository
        extends JpaRepository<Goal, Long> {

    /*
     * JpaRepository ya proporciona:
     *
     * save()
     * findById()
     * findAll()
     * deleteById()
     * existsById()
     */

    /*
     * Devuelve todas las metas
     * pertenecientes a un usuario.
     */
    List<Goal> findByUser(User user);

    /*
     * Permite buscar metas por título.
     *
     * Ejemplos:
     * Estudiar 300 horas
     * Gym 3 veces por semana
     */
    List<Goal> findByTitle(String title);

}