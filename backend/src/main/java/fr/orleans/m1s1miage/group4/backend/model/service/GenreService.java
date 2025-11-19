package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de tous les genres sauvegardés en BD
     */
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param genre Le genre a sauvegarder
     */
    public void save(Genre genre) {
        genreRepository.save(genre);
    }






    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     */
    public Genre findFirst() {
        return genreRepository.findAll().get(0);
    }

}
