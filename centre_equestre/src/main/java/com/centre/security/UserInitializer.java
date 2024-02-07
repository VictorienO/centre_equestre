package com.centre.security;

import com.centre.models.DBUser;
import com.centre.repositories.DBUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private DBUserRepo dbUserRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Vérifiez si les utilisateurs existent déjà dans la base de données
        if (dbUserRepo.count() == 0) {
            // Créez les utilisateurs par défaut et sauvegardez-les dans la base de données
            DBUser user = new DBUser();
            user.setUsername("dbuser");
            user.setPassword(passwordEncoder.encode("user")); // Encodez le mot de passe
            user.setRole("USER");
            dbUserRepo.save(user);

            DBUser admin = new DBUser();
            admin.setUsername("dbadmin");
            admin.setPassword(passwordEncoder.encode("admin")); // Encodez le mot de passe
            admin.setRole("ADMIN");
            dbUserRepo.save(admin);
        }
    }
}
