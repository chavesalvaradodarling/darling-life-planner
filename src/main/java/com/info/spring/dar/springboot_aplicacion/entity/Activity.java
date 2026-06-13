package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

// Indica que esta clase será una tabla en la base de datos
@Entity

// Nombre que tendrá la tabla en MySQL
@Table(name = "activities")
public class Activity {

    // Llave primaria de la tabla
    @Id

    // El id se generará automáticamente (1,2,3,4...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título de la actividad
    // Ejemplos:
    // "Estudiar Cálculo"
    // "Ir al gimnasio"
    // "Ver Interstellar"
    private String title;

    // Descripción de la actividad
    private String description;

    // Fecha en la que se realizará la actividad
    private String activityDate;

    // Hora de inicio
    private String startTime;

    // Hora de finalización
    private String endTime;

    // Horas que se planificaron inicialmente
    private Double plannedHours;

    // Horas realmente realizadas
    private Double realHours;

    // Estado de la actividad
    // COMPLETED
    // PARTIALLY_COMPLETED
    // NOT_COMPLETED
    private String status;

    // Prioridad de la actividad
    // LOW
    // MEDIUM
    // HIGH
    private String priority;

    // Si la actividad fue parcialmente completada,
    // aquí se almacena qué quedó pendiente
    private String pendingDescription;

    /*
     * RELACIÓN MANY TO ONE
     *
     * Muchas actividades pueden pertenecer
     * a una misma planificación.
     *
     * Planning 1 -------- * Activity
     *
     * Se creará una llave foránea planning_id
     * en la tabla activities.
     */
    @ManyToOne

    // Nombre de la llave foránea
    @JoinColumn(name = "planning_id")
    private Planning planning;

    /*
     * RELACIÓN MANY TO ONE
     *
     * Muchas actividades pueden pertenecer
     * a una misma categoría.
     *
     * Category 1 -------- * Activity
     *
     * Se creará una llave foránea category_id
     * en la tabla activities.
     */
    @ManyToOne

    // Nombre de la llave foránea
    @JoinColumn(name = "category_id")
    private Category category;

    /*
     * RELACIÓN MANY TO ONE
     *
     * Muchas actividades pueden pertenecer
     * a un mismo curso.
     *
     * Esta relación es opcional porque no
     * todas las actividades son académicas.
     *
     * Course 1 -------- * Activity
     *
     * Se creará una llave foránea course_id
     * en la tabla activities.
     */
    @ManyToOne

    // Nombre de la llave foránea
    @JoinColumn(name = "course_id")
    private Course course;

    // Constructor vacío requerido por JPA
    public Activity() {
    }

    // Constructor con todos los atributos principales
    public Activity(Long id, String title, String description,
            String activityDate, String startTime,
            String endTime, Double plannedHours,
            Double realHours, String status,
            String priority, String pendingDescription,
            Planning planning, Category category,
            Course course) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.activityDate = activityDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.plannedHours = plannedHours;
        this.realHours = realHours;
        this.status = status;
        this.priority = priority;
        this.pendingDescription = pendingDescription;
        this.planning = planning;
        this.category = category;
        this.course = course;
    }

    // ===== GETTERS Y SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getPlannedHours() {
        return plannedHours;
    }

    public void setPlannedHours(Double plannedHours) {
        this.plannedHours = plannedHours;
    }

    public Double getRealHours() {
        return realHours;
    }

    public void setRealHours(Double realHours) {
        this.realHours = realHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPendingDescription() {
        return pendingDescription;
    }

    public void setPendingDescription(String pendingDescription) {
        this.pendingDescription = pendingDescription;
    }

    // Getter de planning
    public Planning getPlanning() {
        return planning;
    }

    // Setter de planning
    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    // Getter de category
    public Category getCategory() {
        return category;
    }

    // Setter de category
    public void setCategory(Category category) {
        this.category = category;
    }

    // Getter de course
    public Course getCourse() {
        return course;
    }

    // Setter de course
    public void setCourse(Course course) {
        this.course = course;
    }

}