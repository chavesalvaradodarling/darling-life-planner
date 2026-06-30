package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.info.spring.dar.springboot_aplicacion.entity.Profile;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.ProfileService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;
import java.nio.file.*;
import java.util.UUID;
import java.io.IOException;

/**
 * MVC controller that handles the user profile views and form submissions.
 * Manages profile creation, editing, deletion, and profile picture uploads.
 */
@Controller
public class ProfileViewController {

    private final ProfileService profileService;
    private final UserService userService;

    /** Directory where uploaded profile pictures are stored. */
    private final String UPLOAD_DIR = "uploads/";

    /**
     * Constructor with dependency injection.
     *
     * @param profileService the service used to manage profiles
     * @param userService    the service used to get the current user
     */
    public ProfileViewController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    /**
     * Displays all profiles belonging to the currently logged-in user.
     * GET /profilesPage
     *
     * @param model the Spring MVC model
     * @return the profiles list view template
     */
    @GetMapping("/profilesPage")
    public String showProfiles(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("profiles", profileService.getProfilesByUser(user));
        return "profilesPages";
    }

    /**
     * Displays the form to create a new profile.
     * Pre-fills the email field with the current user's email.
     * GET /newProfile
     *
     * @param model the Spring MVC model
     * @return the profile form template
     */
    @GetMapping("/newProfile")
    public String showProfileForm(Model model) {
        User user = userService.getCurrentUser();
        Profile profile = new Profile();
        if (user != null) {
            profile.setEmail(user.getEmail());
            profile.setUser(user);
        }
        model.addAttribute("profile", profile);
        return "profileForm";
    }

    /**
     * Saves a new or updated profile for the current user.
     * If a profile already exists for this user, it is updated instead of duplicated.
     * If a new image file is uploaded, it is saved to the uploads/ directory with a unique name.
     * If no new image is uploaded, the existing image is preserved.
     * POST /saveProfile
     *
     * @param profile the profile object bound from the form
     * @param file    the uploaded profile picture (may be empty)
     * @return redirect to the profiles page
     */
    @PostMapping("/saveProfile")
    public String saveProfile(@ModelAttribute Profile profile,
            @RequestParam("file") MultipartFile file) {

        User user = userService.getCurrentUser();

        if (user != null) {

            Profile existingProfile = profileService.getProfileByEmail(user.getEmail());

            // If a profile already exists, update it instead of creating a new one
            if (existingProfile != null) {
                profile.setId(existingProfile.getId());

                // Keep the existing image if no new one was uploaded
                if (file.isEmpty()) {
                    profile.setImage(existingProfile.getImage());
                }
            }

            profile.setUser(user);
            profile.setEmail(user.getEmail());
        }

        // Handle the uploaded image file
        if (!file.isEmpty()) {
            try {
                Files.createDirectories(Paths.get(UPLOAD_DIR));

                // Generate a unique filename to avoid collisions
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

                Files.copy(
                        file.getInputStream(),
                        Paths.get(UPLOAD_DIR + fileName),
                        StandardCopyOption.REPLACE_EXISTING);

                profile.setImage(fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        profileService.saveProfile(profile);

        return "redirect:/profilesPage";
    }

    /**
     * Displays the form pre-filled with an existing profile's data for editing.
     * GET /editProfile/{id}
     *
     * @param id    the ID of the profile to edit
     * @param model the Spring MVC model
     * @return the profile form template
     */
    @GetMapping("/editProfile/{id}")
    public String editProfile(@PathVariable Long id, Model model) {
        Profile profile = profileService.getProfileById(id);
        User user = userService.getCurrentUser();
        if (user != null) {
            profile.setEmail(user.getEmail());
        }
        model.addAttribute("profile", profile);
        return "profileForm";
    }

    /**
     * Deletes a profile by its ID.
     * GET /deleteProfile/{id}
     *
     * @param id the ID of the profile to delete
     * @return redirect to the profiles page
     */
    @GetMapping("/deleteProfile/{id}")
    public String deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return "redirect:/profilesPage";
    }
}