package com.info.spring.dar.springboot_aplicacion.config;

import com.info.spring.dar.springboot_aplicacion.entity.Profile;
import com.info.spring.dar.springboot_aplicacion.service.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Global controller advice that injects shared model attributes
 * into every controller in the application.
 *
 * This class runs before every request and makes the active user's
 * profile available in all Thymeleaf templates via ${activeProfile}.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    /** Service used to retrieve profile information from the database. */
    private final ProfileService profileService;

    /**
     * Constructor with dependency injection.
     *
     * @param profileService the service that handles profile operations
     */
    public GlobalControllerAdvice(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * Adds the currently authenticated user's profile to the model
     * so it is accessible in all views as ${activeProfile}.
     *
     * @param authentication the current authentication object provided by Spring Security
     * @return the Profile of the logged-in user, or null if no user is authenticated
     */
    @ModelAttribute("activeProfile")
    public Profile getActiveProfile(Authentication authentication) {

        // Check that a user is currently logged in
        if (authentication != null && authentication.isAuthenticated()) {

            // Retrieve the profile using the authenticated user's email
            return profileService.getProfileByEmail(authentication.getName());
        }

        // Return null if no user is authenticated
        return null;
    }
}