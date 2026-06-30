package com.info.spring.dar.springboot_aplicacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the User entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 *
 * Spring Data JPA generates the SQL query automatically from the method name.
 * Example: findByEmail("darling@gmail.com") → SELECT * FROM users WHERE email = ?
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Returns the user with the given email address.
     * Used by Spring Security for authentication and by UserService
     * to retrieve the currently logged-in user.
     *
     * @param email the email address to search for
     * @return the matching user, or null if not found
     */
    User findByEmail(String email);
}