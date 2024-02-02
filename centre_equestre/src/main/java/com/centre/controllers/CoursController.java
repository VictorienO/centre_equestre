package com.centre.controllers;

import com.centre.models.Cavalier;
import com.centre.models.Cours;
import com.centre.services.CavalierService;
import com.centre.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoursController {

    @Autowired
    CavalierService cavalierService;

    @Autowired
    CoursService coursService;

    @GetMapping("/mycours")
    public String getMyArticle(Model model) {
        Cavalier victorien = new Cavalier();
        Cavalier claire = new Cavalier();
        List<Cavalier> listeCav1 = new ArrayList<>();
        listeCav1.add(victorien);
        listeCav1.add(claire);
        Cours coursGalop = new Cours(1,"Galop", "03/02/2024", "14h30", 2.5F, 1, 8, "JeanJacques", listeCav1);
        model.addAttribute("mycours", coursGalop); // Ajout au modèle
        coursService.save(coursGalop);
        return "mycours"; //Envoi vers la vue
    }

    @GetMapping("/allMyCourses")
    public String getAllCourses(Model model) {
        List<Cours> allMyCourse = coursService.findAll();
        model.addAttribute("allMyCourses", allMyCourse);
        return "allMyCourses";
    }

}