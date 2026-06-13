package com.info.spring.dar.springboot_aplicacion.entity;

// Importa las anotaciones necesarias de JPA
import jakarta.persistence.*;

// Indica que esta clase representa una tabla de la base de datos
@Entity

// Nombre de la tabla en MySQL
@Table(name = "trips")
public class Trip {

    // Llave primaria
    @Id

    // El id se genera automáticamente (1,2,3,4...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Destino del viaje
    // Ejemplo:
    // Japón
    // Guanacaste
    // Cartago
    private String destination;

    // Fecha de salida
    private String departureDate;

    // Fecha de regreso
    private String returnDate;

    /*
     * RELACIÓN MANY TO ONE
     *
     * Muchos viajes pueden pertenecer
     * a un mismo usuario.
     *
     * User 1 -------- * Trip
     *
     * En la tabla trips se creará
     * una llave foránea llamada user_id.
     */
    @ManyToOne

    // Nombre de la llave foránea
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor vacío requerido por JPA
    public Trip() {
    }

    // Constructor con los atributos principales
    public Trip(Long id, String destination,
            String departureDate, String returnDate,
            User user) {

        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.user = user;
    }

    // ===== GETTERS Y SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
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