package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MVC controller that handles the login page.
 */
@Controller
public class LoginViewController {

    /**
     * Displays the login page.
     * GET /login
     *
     * @return the login view template
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}