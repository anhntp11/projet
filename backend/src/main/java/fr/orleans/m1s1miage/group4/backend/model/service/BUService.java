package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.BU;
import fr.orleans.m1s1miage.group4.backend.model.repository.BURepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BUService {
    private final BURepository repo;

    public BUService(BURepository repo) {
        this.repo = repo;
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de tous les genres sauvegardés en BD
     */
    public List<BU> findAll() {
        return repo.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param bu La BU à sauvegarder
     */
    public void Save(BU bu) {
        repo.save(bu);
    }

}
