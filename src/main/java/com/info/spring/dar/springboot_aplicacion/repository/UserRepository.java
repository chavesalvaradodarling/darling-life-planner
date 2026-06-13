package com.info.spring.dar.springboot_aplicacion.repository;

// Importa JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;

// Importa la entidad User
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Indica que esta interfaz se encargará de acceder
// a la tabla users de la base de datos
public interface UserRepository extends JpaRepository<User, Long> {

    /*
     * JpaRepository ya proporciona automáticamente:
     *
     * save()
     * findById()
     * findAll()
     * deleteById()
     * existsById()
     *
     * No es necesario programarlos.
     */

    /*
     * Método personalizado.
     *
     * Permite buscar un usuario por su correo electrónico.
     *
     * Ejemplo:
     *
     * User user = userRepository.findByEmail("darling@gmail.com");
     *
     * Spring genera automáticamente la consulta:
     *
     * SELECT * FROM users WHERE email = ?
     */
    User findByEmail(String email);

}
