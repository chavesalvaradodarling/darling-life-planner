package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.info.spring.dar.springboot_aplicacion.dto.ReportDTO;
import com.info.spring.dar.springboot_aplicacion.service.ReportService;

/**
 * MVC controller that handles the reports page.
 * Generates and displays a summary report of the user's activities.
 */
@Controller
public class ReportViewController {

    /** Service that generates the activity report. */
    private final ReportService reportService;

    /**
     * Constructor with dependency injection.
     *
     * @param reportService the service used to generate reports
     */
    public ReportViewController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Generates and displays the activity report.
     * GET /reportsPage
     *
     * @param model the Spring MVC model
     * @return the reports view template
     */
    @GetMapping("/reportsPage")
    public String showReports(Model model) {
        ReportDTO report = reportService.generateReport();
        model.addAttribute("report", report);
        return "reports";
    }
}