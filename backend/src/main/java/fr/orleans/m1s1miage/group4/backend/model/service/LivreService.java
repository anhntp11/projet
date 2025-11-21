package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.LivreCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.LivreDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.BU;
import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.exception.BuInconnueException;
import fr.orleans.m1s1miage.group4.backend.model.exception.GenreInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.exception.LivreInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.repository.LivreRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LivreService {
    private final LivreRepository livreRepository;
    private final BUService buService;
    private final GenreService genreService;

    public LivreService(
            LivreRepository livreRepository,
            BUService buService,
            GenreService genreService
    ) {
        this.livreRepository = livreRepository;
        this.buService = buService;
        this.genreService = genreService;
    }

    public void save(Livre livre) {
        livreRepository.save(livre);
    }

    /**
     * Récupérer tous les livres.
     */
    public List<LivreDTO> findAll() {
        List<Livre> livres = livreRepository.findAll();
        List<LivreDTO> dtos = livres.stream().map(LivreDTO::new).toList();
        return dtos;

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
     */
    public LivreDTO createLivre(LivreCreationDTO creationDTO) throws BuInconnueException, GenreInconnuException {
        Livre livre = new Livre();
        livre.setTitre(creationDTO.getTitre());
        livre.setLangue(creationDTO.getLangue());
        livre.setAuteur(creationDTO.getAuteur());
        livre.setStock(creationDTO.getStock());

        List<BU> bus = new ArrayList<>();
        for (Long id : creationDTO.getBuIds()){
            BU bu = buService.findById(id);
            bus.add(bu);
        }
        livre.setBus(bus);

        List<Genre> genres = new ArrayList<>();
        for (Long id : creationDTO.getGenreIds()){
            Genre genre = genreService.findById(id);
            genres.add(genre);
        }
        livre.setGenres(genres);

        livreRepository.save(livre);
        return new LivreDTO(livre);
    }

    /**
     * Mettre a jour un livre existant
     */
    public Livre updateLivre(Long idLivre, Livre livreModifie) {
        Livre existant = findById(idLivre);
        existant.setTitre(livreModifie.getTitre());
        existant.setLangue(livreModifie.getLangue());
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

    /**
     * Recupere un livre avec son ID
     * @param idLivre l'id du livre cherché
     * @return Le livre cherché
     * @throws LivreInconnuException si l'id donné n'appartient à aucun livre
     */
    public Livre getLivreById(Long idLivre) throws LivreInconnuException {
        return livreRepository.findById(idLivre)
                .orElseThrow(LivreInconnuException::new);
    }

    /**
     * Permet de chercher un livre via son titre
     *
     */
    public List<LivreDTO> chercherParTitre(String titre){
        return livreRepository.findByTitreContainingIgnoreCase(titre).stream()
                .map(LivreDTO::new).toList();
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
