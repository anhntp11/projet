package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.CatalogueDTO;
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
     * @return une liste avec tous les livres au format DTO
     */
    public List<LivreDTO> findAll() {
        List<Livre> livres = livreRepository.findAll();
        return livres.stream().map(LivreDTO::new).toList();

    }

    /**
     * Récupérer un livre par son id.
     * @throws LivreInconnuException si aucun livre ne correspond à l'id
     */
    public Livre findById(Long idLivre){
        return livreRepository.findById(idLivre)
                .orElseThrow(LivreInconnuException::new);
    }

    /**
     * Methode qui facilite le remplissage des champs d'un livre avec le contenu du DTO qui est lié, que ce soit pour la création ou pour l'edition.
     * @param livre Livre à remplir (nouveau ou celui à éditer)
     * @param ajout Dto du livre avec les infos à inscrire dans le livre
     * @return l'objet Livre complété.
     */
    private Livre remplirLivre(Livre livre, LivreCreationDTO ajout){
        livre.setTitre(ajout.getTitre());
        livre.setLangue(ajout.getLangue());
        livre.setAuteur(ajout.getAuteur());
        livre.setStock(ajout.getStock());
        List<BU> bus = new ArrayList<>();
        for (Long id : ajout.getBuIds()){
            BU bu = buService.findById(id);
            bus.add(bu);
        }
        livre.setBus(bus);
        List<Genre> genres = new ArrayList<>();
        for (Long id : ajout.getGenreIds()){
            Genre genre = genreService.findById(id);
            genres.add(genre);
        }
        livre.setGenres(genres);
        return livre;
    }

    /**
     * Créer un nouveau livre.
     * @param creationDTO Dto du nouveau livre
     * @return Un LivreDTO avec le contenu du livre nouvellement créé
     * @throws BuInconnueException si la BU n'est pas trouvé
     * @throws GenreInconnuException si le genre n'est pas trouvé
     */
    public LivreDTO createLivre(LivreCreationDTO creationDTO) throws BuInconnueException, GenreInconnuException {
        Livre livre = remplirLivre(new Livre(), creationDTO);

        livreRepository.save(livre);
        return new LivreDTO(livre);
    }

    /**
     * Mettre à jour un livre existant
     * @param idLivre l'id du livre a update
     * @param livreModifie le DTO avec les modifications a effectué
     * @return Le LivreDTO de l'objet modifié
     */
    public LivreDTO updateLivre(Long idLivre, LivreCreationDTO livreModifie) {
        Livre existant = remplirLivre(findById(idLivre), livreModifie);

        livreRepository.save(existant);
        return new LivreDTO(existant);
    }

    /**
     * Supprimer un livre.
     * @param idLivre l'id du Livre à supprimer
     * @throws LivreInconnuException si le Livre n'est pas trouvé
     */
    public void deleteLivre(Long idLivre) throws LivreInconnuException {
        if (!livreRepository.existsById(idLivre)) {
            throw new LivreInconnuException();
        }
        livreRepository.deleteById(idLivre);
    }

    /**
     * Recupère un livre avec son ID
     * @param idLivre l'id du livre cherché
     * @return Le livre cherché
     * @throws LivreInconnuException si l'id donné n'appartient à aucun livre
     */
    public LivreDTO getLivreById(Long idLivre) throws LivreInconnuException {
        return new LivreDTO(livreRepository.findById(idLivre)
                .orElseThrow(LivreInconnuException::new));
    }

    /**
     * Permet de chercher un livre via son titre
     * @param titre titre du livre
     * @return Une liste de LivreDTO correspondant à la recherche
     */
    public List<LivreDTO> chercherParTitre(String titre){
        return livreRepository.findByTitreContainingIgnoreCase(titre).stream()
                .map(LivreDTO::new).toList();
    }

    /**
     * Permet de chercher un livre via son Auteur
     * @param auteur nom de l'auteur du Livre
     * @return Une liste de LivreDTO correspondant à la recherche
     */
    public List<LivreDTO> chercherParAuteur(String auteur){
        return livreRepository.findByAuteurContainingIgnoreCase(auteur).stream()
                .map(LivreDTO::new).toList();
    }

    /**
     * Permet de chercher un livre via son genre
     * @param genre Genre du livre
     * @return Une Liste de
     */
    public List<LivreDTO> chercherParGenre(String genre){
        return livreRepository.findByGenre(genre).stream().map(LivreDTO::new).toList();
    }


    /**
     * Fonction général pour la recherche de livre, par titre, auteur, genre
     * Idée de filtre pour le front (pouvoir filtrer par titre, auteur ou genre)
     * @param titre Titre du livre
     * @param auteur Auteur du livre
     * @param genre Genre du livre
     * @return Une liste de LivreDTO des livres trouvés
     */
    public List<LivreDTO> rechercherLivres(String titre, String auteur, String genre) {

        if (titre != null) {
            return chercherParTitre(titre);
        }
        if (auteur != null) {
            return chercherParAuteur(auteur);
        }
        if (genre != null) {
            return chercherParGenre(genre);
        }

        // Sinon renvoyer tout
        return livreRepository.findAll()
                .stream().map(LivreDTO::new).toList();
    }

    /**
     * Recupère le catalogue
     * @return Un CatalogueDTO du catalogue
     */
    public CatalogueDTO getCatalogue() {
        List<LivreDTO> livres = livreRepository.findAll()
                .stream()
                .map(LivreDTO::new)
                .toList();

        return new CatalogueDTO(livres);
    }


}
