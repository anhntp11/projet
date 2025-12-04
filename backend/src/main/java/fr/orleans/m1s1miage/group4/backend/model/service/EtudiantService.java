package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {
    private final EtudiantRepository repo;

    public EtudiantService(EtudiantRepository repo) {
        this.repo = repo;
    }

    /**
     * Permet de sauvegarder l'étudiant dans la BD
     * @param etudiant L'étudiant à sauvegarder
     */
    public void save(Etudiant etudiant) {
        repo.save(etudiant);
    }
}
