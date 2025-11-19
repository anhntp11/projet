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
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de tous les étudiants sauvegardés en BD
     */
    public List<Etudiant> findAll() {
        return repo.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param etudiant L'étudiant à sauvegarder
     */
    public void save(Etudiant etudiant) {
        repo.save(etudiant);
    }
}
