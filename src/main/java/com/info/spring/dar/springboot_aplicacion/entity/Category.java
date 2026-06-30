package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a user-defined category stored in the 'categories' table.
 *
 * Categories are used to classify activities and group them on the dashboard.
 * Each category has a name, a color (used for the stat card background), and an icon.
 */
@Entity
@Table(name = "categories")
public class Category {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category.
     * Examples: "Bailar", "Estudiar", "Ejercicio"
     */
    private String name;

    /**
     * Color name associated with the category.
     * Used to apply a CSS class to the stat card on the dashboard.
     * Examples: "Rosa", "Azul", "Amarillo"
     */
    private String color;

    /**
     * Emoji or icon representing the category.
     * Examples: "💃", "📚", "💪"
     */
    private String icon;

    /**
     * List of activities that belong to this category.
     * One category can have many activities.
     */
    @OneToMany(mappedBy = "category")
    private List<Activity> activities = new ArrayList<>();

    /** No-argument constructor required by JPA. */
    public Category() {
    }

    /**
     * Constructor with main fields.
     *
     * @param id    the category ID
     * @param name  the category name
     * @param color the color name
     * @param icon  the emoji or icon
     */
    public Category(Long id, String name, String color, String icon) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public List<Activity> getActivities() { return activities; }
    public void setActivities(List<Activity> activities) { this.activities = activities; }
}