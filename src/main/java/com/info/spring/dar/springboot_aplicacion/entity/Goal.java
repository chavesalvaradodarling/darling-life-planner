package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

/*
 * Esta clase representa las metas del usuario.
 *
 * Ejemplos:
 * - Estudiar 300 horas
 * - Ir al gimnasio 3 veces por semana
 * - Ver 20 películas
 */
@Entity

// Nombre de la tabla
@Table(name = "goals")
public class Goal {

    // Llave primaria
    @Id

    // El id se genera automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la meta
    private String title;

    // Cantidad objetivo
    private Double target;

    // Cantidad alcanzada
    private Double progress;

    /*
     * MANY TO ONE
     *
     * Muchas metas pueden pertenecer
     * a un mismo usuario.
     */
    @ManyToOne

    @JoinColumn(name = "user_id")
    private User user;

    // Constructor vacío requerido por JPA
    public Goal() {
    }

    // Constructor
    public Goal(
            Long id,
            String title,
            Double target,
            Double progress,
            User user) {

        this.id = id;
        this.title = title;
        this.target = target;
        this.progress = progress;
        this.user = user;
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

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
