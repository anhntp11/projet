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
    public ResponseEntity<LivreDTO> createLivre(@RequestBody LivreCreationDTO creationDTO
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
        return ResponseEntity.status(HttpStatus.OK).body(livreService.findAll());
    }

    /**
     * GET /livres
     * recuperer un livre par son id
     *
     */
    @GetMapping("/{idLivre}")
    public ResponseEntity<LivreDTO> getLivreById(@PathVariable Long idLivre){
        LivreDTO livre = livreService.getLivreById(idLivre);
        return ResponseEntity.status(HttpStatus.OK).body(livre);
    }

    /**
     * PatchMapping ("/{idLivre}")
     * Modifier un livre existant
     */
    @PatchMapping("/{idLivre}")
    public ResponseEntity<LivreDTO> updateLivre(@PathVariable Long idLivre,
                                             @RequestBody LivreCreationDTO livreModifie){
        LivreDTO updated = livreService.updateLivre(idLivre, livreModifie);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    /**
     * DeleteMaping("/{idLivre}")
     * Supprimer un livre
     */
    @DeleteMapping("/{idLivre}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long idLivre) {
        livreService.deleteLivre(idLivre);
        return ResponseEntity.noContent().build(); //TODO
    }

    /**
     * PostMapping("/livre/{idLivre}/emprunt")
     * @param idLivre id du livre
     * @return Une reponse avec le DTO de l'emprunt cherché
     */
    @PostMapping("/livre/{idLivre}/emprunt")
    public ResponseEntity<EmpruntDTO> emprunterLivre(
            @PathVariable Long idLivre
            ) {

        String emailEtudiant = "mail temporaire";
        EmpruntDTO emprunt = EmpruntService.EmprunterLivre(idLivre, emailEtudiant);

        return ResponseEntity.ok(emprunt);
    }

}
