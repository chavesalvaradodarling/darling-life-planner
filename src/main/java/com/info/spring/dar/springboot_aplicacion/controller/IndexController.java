package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.info.spring.dar.springboot_aplicacion.service.ActivityService;

@Controller
public class IndexController {

    private final ActivityService activityService;

    public IndexController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute(
                "totalActivities",
                activityService.getAllActivities().size());

        return "index";

    }

}