package com.info.spring.dar.springboot_aplicacion.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.info.spring.dar.springboot_aplicacion.entity.Profile;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.repository.ProfileRepository;

/**
 * Service class that contains the business logic for managing user profiles.
 * Communicates with ProfileRepository to access the database.
 */
@Service
public class ProfileService {

    /** Repository used to perform database operations on profiles. */
    private final ProfileRepository profileRepository;

    /**
     * Constructor with dependency injection.
     *
     * @param profileRepository the repository used to access the profiles table
     */
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Saves a profile to the database.
     *
     * @param profile the profile to save
     * @return the saved profile
     */
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    /**
     * Returns all profiles in the system.
     *
     * @return list of all profiles
     */
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    /**
     * Returns a single profile by its ID, or null if not found.
     *
     * @param id the profile ID
     * @return the matching profile or null
     */
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    /**
     * Returns all profiles belonging to a specific user entity.
     *
     * @param user the user to filter by
     * @return list of profiles for that user
     */
    public List<Profile> getProfilesByUser(User user) {
        return profileRepository.findByUser(user);
    }

    /**
     * Returns all profiles matching the given full name.
     *
     * @param fullName the full name to search for
     * @return list of matching profiles
     */
    public List<Profile> getProfilesByFullName(String fullName) {
        return profileRepository.findByFullName(fullName);
    }

    /**
     * Returns the profile associated with the given email address.
     * Used by GlobalControllerAdvice to load the active user's profile.
     *
     * @param email the email to search for
     * @return the matching profile or null
     */
    public Profile getProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    /**
     * Deletes a profile by its ID.
     *
     * @param id the ID of the profile to delete
     */
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}