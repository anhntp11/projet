package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.entity.InfoBU;
import fr.orleans.m1s1miage.group4.backend.model.repository.InfoBURepository;

import java.util.List;

public class InfoBUService {
    private final InfoBURepository infoBURepository;

    public InfoBUService(InfoBURepository genreRepository) {this.infoBURepository = genreRepository;}

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de tous les genres sauvegardés en BD
     */
    public List<InfoBU> findAll() {
        return infoBURepository.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param infoBU L'infoBU a sauvegarder
     */
    public void Save(InfoBU infoBU) {
        infoBURepository.save(infoBU);
    }

}
