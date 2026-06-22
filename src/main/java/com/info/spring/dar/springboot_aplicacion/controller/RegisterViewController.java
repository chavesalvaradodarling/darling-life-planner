package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

@Controller
public class RegisterViewController {

    // Servicio de usuarios
    private final UserService userService;

    // Constructor
    public RegisterViewController(UserService userService) {
        this.userService = userService;
    }

    /*
     * Muestra la pantalla de registro.
     */
    @GetMapping("/register")
    public String showRegisterPage(Model model) {

        model.addAttribute(
                "user",
                new User());

        return "register";

    }

    /*
     * Guarda el usuario en MySQL
     */
    @PostMapping("/register")
    public String registerUser(User user) {

        // Todos los usuarios normales tendrán rol USER
        user.setRole("USER");

        // Guardar usuario
        userService.saveUser(user);

        // Después del registro enviar al login
        return "redirect:/login";

    }

}