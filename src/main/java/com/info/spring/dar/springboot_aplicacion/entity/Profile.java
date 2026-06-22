package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

/*
 * Esta clase representa el perfil del usuario.
 *
 * Ejemplos:
 * - Nombre
 * - Correo
 * - Universidad
 * - Carrera
 * - Descripción personal
 * - Foto de perfil
 */
@Entity

// Nombre de la tabla
@Table(name = "profiles")
public class Profile {

    // Llave primaria
    @Id

    // El id se genera automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo
    private String fullName;

    // Correo electrónico
    private String email;

    // Universidad
    private String university;

    // Carrera
    private String career;

    // Descripción personal
    private String description;

    // Imagen o foto de perfil
    private String image;

    /*
     * MANY TO ONE
     *
     * Muchos perfiles pueden pertenecer
     * a un mismo usuario.
     */
    @ManyToOne

    @JoinColumn(name = "user_id")
    private User user;

    // Constructor vacío requerido por JPA
    public Profile() {
    }

    // Constructor
    public Profile(
            Long id,
            String fullName,
            String email,
            String university,
            String career,
            String description,
            String image,
            User user) {

        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.university = university;
        this.career = career;
        this.description = description;
        this.image = image;
        this.user = user;
    }

    // ===== GETTERS Y SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
