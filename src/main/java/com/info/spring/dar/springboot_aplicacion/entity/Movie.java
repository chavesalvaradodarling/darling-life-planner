package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a movie stored in the 'movies' table.
 *
 * Movies are created by the user and can optionally be associated
 * with activities. The image field stores a URL pointing to the movie poster.
 * Each movie belongs to one user.
 */
@Entity
@Table(name = "movies")
public class Movie {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Title of the movie. Example: "Interstellar" */
    private String title;

    /** Genre of the movie. Example: "Ficción", "Drama" */
    private String genre;

    /** Duration of the movie in minutes. */
    private Integer duration;

    /**
     * URL or path to the movie poster image.
     * Displayed on the weekly calendar when the activity is linked to this movie.
     */
    private String image;

    /**
     * The user who added this movie.
     * Many movies can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * List of activities associated with this movie.
     * One movie can be linked to many activities.
     */
    @OneToMany(mappedBy = "movie")
    private List<Activity> activities = new ArrayList<>();

    /** No-argument constructor required by JPA. */
    public Movie() {
    }

    /**
     * Full constructor.
     *
     * @param id       the movie ID
     * @param title    the movie title
     * @param genre    the movie genre
     * @param duration the duration in minutes
     * @param image    the image URL or path
     * @param user     the owner of this movie
     */
    public Movie(Long id, String title, String genre,
            Integer duration, String image, User user) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.image = image;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Activity> getActivities() { return activities; }
    public void setActivities(List<Activity> activities) { this.activities = activities; }
}