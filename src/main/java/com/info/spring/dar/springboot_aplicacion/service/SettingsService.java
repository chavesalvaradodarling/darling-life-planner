package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Settings;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.SettingsRepository;

/*
 * Esta clase contiene la lógica relacionada con las configuraciones.
 *
 * Se comunica con SettingsRepository para acceder a la base de datos.
 */
@Service
public class SettingsService {

    // Objeto que permite acceder a la tabla settings
    private final SettingsRepository settingsRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    /*
     * Guarda una configuración en la base de datos.
     */
    public Settings saveSettings(Settings settings) {
        return settingsRepository.save(settings);
    }

    /*
     * Devuelve todas las configuraciones registradas.
     */
    public List<Settings> getAllSettings() {
        return settingsRepository.findAll();
    }

    /*
     * Busca una configuración por su id.
     *
     * Si no existe, devuelve null.
     */
    public Settings getSettingsById(Long id) {
        return settingsRepository.findById(id).orElse(null);
    }

    /*
     * Devuelve todas las configuraciones
     * pertenecientes a un usuario.
     */
    public List<Settings> getSettingsByUser(User user) {
        return settingsRepository.findByUser(user);
    }

    /*
     * Busca configuraciones por tema.
     *
     * Ejemplos:
     * Pastel
     * Oscuro
     * Claro
     */
    public List<Settings> getSettingsByTheme(String theme) {
        return settingsRepository.findByTheme(theme);
    }

    /*
     * Elimina una configuración por su id.
     */
    public void deleteSettings(Long id) {
        settingsRepository.deleteById(id);
    }

}