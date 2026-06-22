package com.info.spring.dar.springboot_aplicacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.info.spring.dar.springboot_aplicacion.entity.Goal;
import com.info.spring.dar.springboot_aplicacion.service.GoalService;

@Controller
public class GoalViewController {

    // Servicio de metas
    private final GoalService goalService;

    // Constructor
    public GoalViewController(GoalService goalService) {
        this.goalService = goalService;
    }

    /*
     * Muestra todas las metas
     */
    @GetMapping("/goalsPage")
    public String showGoals(Model model) {

        model.addAttribute(
                "goals",
                goalService.getAllGoals());

        return "goalsPage";
    }

    /*
     * Muestra el formulario para crear una nueva meta
     */
    @GetMapping("/newGoal")
    public String showGoalForm(Model model) {

        model.addAttribute(
                "goal",
                new Goal());

        return "goalForm";
    }

    /*
     * Guarda una meta en MySQL
     */
    @PostMapping("/saveGoal")
    public String saveGoal(Goal goal) {

        goalService.saveGoal(goal);

        return "redirect:/goalsPage";
    }

    /*
     * Editar una meta
     */
    @GetMapping("/editGoal/{id}")
    public String editGoal(
            @PathVariable Long id,
            Model model) {

        Goal goal =
                goalService.getGoalById(id);

        model.addAttribute(
                "goal",
                goal);

        return "goalForm";
    }

    /*
     * Eliminar una meta
     */
    @GetMapping("/deleteGoal/{id}")
    public String deleteGoal(
            @PathVariable Long id) {

        goalService.deleteGoal(id);

        return "redirect:/goalsPage";
    }

}
