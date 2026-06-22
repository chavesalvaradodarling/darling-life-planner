package com.info.spring.dar.springboot_aplicacion.repository;

// Importa List para devolver varios perfiles
import java.util.List;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Profile;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/*
 * Esta interfaz se encargará de acceder
 * a la tabla profiles de la base de datos.
 *
 * Long indica que la llave primaria (id)
 * es de tipo Long.
 */
public interface ProfileRepository
        extends JpaRepository<Profile, Long> {

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
     * Devuelve todos los perfiles
     * pertenecientes a un usuario.
     */
    List<Profile> findByUser(User user);

    /*
     * Permite buscar perfiles por nombre.
     */
    List<Profile> findByFullName(String fullName);

    /*
     * Permite buscar un perfil por correo.
     */
    Profile findByEmail(String email);

}