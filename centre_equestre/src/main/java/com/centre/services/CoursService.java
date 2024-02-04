package com.centre.services;

import com.centre.models.Cavalier;
import com.centre.models.Cours;
import com.centre.repositories.CavalierRepo;
import com.centre.repositories.CoursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CoursService {

    @Autowired
    private CoursRepo repository;

    public List<Cours> findAll() {
        return (List<Cours>) repository.findAll();
    }

    public void save(Cours cours) {  // pas testé avec persist !
        // repository.save(cours);
    }

    public Optional<Cours> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Cours cours) {
        repository.delete(cours);
    }
}
