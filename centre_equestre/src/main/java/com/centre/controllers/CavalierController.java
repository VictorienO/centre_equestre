package com.centre.controllers;

import com.centre.models.Cavalier;
import com.centre.models.Cours;
import com.centre.services.CavalierService;
import com.centre.services.CoursService;
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

    @Autowired
    private CoursService coursService;

    @GetMapping
    public String listCavaliers(Model model) {
        List<Cavalier> cavaliers = cavalierService.findAll();
        model.addAttribute("cavaliers", cavaliers);
        return "cavaliers/listCavaliers";
    }

    @GetMapping("/addCavalier")
    public String addCavalierForm(Model model) {
        model.addAttribute("cavalier", new Cavalier());
        return "cavaliers/addCavalier";
    }

    @PostMapping("/addCavalier")
    public String addCavalier(@ModelAttribute Cavalier cavalier) {
        cavalierService.save(cavalier);
        return "redirect:/cavaliers"; // Redirection vers la liste des cavaliers
    }

    @GetMapping("/editCavalier/{id}")
    public String editCavalierForm(@PathVariable Long id, Model model) {
        Optional<Cavalier> cavalier = cavalierService.findById(id);
        model.addAttribute("cavalier", cavalier.orElse(new Cavalier())); // Si l'id n'existe pas, création d'un nouvel objet Cavalier
        return "cavaliers/editCavalier";
    }

    @PostMapping("/editCavalier")
    public String editCavalier(@ModelAttribute Cavalier cavalier) {
        cavalierService.edit(cavalier);
        return "redirect:/cavaliers"; // Redirection vers la liste des cavaliers
    }

    @GetMapping("/delete/{id}")
    public String deleteCavalier(@PathVariable Long id) {
        Optional<Cavalier> cavalier = cavalierService.findById(id);
        cavalier.ifPresent(c -> cavalierService.delete(c));
        return "redirect:/cavaliers";
    }

    @GetMapping("/{id}/sesCours")
    public String afficherCoursCavalier(@PathVariable("id") Long id, Model model) {
        Optional<Cavalier> optionalCavalier = cavalierService.findById(id);
        if (optionalCavalier.isPresent()) {
            Cavalier cavalier = optionalCavalier.get();
            List<Cours> coursCavalier = cavalierService.getCoursCavalier(cavalier);
            List<Cours> allCours = coursService.findAll();
            model.addAttribute("sesCours", coursCavalier);
            model.addAttribute("cavalier", cavalier);
            model.addAttribute("listCours", allCours);
            return "cavaliers/listCoursCavalier";
        } else {
            // Faire une pop up d'erreur !!
            return "redirect:/cavaliers";
        }
    }

    @PostMapping("/{id_cav}/edit/{id_cours}")
    public String addCours(@PathVariable("id_cav") Long idCav, @PathVariable("id_cours") Long idCours) {
        Optional<Cavalier> optionalCavalier = cavalierService.findById(idCav);
        Optional<Cours> optionalCours = coursService.findById(idCours);

        if (optionalCavalier.isPresent() && optionalCours.isPresent()) {
            Cavalier cavalier = optionalCavalier.get();
            Cours cours = optionalCours.get();

            // Ajouter le cours au cavalier
            cavalierService.addCours(cavalier, cours);

            // Redirection vers la page des cours du cavalier avec l'ID du cavalier
            return "redirect:/cavaliers/" + idCav + "/sesCours";
        } else {
            // Gérer l'erreur si le cavalier ou le cours n'existe pas
            return "redirect:/cavaliers";
        }
    }

}
