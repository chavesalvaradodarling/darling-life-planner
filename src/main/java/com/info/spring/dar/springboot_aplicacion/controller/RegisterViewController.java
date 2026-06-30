package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that handles the user registration page.
 * Displays the registration form and saves new users to the database.
 */
@Controller
public class RegisterViewController {

    /** Service used to save new users to the database. */
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param userService the service used to manage users
     */
    public RegisterViewController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the registration page.
     * GET /register
     *
     * @param model the Spring MVC model
     * @return the register view template
     */
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Saves a new user with the role USER and redirects to the login page.
     * POST /register
     *
     * @param user the user object bound from the registration form
     * @return redirect to the login page
     */
    @PostMapping("/register")
    public String registerUser(User user) {
        user.setRole("USER");
        userService.saveUser(user);
        return "redirect:/login";
    }
}