package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.info.spring.dar.springboot_aplicacion.entity.Settings;
import com.info.spring.dar.springboot_aplicacion.service.SettingsService;

@Controller
public class SettingsViewController {

    // Servicio de configuraciones
    private final SettingsService settingsService;

    // Constructor
    public SettingsViewController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    /*
     * Muestra todas las configuraciones
     */
    @GetMapping("/settingsPage")
    public String showSettings(Model model) {

        model.addAttribute(
                "settingsList",
                settingsService.getAllSettings());

        return "settingsPage";
    }

    /*
     * Muestra el formulario para crear una nueva configuración
     */
    @GetMapping("/newSettings")
    public String showSettingsForm(Model model) {

        model.addAttribute(
                "settings",
                new Settings());

        return "settingsForm";
    }

    /*
     * Guarda una configuración en MySQL
     */
    @PostMapping("/saveSettings")
    public String saveSettings(Settings settings) {

        settingsService.saveSettings(settings);

        return "redirect:/settingsPage";
    }

    /*
     * Editar una configuración
     */
    @GetMapping("/editSettings/{id}")
    public String editSettings(
            @PathVariable Long id,
            Model model) {

        Settings settings =
                settingsService.getSettingsById(id);

        model.addAttribute(
                "settings",
                settings);

        return "settingsForm";
    }

    /*
     * Eliminar una configuración
     */
    @GetMapping("/deleteSettings/{id}")
    public String deleteSettings(
            @PathVariable Long id) {

        settingsService.deleteSettings(id);

        return "redirect:/settingsPage";
    }

}