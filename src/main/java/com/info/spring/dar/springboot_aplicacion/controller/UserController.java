package com.info.spring.dar.springboot_aplicacion.controller;

// Importa las anotaciones necesarias de Spring Boot
import org.springframework.web.bind.annotation.*;

// Permite trabajar con listas
import java.util.List;

// Importa la entidad User
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el servicio
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/*
 * Esta clase se encargará de recibir las peticiones HTTP
 * relacionadas con los usuarios.
 */
@RestController

// Ruta principal para usuarios
@RequestMapping("/users")
public class UserController {

    // Objeto que permitirá acceder a la lógica del servicio
    private final UserService userService;

    /*
     * Constructor con inyección de dependencias.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * GET
     *
     * Devuelve todos los usuarios.
     *
     * URL:
     * http://localhost:8080/users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /*
     * GET
     *
     * Devuelve un usuario según su id.
     *
     * Ejemplo:
     * http://localhost:8080/users/1
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /*
     * POST
     *
     * Guarda un nuevo usuario.
     *
     * Se recibe un JSON desde Postman o desde el frontend.
     */
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /*
     * DELETE
     *
     * Elimina un usuario según su id.
     *
     * Ejemplo:
     * http://localhost:8080/users/1
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
