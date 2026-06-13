package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

// Indica que esta clase representa una tabla en la base de datos
@Entity

// Nombre de la tabla
@Table(name = "movies")
public class Movie {

    // Llave primaria
    @Id

    // El id se genera automáticamente (1,2,3,4...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la película
    private String title;

    // Género de la película
    private String genre;

    // Duración en minutos
    private Integer duration;

    // Imagen o portada de la película
    // Aquí se podría guardar una URL o la ruta de la imagen
    private String image;

    /*
     * RELACIÓN MANY TO ONE
     *
     * Muchas películas pueden pertenecer
     * a un mismo usuario.
     *
     * User 1 -------- * Movie
     *
     * Se creará una llave foránea user_id
     * en la tabla movies.
     */
    @ManyToOne

    // Nombre de la llave foránea
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor vacío requerido por JPA
    public Movie() {
    }

    // Constructor con todos los atributos principales
    public Movie(Long id, String title, String genre,
            Integer duration, String image,
            User user) {

        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Getter de user
    public User getUser() {
        return user;
    }

    // Setter de user
    public void setUser(User user) {
        this.user = user;
    }

}
