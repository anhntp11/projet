package fr.orleans.m1s1miage.group4.backend.configuration;

import fr.orleans.m1s1miage.group4.backend.model.entity.Administrateur;
import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.entity.RoleUtilisateur;
import fr.orleans.m1s1miage.group4.backend.model.repository.AdministrateurRepository;
import fr.orleans.m1s1miage.group4.backend.model.service.AdministrateurService;
import fr.orleans.m1s1miage.group4.backend.model.service.EtudiantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class DataInitializer {

    private final AdministrateurService administrateurService;
    private final EtudiantService etudiantService;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(AdministrateurService administrateurService, EtudiantService etudiantService, PasswordEncoder passwordEncoder) {
        this.administrateurService = administrateurService;
        this.etudiantService = etudiantService;
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

                System.out.println("--> Init administrateur : Administrateur initial créé (admin@bu.fr admin)");
            }
        };
    }

    /**
     * Créer un etudiant avec les infos suivantes :
     * Email : etu@bu.fr
     * MotDePasse : etu1
     */
    @Bean
    public CommandLineRunner initEtudiant() {
        return args -> {
            if(etudiantService.getEtudiantCount() == 0) {
                Etudiant etudiant = new Etudiant();
                etudiant.setEmail("etu@bu.fr");
                etudiant.setMotDePasse(passwordEncoder.encode("etu1"));
                etudiant.setRole(RoleUtilisateur.ETUDIANT);

                etudiant.setNumEtudiant("22501234");
                etudiant.setDepartement("Informatique");
                etudiant.setNotifications(new ArrayList<>());
                etudiant.setPrenom("John");
                etudiant.setNom("Doe");

                etudiantService.save(etudiant);

                System.out.println("--> Init etudiant : Etudiant initial créé (etu@bu.fr etu1)");
            }
        };
    }
}
