package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.info.spring.dar.springboot_aplicacion.entity.Settings;
import com.info.spring.dar.springboot_aplicacion.service.SettingsService;

/**
 * MVC controller that handles the settings views and form submissions.
 * Manages the settings list, creation, editing, and deletion.
 */
@Controller
public class SettingsViewController {

    /** Service that handles all settings-related business logic. */
    private final SettingsService settingsService;

    /**
     * Constructor with dependency injection.
     *
     * @param settingsService the service used to manage settings
     */
    public SettingsViewController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    /**
     * Displays all settings records.
     * GET /settingsPage
     *
     * @param model the Spring MVC model
     * @return the settings list view template
     */
    @GetMapping("/settingsPage")
    public String showSettings(Model model) {
        model.addAttribute("settingsList", settingsService.getAllSettings());
        return "settingsPage";
    }

    /**
     * Displays the form to create a new settings record.
     * GET /newSettings
     *
     * @param model the Spring MVC model
     * @return the settings form template
     */
    @GetMapping("/newSettings")
    public String showSettingsForm(Model model) {
        model.addAttribute("settings", new Settings());
        return "settingsForm";
    }

    /**
     * Saves a new or edited settings record.
     * POST /saveSettings
     *
     * @param settings the settings object bound from the form
     * @return redirect to the settings page
     */
    @PostMapping("/saveSettings")
    public String saveSettings(Settings settings) {
        settingsService.saveSettings(settings);
        return "redirect:/settingsPage";
    }

    /**
     * Displays the form pre-filled with an existing settings record for editing.
     * GET /editSettings/{id}
     *
     * @param id    the ID of the settings record to edit
     * @param model the Spring MVC model
     * @return the settings form template
     */
    @GetMapping("/editSettings/{id}")
    public String editSettings(@PathVariable Long id, Model model) {
        Settings settings = settingsService.getSettingsById(id);
        model.addAttribute("settings", settings);
        return "settingsForm";
    }

    /**
     * Deletes a settings record by its ID.
     * GET /deleteSettings/{id}
     *
     * @param id the ID of the settings record to delete
     * @return redirect to the settings page
     */
    @GetMapping("/deleteSettings/{id}")
    public String deleteSettings(@PathVariable Long id) {
        settingsService.deleteSettings(id);
        return "redirect:/settingsPage";
    }
}