package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;

/**
 * Entity representing user application settings stored in the 'settings' table.
 *
 * Currently stores the selected UI theme for the user.
 * Each settings record belongs to one user.
 */
@Entity
@Table(name = "settings")
public class Settings {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The selected UI theme.
     * Examples: "Claro", "Oscuro"
     */
    private String theme;

    /**
     * The user these settings belong to.
     * Many settings records can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** No-argument constructor required by JPA. */
    public Settings() {}

    /**
     * Full constructor.
     *
     * @param id    the settings ID
     * @param theme the selected theme
     * @param user  the owner of these settings
     */
    public Settings(Long id, String theme, User user) {
        this.id = id;
        this.theme = theme;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}