package com.info.spring.dar.springboot_aplicacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.info.spring.dar.springboot_aplicacion.entity.Profile;
import com.info.spring.dar.springboot_aplicacion.entity.User;

/**
 * Repository interface for the Profile entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations:
 * save(), findById(), findAll(), deleteById(), existsById().
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    /**
     * Returns all profiles belonging to a specific user.
     *
     * @param user the user to filter by
     * @return list of profiles for that user
     */
    List<Profile> findByUser(User user);

    /**
     * Returns all profiles matching the given full name.
     *
     * @param fullName the full name to search for
     * @return list of matching profiles
     */
    List<Profile> findByFullName(String fullName);

    /**
     * Returns the profile associated with the given email address.
     * Used by GlobalControllerAdvice to load the active profile.
     *
     * @param email the email to search for
     * @return the matching profile, or null if not found
     */
    Profile findByEmail(String email);
}