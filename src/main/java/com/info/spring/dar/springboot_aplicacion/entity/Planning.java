package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

// Importa List y ArrayList para manejar relaciones OneToMany
import java.util.ArrayList;
import java.util.List;

// Indica que esta clase será una tabla en la base de datos
@Entity

// Nombre que tendrá la tabla en MySQL
@Table(name = "planning")
public class Planning {

    // Llave primaria de la tabla
    @Id

    // El id se genera automáticamente (1,2,3,4...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la planificación
    // Ejemplos:
    // "Semestre I 2027"
    // "Semana de Exámenes"
    private String name;

    // Descripción de la planificación
    private String description;

    // Objetivo de la planificación
    private String goal;

    // Tipo de planificación
    // WEEKLY
    // MONTHLY
    // BIMONTHLY
    // SEMESTER
    private String type;

    // Fecha de inicio de la planificación
    private String startDate;

    // Fecha de finalización
    private String endDate;

    /*
     * RELACIÓN MANY TO ONE
     *
     * Muchas planificaciones pueden pertenecer
     * a un mismo usuario.
     *
     * User 1 -------- * Planning
     *
     * En la tabla planning se creará una llave
     * foránea llamada user_id.
     */
    @ManyToOne

    // Nombre de la llave foránea
    @JoinColumn(name = "user_id")
    private User user;

    /*
     * RELACIÓN ONE TO MANY
     *
     * Una planificación puede tener muchas
     * actividades.
     *
     * Planning 1 -------- * Activity
     */
    @OneToMany(mappedBy = "planning")
    private List<Activity> activities = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Planning() {
    }

    // Constructor con los atributos principales
    public Planning(Long id, String name, String description,
            String goal, String type, String startDate,
            String endDate, User user) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    // ===== GETTERS Y SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // Getter de user
    public User getUser() {
        return user;
    }

    // Setter de user
    public void setUser(User user) {
        this.user = user;
    }

    // Getter de activities
    public List<Activity> getActivities() {
        return activities;
    }

    // Setter de activities
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
