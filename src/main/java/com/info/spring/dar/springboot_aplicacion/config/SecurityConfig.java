package com.info.spring.dar.springboot_aplicacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();

    }

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
                                "/css/**")

                        .permitAll()

                        .anyRequest()
                        .authenticated())

                .formLogin(login -> login

                        .loginPage("/login")

                        // IMPORTANTE
                        .usernameParameter("email")

                        .passwordParameter("password")

                        .defaultSuccessUrl("/", true)

                        .permitAll())

                .logout(logout -> logout

                        .logoutSuccessUrl("/login")

                        .permitAll())

                .httpBasic(Customizer.withDefaults());

        return http.build();

    }

}