package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Administrateur;
import fr.orleans.m1s1miage.group4.backend.model.repository.AdministrateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurService {
    private final AdministrateurRepository repo;

    public AdministrateurService(AdministrateurRepository repo) {
        this.repo = repo;
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de tous les administrateurs sauvegardés en BD
     */
    public List<Administrateur> findAll() {
        return repo.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param administrateur L'administrateur à sauvegarder
     */
    public void save(Administrateur administrateur) {
        repo.save(administrateur);
    }


}
