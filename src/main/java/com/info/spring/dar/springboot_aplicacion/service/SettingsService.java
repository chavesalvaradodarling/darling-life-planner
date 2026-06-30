package com.info.spring.dar.springboot_aplicacion.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Settings;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.SettingsRepository;

/**
 * Service class that contains the business logic for managing application settings.
 * Communicates with SettingsRepository to access the database.
 * Currently handles the UI theme selection per user.
 */
@Service
public class SettingsService {

    /** Repository used to perform database operations on settings. */
    private final SettingsRepository settingsRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param settingsRepository the repository used to access the settings table
     */
    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    /**
     * Saves a settings record to the database.
     *
     * @param settings the settings object to save
     * @return the saved settings
     */
    public Settings saveSettings(Settings settings) {
        return settingsRepository.save(settings);
    }

    /**
     * Returns all settings records in the system.
     *
     * @return list of all settings
     */
    public List<Settings> getAllSettings() {
        return settingsRepository.findAll();
    }

    /**
     * Returns a single settings record by its ID, or null if not found.
     *
     * @param id the settings ID
     * @return the matching settings or null
     */
    public Settings getSettingsById(Long id) {
        return settingsRepository.findById(id).orElse(null);
    }

    /**
     * Returns all settings records belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of settings for that user
     */
    public List<Settings> getSettingsByUser(User user) {
        return settingsRepository.findByUser(user);
    }

    /**
     * Deletes a settings record by its ID.
     *
     * @param id the ID of the settings to delete
     */
    public void deleteSettings(Long id) {
        settingsRepository.deleteById(id);
    }
}