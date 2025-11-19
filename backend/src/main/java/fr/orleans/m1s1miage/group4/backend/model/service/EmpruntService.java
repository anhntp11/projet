package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Emprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.InfoBU;
import fr.orleans.m1s1miage.group4.backend.model.repository.EmpruntRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.InfoBURepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpruntService {
    private final EmpruntRepository empruntRepository;

    public EmpruntService(EmpruntRepository empRepository) {this.empruntRepository = empRepository;}

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de tous les emprunts sauvegardés en BD
     */
    public List<Emprunt> findAll() {
        return empruntRepository.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param emprunt L'infoBU a sauvegarder
     */
    public void save(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }

}
