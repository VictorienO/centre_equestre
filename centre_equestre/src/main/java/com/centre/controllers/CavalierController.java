package com.centre.controllers;

import com.centre.models.Cavalier;
import com.centre.services.CavalierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cavaliers")
public class CavalierController {

    @Autowired
    private CavalierService cavalierService;

    @GetMapping
    public String listCavaliers(Model model) {
        List<Cavalier> cavaliers = cavalierService.findAll();
        model.addAttribute("cavaliers", cavaliers);
        return "listCavaliers";
    }

    @GetMapping("/addCavalier")
    public String addCavalierForm(Model model) {
        model.addAttribute("cavalier", new Cavalier());
        return "addCavalier"; // Assurez-vous d'avoir une vue nommée "addCavalier.html"
    }

    @PostMapping("/addCavalier")
    public String addCavalier(@ModelAttribute Cavalier cavalier) {
        cavalierService.save(cavalier);
        return "redirect:/cavaliers"; // Redirection vers la liste des cavaliers
    }

    @GetMapping("/editCavaliers/{id}")
    public String editCavalierForm(@PathVariable Long id, Model model) {
        Optional<Cavalier> cavalier = cavalierService.findById(id);
        model.addAttribute("cavalier", cavalier.orElse(new Cavalier())); // Si l'id n'existe pas, crée un nouvel objet Cavalier
        return "editCavalier";
    }

    @PostMapping("/editCavaliers")
    public String editCavalier(@ModelAttribute Cavalier cavalier) {
        cavalierService.save(cavalier);
        return "redirect:/cavaliers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCavalier(@PathVariable Long id) {
        Optional<Cavalier> cavalier = cavalierService.findById(id);
        cavalier.ifPresent(c -> cavalierService.delete(c));
        return "redirect:/cavaliers";
    }
}
