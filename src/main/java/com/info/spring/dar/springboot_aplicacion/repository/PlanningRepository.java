package com.info.spring.dar.springboot_aplicacion.repository;

// Importa List para devolver varias planificaciones
import java.util.List;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/*
 * Esta interfaz se encargará de comunicarse con la tabla planning
 * de la base de datos.
 *
 * Long indica que la llave primaria (id)
 * es de tipo Long.
 */
public interface PlanningRepository extends JpaRepository<Planning, Long> {

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
     * Método personalizado.
     *
     * Permite obtener todas las planificaciones
     * pertenecientes a un usuario.
     *
     * Ejemplo:
     *
     * List<Planning> plannings =
     * planningRepository.findByUser(user);
     *
     * Spring generará automáticamente:
     *
     * SELECT * FROM planning
     * WHERE user_id = ?
     */
    List<Planning> findByUser(User user);

}
