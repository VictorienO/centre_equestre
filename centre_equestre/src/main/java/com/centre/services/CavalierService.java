package com.centre.services;

import com.centre.models.Cavalier;
import com.centre.models.Cours;
import com.centre.repositories.CavalierRepo;
import com.centre.repositories.CoursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class CavalierService {

    @Autowired
    private CavalierRepo repositoryCavalier;

    @Autowired
    private CoursRepo repositoryCours;

    public List<Cavalier> findAll() { return (List<Cavalier>) repositoryCavalier.findAll(); }

    public void save(Cavalier cavalier) {  // pas testé avec persist !
        repositoryCavalier.save(cavalier);
    }

    public void edit(Cavalier cavalier) { repositoryCavalier.save(cavalier); }

    public Optional<Cavalier> findById(Long id) {
        return repositoryCavalier.findById(id);
    }

    public void delete(Cavalier cavalier) {
        repositoryCavalier.delete(cavalier);
    }

    public List<Cours> getCoursCavalier(Cavalier cavalier) {
        return cavalier.getCours();
    }

    public void subscribeCoursCavaliers(Cavalier cavalier, Cours cours) {
        cavalier.getCours().add(cours); // Ajouter le cours à la liste des cours du cavalier
        repositoryCavalier.save(cavalier); // Enregistrer le cavalier avec le nouveau cours
    }

    public void unsubscribeCoursCavaliers(Cavalier cavalier, Cours cours) {
        cavalier.getCours().remove(cours); // Retirer le cours à la liste des cours du cavalier
        repositoryCavalier.save(cavalier); // Enregistrer le cavalier avec le nouveau cours
    }

}


