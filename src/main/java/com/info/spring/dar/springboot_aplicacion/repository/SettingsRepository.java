package com.info.spring.dar.springboot_aplicacion.repository;

// Importa List para devolver varias configuraciones
import java.util.List;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Settings;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/*
 * Esta interfaz se encargará de acceder
 * a la tabla settings de la base de datos.
 *
 * Long indica que la llave primaria (id)
 * es de tipo Long.
 */
public interface SettingsRepository
        extends JpaRepository<Settings, Long> {

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
     * Devuelve todas las configuraciones
     * pertenecientes a un usuario.
     */
    List<Settings> findByUser(User user);

    /*
     * Permite buscar configuraciones por tema.
     *
     * Ejemplos:
     * Pastel
     * Oscuro
     * Claro
     */
    List<Settings> findByTheme(String theme);

}