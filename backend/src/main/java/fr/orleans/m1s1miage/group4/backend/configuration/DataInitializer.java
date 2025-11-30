package fr.orleans.m1s1miage.group4.backend.configuration;

import fr.orleans.m1s1miage.group4.backend.model.entity.Administrateur;
import fr.orleans.m1s1miage.group4.backend.model.entity.RoleUtilisateur;
import fr.orleans.m1s1miage.group4.backend.model.repository.AdministrateurRepository;
import fr.orleans.m1s1miage.group4.backend.model.service.AdministrateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private final AdministrateurService administrateurService;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(AdministrateurService administrateurService, PasswordEncoder passwordEncoder) {
        this.administrateurService = administrateurService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Créer un administrateur avec les infos suivantes :
     * Email : admin@bu.fr
     * MotDePasse : admin
     */
    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {
            if(administrateurService.getAdministrateurCount() == 0) {
                Administrateur administrateur = new Administrateur();
                administrateur.setEmail("admin@bu.fr");
                administrateur.setMotDePasse(passwordEncoder.encode("admin"));
                administrateur.setRole(RoleUtilisateur.ADMINISTRATEUR);

                administrateurService.save(administrateur);

                System.out.println("--> Init administrateur : Administrateur initial créé");
            }
        };
    }
}
