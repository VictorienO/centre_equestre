package com.centre.services;

import com.centre.models.Cavalier;
import com.centre.models.Cours;
import com.centre.repositories.CavalierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class CavalierService {

    @Autowired
    private CavalierRepo repository;

    public List<Cavalier> findAll() { return (List<Cavalier>) repository.findAll(); }

    public void save(Cavalier cavalier) {  // pas testé avec persist !
        repository.save(cavalier);
    }

    public void edit(Cavalier cavalier) { repository.save(cavalier); }

    public Optional<Cavalier> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Cavalier cavalier) {
        repository.delete(cavalier);
    }

    public List<Cours> getCoursCavalier(Cavalier cavalier) {
        return cavalier.getCours();
    }

    public void addCours(Cavalier cavalier, Cours cours) {
        cavalier.getCours().add(cours); // Ajout du cours à la liste des cours du cavalier
        repository.save(cavalier); // Enregistrer le cavalier avec le nouveau cours
    }

}


