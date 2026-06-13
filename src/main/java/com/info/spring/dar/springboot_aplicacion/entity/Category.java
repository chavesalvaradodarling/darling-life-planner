package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

// Importa List y ArrayList para relaciones OneToMany
import java.util.ArrayList;
import java.util.List;

// Indica que esta clase representa una tabla en la base de datos
@Entity

// Nombre de la tabla
@Table(name = "categories")
public class Category {

    // Llave primaria
    @Id

    // El id se genera automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la categoría
    private String name;

    // Color asociado a la categoría
    // Ejemplo: rosa, azul, verde, morado
    private String color;

    // Nombre o referencia del icono
    private String icon;

    /*
     * RELACIÓN ONE TO MANY
     *
     * Una categoría puede tener muchas actividades.
     *
     * Category 1 -------- * Activity
     */
    @OneToMany(mappedBy = "category")
    private List<Activity> activities = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Category() {
    }

    // Constructor con los atributos principales
    public Category(Long id, String name, String color, String icon) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.icon = icon;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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