package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.exception.LivreInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LivreService {
    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
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
        livre.setIdLivre(null);
        livre.setTempsCreer(LocalDateTime.now());
        livre.setTempsMiseAJour(LocalDateTime.now());
        return livreRepository.save(livre);
    }

    /**
     * Mettre a jour un livre existant
     */
    public Livre updateLivre(Long idLivre, Livre livreModifie) {
        Livre existant = findById(idLivre);
        existant.setTitre(livreModifie.getTitre());
        existant.setLangue(livreModifie.getLangue());
        existant.setEditon(livreModifie.getEditon());
        existant.setNomAuteur(livreModifie.getNomAuteur());
        existant.setTempsMiseAJour(LocalDateTime.now());
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
}
