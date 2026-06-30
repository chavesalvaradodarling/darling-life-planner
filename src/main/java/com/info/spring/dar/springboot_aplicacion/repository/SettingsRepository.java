package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Settings;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the Settings entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 */
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    /**
     * Returns all settings records belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of settings for that user
     */
    List<Settings> findByUser(User user);

    /**
     * Returns all settings records matching the given theme.
     *
     * @param theme the theme name to filter by (e.g. "Claro", "Oscuro")
     * @return list of matching settings
     */
    List<Settings> findByTheme(String theme);
}