package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.facades.FacadeLivre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
//@CrossOrigin

public class LivreControleur {
    private final FacadeLivre facadeLivre;

    public LivreControleur(FacadeLivre facadeLivre) {
        this.facadeLivre = facadeLivre;
    }

    /**
     * POST /livres
     * creer un nouveau livre
     */
    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre){
        Livre created = facadeLivre.createLivre(livre);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /livres
     * recuperer des livre
     */
    @GetMapping
    public ResponseEntity<List<Livre>> getAllLivres(){
        return ResponseEntity.ok(facadeLivre.getAllLivres());
    }

    /**
     * GET /livres
     * recuperer un livre par son id
     */
    @GetMapping("/{idLivre}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long idLivre){
        return ResponseEntity.ok(facadeLivre.getLivre(idLivre));
    }

    /**
     * PutMapping ("/{idLivre}")
     * Modifier un livre existant
     */
    @PutMapping("/{idLivre}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long idLivre,
                                             @RequestBody Livre livreModifie){
        Livre updated = facadeLivre.updateLivre(idLivre, livreModifie);
        return ResponseEntity.ok(updated);
    }

    /**
     * DeleteMaping("/{idLivre}")
     * Supprimer un livre
     */
    public ResponseEntity<Void> deleteLivre(@PathVariable Long idLivre){
        facadeLivre.deleteLivre(idLivre);
        return ResponseEntity.noContent().build();
    }

}
