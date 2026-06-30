package com.info.spring.dar.springboot_aplicacion.service;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.UserRepository;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 *
 * Called automatically by Spring Security during login to load
 * the user's credentials and roles from the database by email.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /** Repository used to look up users by email. */
    private final UserRepository userRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param userRepository the repository used to access the users table
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by their email address (used as username).
     * Throws UsernameNotFoundException if no user is found.
     * Returns a CustomUserDetails object with the user's email,
     * password, name, and role.
     *
     * @param email the email entered on the login form
     * @return a UserDetails object for Spring Security
     * @throws UsernameNotFoundException if no user exists with that email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }
}