package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.LivreDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.exception.LivreInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.repository.LivreRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LivreService {
    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public void save(Livre livre) {
        livreRepository.save(livre);
    }

    /**
     * Récupérer tous les livres.
     */
    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    /**
     * Récupérer un livre par son id.
     * @throws LivreInconnuException si aucun livre ne correcspond a l'id
     */
    public Livre findById(Long idLivre){
        return livreRepository.findById(idLivre)
                .orElseThrow(LivreInconnuException::new);
    }

    /**
     * Créer un nouveau livre.
     * On met à null l'id pour forcer JPA à faire un insert.
     */
    public Livre createLivre(Livre livre){
//        livre.setIdLivre(null);
//        livre.setTempsCreer(LocalDateTime.now());
//        livre.setTempsMiseAJour(LocalDateTime.now());
        return livreRepository.save(livre);
    }

    /**
     * Mettre a jour un livre existant
     */
    public Livre updateLivre(Long idLivre, Livre livreModifie) {
        Livre existant = findById(idLivre);
        existant.setTitre(livreModifie.getTitre());
        existant.setLangue(livreModifie.getLangue());
//        existant.setEditon(livreModifie.getEditon());
//        existant.setNomAuteur(livreModifie.getNomAuteur());
//        existant.setTempsMiseAJour(LocalDateTime.now());
        existant.setStock(livreModifie.getStock());

        return livreRepository.save(existant);
    }

    /**
     * Supprimer un livre.
     */
    public void deleteLivre(Long idLivre) throws LivreInconnuException {
        if (!livreRepository.existsById(idLivre)) {
            throw new LivreInconnuException();
        }
        livreRepository.deleteById(idLivre);
    }

    public Livre getLivreById(Long idLivre){
        return livreRepository.findById(idLivre)
                .orElseThrow(LivreInconnuException::new);
    }

    /**
     * Permet de chercher un livre via son titre
     *
     */
    public List<LivreDTO> chercherParTitre(String titre){
        List<LivreDTO> list= livreRepository.findByTitreContainingIgnoreCase(titre).stream()
                .map(LivreDTO::new).toList();
        return list;
    }

    /**
     * Permet de chercher un livre via son Auteur
     */
    public List<Livre> chercherParAuteur(String auteur){
        return livreRepository.findByAuteurContainingIgnoreCase(auteur);
    }

    /**
     * Permet de chercher un livre via son genre
     */
    public List<Livre> chercherParGenre(String genre){
        return livreRepository.findByGenre(genre);
    }


    /**
     * Fonction général pour la recherche de livre, par titre, auteur, genre
     * Idée de filtre pour le front (pouvoir filtrer par titre, auteur ou genre)
     */
    public List<LivreDTO> rechercherLivres(String titre, String auteur, String genre) {

        if (titre != null) {
            return chercherParTitre(titre);
        }
        if (auteur != null) {
            return chercherParAuteur(auteur)
                    .stream().map(LivreDTO::new).toList();
        }
        if (genre != null) {
            return chercherParGenre(genre)
                    .stream().map(LivreDTO::new).toList();
        }

        // Sinon renvoyer tout
        return livreRepository.findAll()
                .stream().map(LivreDTO::new).toList();
    }

}
