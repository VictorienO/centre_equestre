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
            return "redirect:/cavaliers?error=ioException";
        }
        return "redirect:/cavaliers";
    }

    @GetMapping("/editCours/{id}")
    public String editCavalierForm(@PathVariable Long id, Model model) {
        Optional<Cours> cours = coursService.findById(id);
        model.addAttribute("cours", cours.orElse(new Cours())); // Si l'id n'existe pas, création d'un nouvel objet Cours
        return "cours/editCours";
    }

    @PostMapping("/editCours")
    public String editCours(@ModelAttribute Cours cours) {
        coursService.edit(cours);
        return "redirect:/cours"; // Redirection vers la liste des cours
    }

    @GetMapping("/delete/{id}")
    public String deleteCours(@PathVariable Long id) {
        Optional<Cours> cours = coursService.findById(id);
        cours.ifPresent(c -> coursService.delete(c));
        return "redirect:/cours";
    }

}