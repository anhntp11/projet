package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.dto.EmpruntDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.LivreCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.LivreDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.service.EmpruntService;
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
    public ResponseEntity<LivreDTO> createLivre(
            @RequestBody LivreCreationDTO creationDTO
    ){
        LivreDTO created = livreService.createLivre(creationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /livres
     * recuperer des livre
     */
    @GetMapping
    public ResponseEntity<List<LivreDTO>> getAllLivres(){
        return ResponseEntity.ok(livreService.findAll());
    }

    /**
     * GET /livres
     * recuperer un livre par son id
     *
     */
    @GetMapping("/{idLivre}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long idLivre){
        Livre livre = livreService.getLivreById(idLivre);
        return ResponseEntity.status(HttpStatus.OK).body(livre);
    }

    /**
     * PatchMapping ("/{idLivre}")
     * Modifier un livre existant
     */
    @PatchMapping("/{idLivre}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long idLivre,
                                             @RequestBody Livre livreModifie){
        Livre updated = livreService.updateLivre(idLivre, livreModifie);
        return ResponseEntity.ok(updated);
    }

    /**
     * DeleteMaping("/{idLivre}")
     * Supprimer un livre
     */
    public ResponseEntity<Void> deleteLivre(@PathVariable Long idLivre) {
        livreService.deleteLivre(idLivre);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/livre/{idLivre}/emprunt")
    public ResponseEntity<EmpruntDTO> emprunterLivre(
            @PathVariable Long idLivre
            ) {

        String emailEtudiant = "mail temporaire";
        EmpruntDTO emprunt = EmpruntService.EmprunterLivre(idLivre, emailEtudiant);

        return ResponseEntity.ok(emprunt);
    }

}
