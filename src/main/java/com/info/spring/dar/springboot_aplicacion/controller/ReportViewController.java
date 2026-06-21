package com.info.spring.dar.springboot_aplicacion.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.info.spring.dar.springboot_aplicacion.entity.Activity;
import com.info.spring.dar.springboot_aplicacion.service.ActivityService;

@Controller
public class ReportViewController {

    private final ActivityService activityService;

    public ReportViewController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/reportsPage")
    public String showReports(Model model) {

        List<Activity> activities =
                activityService.getAllActivities();

        int completed = 0;
        int partial = 0;
        int pending = 0;

        double studyHours = 0;
        double exerciseHours = 0;
        double leisureHours = 0;

        for (Activity activity : activities) {

            // Contar estados
            if (activity.getStatus().equals("COMPLETED")) {

                completed++;

            }

            else if (activity.getStatus().equals("PARTIALLY_COMPLETED")) {

                partial++;

            }

            else if (activity.getStatus().equals("NOT_COMPLETED")) {

                pending++;

            }

            // Sumar horas por categoría
            if (activity.getCategory() != null) {

                String categoryName =
                        activity.getCategory().getName();

                if (categoryName.equalsIgnoreCase("Estudio")) {

                    studyHours += activity.getRealHours();

                }

                if (categoryName.equalsIgnoreCase("Ejercicio")) {

                    exerciseHours += activity.getRealHours();

                }

                if (categoryName.equalsIgnoreCase("Ocio")) {

                    leisureHours += activity.getRealHours();

                }

            }

        }

        model.addAttribute(
                "totalActivities",
                activities.size());

        model.addAttribute(
                "completedActivities",
                completed);

        model.addAttribute(
                "partialActivities",
                partial);

        model.addAttribute(
                "pendingActivities",
                pending);

        model.addAttribute(
                "studyHours",
                studyHours);

        model.addAttribute(
                "exerciseHours",
                exerciseHours);

        model.addAttribute(
                "leisureHours",
                leisureHours);

        return "reports";
    }

}