package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.info.spring.dar.springboot_aplicacion.entity.Profile;
import com.info.spring.dar.springboot_aplicacion.service.ProfileService;

@Controller
public class ProfileViewController {

    // Servicio de perfiles
    private final ProfileService profileService;

    // Constructor
    public ProfileViewController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /*
     * Muestra todos los perfiles
     */
    @GetMapping("/profilesPage")
    public String showProfiles(Model model) {

        model.addAttribute(
                "profiles",
                profileService.getAllProfiles());

        return "profilesPage";
    }

    /*
     * Muestra el formulario para crear un nuevo perfil
     */
    @GetMapping("/newProfile")
    public String showProfileForm(Model model) {

        model.addAttribute(
                "profile",
                new Profile());

        return "profileForm";
    }

    /*
     * Guarda un perfil en MySQL
     */
    @PostMapping("/saveProfile")
    public String saveProfile(Profile profile) {

        profileService.saveProfile(profile);

        return "redirect:/profilesPage";
    }

    /*
     * Editar un perfil
     */
    @GetMapping("/editProfile/{id}")
    public String editProfile(
            @PathVariable Long id,
            Model model) {

        Profile profile =
                profileService.getProfileById(id);

        model.addAttribute(
                "profile",
                profile);

        return "profileForm";
    }

    /*
     * Eliminar un perfil
     */
    @GetMapping("/deleteProfile/{id}")
    public String deleteProfile(
            @PathVariable Long id) {

        profileService.deleteProfile(id);

        return "redirect:/profilesPage";
    }

}