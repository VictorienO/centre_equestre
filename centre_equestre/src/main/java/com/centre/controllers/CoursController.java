package com.centre.controllers;

import com.centre.models.Cavalier;
import com.centre.models.Cours;
import com.centre.services.CavalierService;
import com.centre.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @Autowired
    private CavalierService cavalierService;

    @GetMapping
    public String listCours(Model model) {
        List<Cours> cours = coursService.findAll();
        model.addAttribute("cours", cours);
        return "cours/listCours";
    }

    @GetMapping("/addCours")
    public String addCoursForm(Model model) {
        model.addAttribute("cours", new Cours());
        return "cours/addCours";
    }

    @PostMapping("/addCours")
    public String addCours(@ModelAttribute Cours cours) {
        coursService.save(cours);
        return "redirect:/cours"; // Redirection vers la liste des cours
    }

    @GetMapping("/addMultipleCours")
    public String addMultipleCoursCSV(Model model) {
        return "cours/addMultipleCours";
    }

    @PostMapping("/addMultipleCours")
    public String addMultipleCours(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Cours cours = new Cours(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), data[6], null);
                coursService.save(cours);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/cours?error=ioException";
        }
        return "redirect:/cours";
    }

    @GetMapping("/editCours/{id}")
    public String editCoursForm(@PathVariable Long id, Model model) {
        Optional<Cours> cours = coursService.findById(id);
        model.addAttribute("cours", cours.orElse(new Cours())); // Si l'id n'existe pas, création d'un nouvel objet Cours
        return "cours/editCours";
    }

    @PostMapping("/editCours")
    public String editCours(@ModelAttribute Cours cours) {
        // Charger le cours existant à partir de la base de données
        Optional<Cours> existingCoursOptional = coursService.findById((long) cours.getId_cours());
        if (existingCoursOptional.isPresent()) {
            Cours existingCours = existingCoursOptional.get();
            // Copier la liste des cavaliers du cours existant dans le cours provenant du formulaire
            cours.setCavaliers(existingCours.getCavaliers());
            // Sauvegarder le cours modifié
            coursService.edit(cours);
            return "redirect:/cours"; // Redirection vers la liste des cours
        } else {
            // Gérer l'erreur si le cours n'existe pas
            return "redirect:/cours?error=coursNotFound";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCours(@PathVariable Long id) {
        Optional<Cours> cours = coursService.findById(id);
        cours.ifPresent(c -> coursService.delete(c));
        return "redirect:/cours";
    }

    @GetMapping("/{id}/cavaliers")
    public String listCavaliersCours(@PathVariable("id") Long id, Model model) {
        Optional<Cours> optionalCours = coursService.findById(id);
        if (optionalCours.isPresent()) {
            Cours cours = optionalCours.get();
            List<Cavalier> CavaliersCours = coursService.getCavaliersCours(cours);
            model.addAttribute("cavaliersCours", CavaliersCours);
            model.addAttribute("cours", cours);
            return "cours/listCavaliersCours";
        } else {
            // Gérer l'erreur si le cours n'existe pas
            return "redirect:/cours?error=coursNotFound";
        }
    }

}