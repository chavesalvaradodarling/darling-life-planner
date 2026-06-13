package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

// Importa List y ArrayList para manejar relaciones OneToMany
import java.util.ArrayList;
import java.util.List;

// Indica que esta clase será una tabla en la base de datos
@Entity

// Nombre que tendrá la tabla en MySQL
@Table(name = "users")
public class User {

    // Llave primaria de la tabla
    @Id

    // El id se generará automáticamente (1, 2, 3, ...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del usuario
    private String name;

    // El email no puede repetirse y es obligatorio
    @Column(unique = true, nullable = false)
    private String email;

    // La contraseña es obligatoria
    @Column(nullable = false)
    private String password;

    // Rol del usuario
    // Ejemplos:
    // ADMIN
    // USER
    private String role;

    /*
     * RELACIÓN ONE TO MANY
     *
     * Un usuario puede tener muchas planificaciones.
     *
     * User 1 -------- * Planning
     */
    @OneToMany(mappedBy = "user")
    private List<Planning> plannings = new ArrayList<>();

    /*
     * RELACIÓN ONE TO MANY
     *
     * Un usuario puede tener muchos cursos.
     *
     * User 1 -------- * Course
     */
    @OneToMany(mappedBy = "user")
    private List<Course> courses = new ArrayList<>();

    /*
     * RELACIÓN ONE TO MANY
     *
     * Un usuario puede tener muchas películas.
     *
     * User 1 -------- * Movie
     */
    @OneToMany(mappedBy = "user")
    private List<Movie> movies = new ArrayList<>();

    /*
     * RELACIÓN ONE TO MANY
     *
     * Un usuario puede tener muchos viajes.
     *
     * User 1 -------- * Trip
     */
    @OneToMany(mappedBy = "user")
    private List<Trip> trips = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public User() {
    }

    // Constructor con los atributos principales
    public User(Long id, String name, String email,
            String password, String role) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Planning> getPlannings() {
        return plannings;
    }

    public void setPlannings(List<Planning> plannings) {
        this.plannings = plannings;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

}
