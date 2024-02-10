package com.centre.repositories;

import com.centre.models.Cours;
import org.springframework.data.repository.CrudRepository;

public interface CoursRepo extends CrudRepository<Cours, Long> {
}
