package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.entity.Category;
import com.info.spring.dar.springboot_aplicacion.entity.Course;
import com.info.spring.dar.springboot_aplicacion.entity.Movie;
import com.info.spring.dar.springboot_aplicacion.entity.Planning;
import com.info.spring.dar.springboot_aplicacion.entity.Trip;
import com.info.spring.dar.springboot_aplicacion.entity.User;
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;
import com.info.spring.dar.springboot_aplicacion.service.CategoryService;
import com.info.spring.dar.springboot_aplicacion.service.CourseService;
import com.info.spring.dar.springboot_aplicacion.service.MovieService;
import com.info.spring.dar.springboot_aplicacion.service.PlanningService;
import com.info.spring.dar.springboot_aplicacion.service.TripService;
import com.info.spring.dar.springboot_aplicacion.service.UserService;

/**
 * MVC controller that handles the activity views and form submissions.
 * Manages the activity list, creation, editing, completion, and deletion.
 */
@Controller
public class ActivityViewController {

    private final ActivityService activityService;
    private final CategoryService categoryService;
    private final PlanningService planningService;
    private final CourseService courseService;
    private final MovieService movieService;
    private final TripService tripService;
    private final UserService userService;

    /**
     * Constructor with dependency injection for all required services.
     */
    public ActivityViewController(
            ActivityService activityService,
            CategoryService categoryService,
            PlanningService planningService,
            CourseService courseService,
            MovieService movieService,
            TripService tripService,
            UserService userService) {

        this.activityService = activityService;
        this.categoryService = categoryService;
        this.planningService = planningService;
        this.courseService = courseService;
        this.movieService = movieService;
        this.tripService = tripService;
        this.userService = userService;
    }

    /**
     * Displays all activities belonging to the currently logged-in user.
     * GET /activitiesPage
     *
     * @param model the Spring MVC model
     * @return the activities view template
     */
    @GetMapping("/activitiesPage")
    public String showActivities(Model model) {

        User user = userService.getCurrentUser();

        model.addAttribute(
                "activities",
                activityService.getActivitiesByUser(user));

        return "activities";
    }

    /**
     * Displays the form to create a new activity.
     * Loads all related entities (categories, plannings, courses, movies, trips)
     * so the user can optionally associate them with the activity.
     * GET /newActivity
     *
     * @param model the Spring MVC model
     * @return the activity form template
     */
    @GetMapping("/newActivity")
    public String showActivityForm(Model model) {

        User user = userService.getCurrentUser();

        model.addAttribute("activity", new Activity());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("plannings", planningService.getPlanningsByUser(user));
        model.addAttribute("courses", courseService.getCoursesByUser(user));
        model.addAttribute("movies", movieService.getMoviesByUser(user));
        model.addAttribute("trips", tripService.getTripsByUser(user));

        return "activityForm";
    }

    /**
     * Saves a new or edited activity.
     * Clears all optional associations first, then sets only the ones
     * that were selected by the user. If a trip is selected and the
     * activity has no date, the trip's departure date is used.
     * POST /saveActivity
     *
     * @param activity   the activity object bound from the form
     * @param categoryId the ID of the selected category (required)
     * @param planningId the ID of the selected planning (optional)
     * @param courseId   the ID of the selected course (optional)
     * @param movieId    the ID of the selected movie (optional)
     * @param tripId     the ID of the selected trip (optional)
     * @return redirect to the activities page
     */
    @PostMapping("/saveActivity")
    public String saveActivity(
            Activity activity,
            @RequestParam("category.id") Long categoryId,
            @RequestParam(required = false, value = "planning.id") Long planningId,
            @RequestParam(required = false, value = "course.id") Long courseId,
            @RequestParam(required = false, value = "movie.id") Long movieId,
            @RequestParam(required = false, value = "trip.id") Long tripId) {

        // Clear all optional associations before setting the selected ones
        activity.setCourse(null);
        activity.setMovie(null);
        activity.setTrip(null);
        activity.setPlanning(null);

        // Category is required
        Category category = categoryService.getCategoryById(categoryId);
        activity.setCategory(category);

        // Planning is optional
        if (planningId != null) {
            Planning planning = planningService.getPlanningById(planningId);
            activity.setPlanning(planning);
        }

        // Course is optional
        if (courseId != null) {
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                activity.setCourse(course);
            }
        }

        // Movie is optional
        if (movieId != null) {
            Movie movie = movieService.getMovieById(movieId);
            if (movie != null) {
                activity.setMovie(movie);
            }
        }

        // Trip is optional — if selected and no date was set, use the trip's departure date
        if (tripId != null) {
            Trip trip = tripService.getTripById(tripId);
            if (trip != null) {
                activity.setTrip(trip);
                if (activity.getActivityDate() == null || activity.getActivityDate().isBlank()) {
                    activity.setActivityDate(trip.getDepartureDate());
                }
            }
        }

        // Assign the currently authenticated user
        User user = userService.getCurrentUser();
        activity.setUser(user);

        // Set default status if not provided
        if (activity.getStatus() == null || activity.getStatus().isBlank()) {
            activity.setStatus("PENDING");
        }

        activityService.saveActivity(activity);

        return "redirect:/activitiesPage";
    }

    /**
     * Displays the form pre-filled with an existing activity's data for editing.
     * GET /editActivity/{id}
     *
     * @param id    the ID of the activity to edit
     * @param model the Spring MVC model
     * @return the activity form template
     */
    @GetMapping("/editActivity/{id}")
    public String editActivity(@PathVariable Long id, Model model) {

        User user = userService.getCurrentUser();
        Activity activity = activityService.getActivityById(id);

        model.addAttribute("activity", activity);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("plannings", planningService.getPlanningsByUser(user));
        model.addAttribute("courses", courseService.getCoursesByUser(user));
        model.addAttribute("movies", movieService.getMoviesByUser(user));
        model.addAttribute("trips", tripService.getTripsByUser(user));

        return "activityForm";
    }

    /**
     * Marks an activity as completed.
     * GET /completeActivity/{id}
     *
     * @param id the ID of the activity to complete
     * @return redirect to the activities page
     */
    @GetMapping("/completeActivity/{id}")
    public String completeActivity(@PathVariable Long id) {
        activityService.completeActivity(id);
        return "redirect:/activitiesPage";
    }

    /**
     * Deletes an activity by its ID.
     * GET /deleteActivity/{id}
     *
     * @param id the ID of the activity to delete
     * @return redirect to the activities page
     */
    @GetMapping("/deleteActivity/{id}")
    public String deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return "redirect:/activitiesPage";
    }
}