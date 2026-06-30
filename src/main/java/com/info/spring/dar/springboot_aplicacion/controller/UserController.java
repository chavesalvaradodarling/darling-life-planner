package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * REST controller that exposes API endpoints for managing users.
 * Base URL: /users
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /** Service that handles all user-related business logic. */
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param userService the service used to manage users
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns all users in the system.
     * GET /users
     *
     * @return list of all users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Returns a single user by their ID.
     * GET /users/{id}
     *
     * @param id the user ID
     * @return the matching user, or null if not found
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Saves a new user.
     * POST /users
     *
     * @param user the user object received in the request body
     * @return the saved user
     */
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Deletes a user by their ID.
     * DELETE /users/{id}
     *
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}