package com.centre.controllers;

import com.centre.models.Cours;
import com.centre.models.Cours;
import com.centre.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @GetMapping
    public String listCours(Model model) {
        List<Cours> cours = coursService.findAll();
        model.addAttribute("cours", cours);
        return "cours/list";
    }

    @GetMapping("/editCours/{id}")
    public String editCoursForm(@PathVariable Long id, Model model) {
        Optional<Cours> cours = coursService.findById(id);
        model.addAttribute("cours", cours.orElse(new Cours())); // Si l'id n'existe pas, crée un nouvel objet Cours
        return "cours/editCours";
    }

    @PostMapping("/editCours")
    public String editCours(@ModelAttribute Cours cours) {
        coursService.save(cours);
        return "redirect:/cours";
    }

    @GetMapping("/deleteCours/{id}")
    public String deleteCours (@PathVariable Long id) {
        Optional<Cours> cours = coursService.findById(id);
        cours.ifPresent(c -> coursService.delete(c));
        return "redirect:/cours";
    }

}