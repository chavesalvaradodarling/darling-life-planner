package com.info.spring.dar.springboot_aplicacion.entity;

import jakarta.persistence.*;

/**
 * Entity representing a user profile stored in the 'profiles' table.
 *
 * A profile contains personal information about the user such as
 * their full name, university, career, a personal description, and a profile picture.
 * Each profile belongs to one user.
 */
@Entity
@Table(name = "profiles")
public class Profile {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Full name of the user. */
    private String fullName;

    /** Email address associated with this profile. */
    private String email;

    /** University the user attends. */
    private String university;

    /** Academic career or major. */
    private String career;

    /** Short personal description written by the user. */
    private String description;

    /**
     * Filename of the uploaded profile picture.
     * Stored in the uploads/ directory and accessed via /uploads/{filename}.
     */
    private String image;

    /**
     * The user this profile belongs to.
     * Many profiles can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** No-argument constructor required by JPA. */
    public Profile() {
    }

    /**
     * Full constructor.
     *
     * @param id          the profile ID
     * @param fullName    the user's full name
     * @param email       the user's email
     * @param university  the university name
     * @param career      the academic career
     * @param description a personal description
     * @param image       the profile picture filename
     * @param user        the owner of this profile
     */
    public Profile(Long id, String fullName, String email,
            String university, String career,
            String description, String image, User user) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.university = university;
        this.career = career;
        this.description = description;
        this.image = image;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getCareer() { return career; }
    public void setCareer(String career) { this.career = career; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}