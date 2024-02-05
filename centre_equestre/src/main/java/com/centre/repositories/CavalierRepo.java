package com.centre.repositories;

import com.centre.models.Cavalier;
import org.springframework.data.repository.CrudRepository;

public interface CavalierRepo extends CrudRepository<Cavalier, Long> {
    // Ajout de méthodes personnalisées si nécessaire
}
