package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreControleur {
    private final LivreService livreService;

    public LivreControleur(LivreService livreService) {
        this.livreService = livreService;
    }

    /**
     * POST /livres
     * creer un nouveau livre
     */
    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre){
        Livre created = livreService.createLivre(livre);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /livres
     * recuperer des livre
     */
    @GetMapping
    public ResponseEntity<List<Livre>> getAllLivres(){
        return ResponseEntity.ok(livreService.findAll());
    }

    /**
     * GET /livres
     * recuperer un livre par son id
     *
     */
    @GetMapping("/{idLivre}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long idLivre){
        return ResponseEntity.ok(livreService.getLivreById(idLivre));
    }

    /**
     * PutMapping ("/{idLivre}")
     * Modifier un livre existant
     */
    @PutMapping("/{idLivre}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long idLivre,
                                             @RequestBody Livre livreModifie){
        Livre updated = livreService.updateLivre(idLivre, livreModifie);
        return ResponseEntity.ok(updated);
    }

    /**
     * DeleteMaping("/{idLivre}")
     * Supprimer un livre
     */
    public ResponseEntity<Void> deleteLivre(@PathVariable Long idLivre){
        livreService.deleteLivre(idLivre);
        return ResponseEntity.noContent().build();
    }

}
