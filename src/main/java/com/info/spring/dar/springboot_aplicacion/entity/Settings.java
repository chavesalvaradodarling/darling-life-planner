package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

/*
 * Esta clase representa la configuración del usuario.
 *
 * Ejemplos:
 * - Tema de la aplicación
 * - Color principal
 * - Mostrar gráficos
 * - Mostrar calendario
 * - Activar notificaciones
 */
@Entity

// Nombre de la tabla
@Table(name = "settings")
public class Settings {

    // Llave primaria
    @Id

    // El id se genera automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tema de la aplicación
    // Ejemplos:
    // Pastel
    // Oscuro
    // Claro
    private String theme;

    // Color principal
    private String mainColor;

    // Mostrar calendario
    private Boolean showCalendar;

    // Mostrar gráficos
    private Boolean showCharts;

    // Activar notificaciones
    private Boolean notifications;

    /*
     * MANY TO ONE
     *
     * Muchas configuraciones pueden pertenecer
     * a un mismo usuario.
     */
    @ManyToOne

    @JoinColumn(name = "user_id")
    private User user;

    // Constructor vacío requerido por JPA
    public Settings() {
    }

    // Constructor
    public Settings(
            Long id,
            String theme,
            String mainColor,
            Boolean showCalendar,
            Boolean showCharts,
            Boolean notifications,
            User user) {

        this.id = id;
        this.theme = theme;
        this.mainColor = mainColor;
        this.showCalendar = showCalendar;
        this.showCharts = showCharts;
        this.notifications = notifications;
        this.user = user;
    }

    // ===== GETTERS Y SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public Boolean getShowCalendar() {
        return showCalendar;
    }

    public void setShowCalendar(Boolean showCalendar) {
        this.showCalendar = showCalendar;
    }

    public Boolean getShowCharts() {
        return showCharts;
    }

    public void setShowCharts(Boolean showCharts) {
        this.showCharts = showCharts;
    }

    public Boolean getNotifications() {
        return notifications;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}