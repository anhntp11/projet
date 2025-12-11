package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.dto.emprunt.EmpruntDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.EmpruntService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class EmpruntControleur {
    private final EmpruntService empruntService;

    public EmpruntControleur(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    /**
     * PostMapping("/livre/{idLivre}/emprunt")
     * @param idLivre id du livre
     * @return Une reponse avec le DTO de l'emprunt cherché
     */
    @PostMapping("/livres/{idLivre}/emprunt")
    public ResponseEntity<EmpruntDTO> emprunterLivre(
            @PathVariable Long idLivre) {
        String emailEtudiant = "mail temporaire"; //TODO auth
        EmpruntDTO emprunt = empruntService.emprunterLivre(idLivre, emailEtudiant);

        return ResponseEntity.status(HttpStatus.CREATED).body(emprunt);
    }
}
