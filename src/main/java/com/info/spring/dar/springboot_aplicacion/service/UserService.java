package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa la entidad User
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el repositorio UserRepository
import com.info.spring.dar.springboot_aplicacion.repository.UserRepository;

/*
 * Esta clase contiene la lógica relacionada con los usuarios.
 *
 * Se comunica con UserRepository para acceder a la base de datos.
 */
@Service
public class UserService {

    // Objeto que permite acceder a la tabla users
    private final UserRepository userRepository;

    /*
     * Constructor con inyección de dependencias.
     *
     * Spring Boot creará automáticamente el objeto UserRepository
     * y lo asignará a esta variable.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * Guarda un usuario en la base de datos.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /*
     * Devuelve todos los usuarios registrados.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /*
     * Busca un usuario por su id.
     *
     * Si no existe, devuelve null.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /*
     * Elimina un usuario por su id.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}