package com.info.spring.dar.springboot_aplicacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Security configuration class for the application.
 *
 * Defines the password encoder, authentication manager,
 * and the HTTP security filter chain including login, logout,
 * and route access rules.
 */
@Configuration
public class SecurityConfig {

    /**
     * Defines the password encoder bean used to hash and verify passwords.
     * Uses BCrypt hashing algorithm.
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the AuthenticationManager bean so it can be injected
     * in other components that need to authenticate users programmatically.
     *
     * @param config the authentication configuration provided by Spring
     * @return the configured AuthenticationManager
     * @throws Exception if the manager cannot be retrieved
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }

    /**
     * Configures the HTTP security filter chain.
     *
     * Rules:
     * - CSRF is disabled.
     * - Public routes: /login, /register, /css/**, /images/**, /js/**
     * - All other routes require authentication.
     * - Custom login page at /login using email and password fields.
     * - Logout redirects to /login?logout.
     * - HTTP Basic authentication is also enabled.
     *
     * @param http the HttpSecurity object to configure
     * @return the built SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/register",
                                "/css/**",
                                "/images/**",
                                "/js/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}