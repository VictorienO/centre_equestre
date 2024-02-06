package com.centre.repositories;

import com.centre.models.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DBUserRepo extends JpaRepository<DBUser, Integer> {
    public DBUser findByUsername(String username);
}