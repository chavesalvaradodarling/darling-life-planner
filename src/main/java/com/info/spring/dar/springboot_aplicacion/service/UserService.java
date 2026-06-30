package com.info.spring.dar.springboot_aplicacion.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.UserRepository;

/**
 * Service class that contains the business logic for managing users.
 * Handles user creation with password encoding and retrieval of the
 * currently authenticated user from the Spring Security context.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor with dependency injection.
     *
     * @param userRepository  the repository used to access the users table
     * @param passwordEncoder the BCrypt encoder used to hash passwords
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves a new user to the database.
     * Encodes the password using BCrypt before saving.
     *
     * @param user the user to save
     * @return the saved user with encoded password
     */
    public User saveUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    /**
     * Updates the password of an existing user.
     * Encodes the new password using BCrypt before saving.
     *
     * @param user        the user whose password should be updated
     * @param newPassword the plain-text new password
     */
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    /**
     * Returns all users in the system.
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns a single user by their ID, or null if not found.
     *
     * @param id the user ID
     * @return the matching user or null
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Returns the user with the given email address.
     *
     * @param email the email to search for
     * @return the matching user or null
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Returns the currently authenticated user from the Spring Security context.
     * Uses the email stored in the authentication object to look up the user.
     *
     * @return the currently logged-in user, or null if not authenticated
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return userRepository.findByEmail(authentication.getName());
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}