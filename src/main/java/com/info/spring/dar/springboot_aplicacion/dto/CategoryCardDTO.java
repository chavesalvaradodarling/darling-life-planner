package com.info.spring.dar.springboot_aplicacion.dto;

/**
 * Data Transfer Object used to display a category statistics card
 * on the home dashboard.
 *
 * Carries the category name, total real hours logged, color, icon,
 * and a hex color value derived from the color name.
 */
public class CategoryCardDTO {

    /** Name of the category (e.g. "Bailar", "Estudiar"). */
    private String name;

    /** Total real hours logged for this category. */
    private Double hours;

    /**
     * Color name entered by the user (e.g. "Amarillo", "Rosa").
     * Used to generate the CSS class for the card's background color.
     */
    private String color;

    /** Emoji or icon associated with the category (e.g. "💃", "📚"). */
    private String icon;

    /** No-argument constructor. */
    public CategoryCardDTO() {
    }

    /**
     * Full constructor.
     *
     * @param name  the category name
     * @param hours the total real hours for this category
     * @param color the color name (used to generate the CSS class)
     * @param icon  the emoji or icon for this category
     */
    public CategoryCardDTO(String name, Double hours, String color, String icon) {
        this.name = name;
        this.hours = hours;
        this.color = color;
        this.icon = icon;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getHours() { return hours; }
    public void setHours(Double hours) { this.hours = hours; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    /**
     * Returns a hex color code based on the category's color name.
     * Used as a fallback for inline styling when CSS classes are not available.
     * Defaults to the app's main purple (#C9A7EB) for unknown color names.
     *
     * @return hex color string (e.g. "#FFCAD4" for "rosa")
     */
    public String getColorHex() {
        if (color == null) return "#C9A7EB";
        switch (color.toLowerCase()) {
            case "rosa":     return "#FFCAD4";
            case "amarillo": return "#FFD6A5";
            case "morado":   return "#C9A7EB";
            case "celeste":  return "#A8D8EA";
            case "café":     return "#D4A574";
            case "verde":    return "#B5EAD7";
            case "azul":     return "#BDE0FE";
            case "rojo":     return "#FFB3B3";
            case "naranja":  return "#FFC8A2";
            default:         return "#C9A7EB";
        }
    }
}