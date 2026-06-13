package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

// Importa List y ArrayList para relaciones OneToMany
import java.util.ArrayList;
import java.util.List;

// Indica que esta clase será una tabla en la base de datos
@Entity

// Nombre que tendrá la tabla en MySQL
@Table(name = "courses")
public class Course {

    // Llave primaria
    @Id

    // El id se genera automáticamente (1,2,3,4...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del curso
    // Ejemplo:
    // Cálculo I
    // Programación III
    private String name;

    // Código del curso
    private String code;

    // Cantidad de créditos
    private Integer credits;

    // Nombre del profesor
    private String teacher;

    /*
     * RELACIÓN MANY TO ONE
     *
     * Muchos cursos pueden pertenecer a un mismo usuario.
     *
     * User 1 -------- * Course
     *
     * Se creará una llave foránea llamada user_id
     * en la tabla courses.
     */
    @ManyToOne

    // Nombre de la llave foránea
    @JoinColumn(name = "user_id")
    private User user;

    /*
     * RELACIÓN ONE TO MANY
     *
     * Un curso puede tener muchas actividades.
     *
     * Course 1 -------- * Activity
     */
    @OneToMany(mappedBy = "course")
    private List<Activity> activities = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Course() {
    }

    // Constructor con todos los atributos principales
    public Course(Long id, String name, String code,
            Integer credits, String teacher, User user) {

        this.id = id;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.teacher = teacher;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
