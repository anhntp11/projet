package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.dto.LivreDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogue")
public class CatalogueController {

    private final LivreService livreService;

    public CatalogueController(LivreService livreService){
        this.livreService=livreService;
    }

    @GetMapping
    public ResponseEntity<List<LivreDTO>> rechercherLivres(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String auteur,
            @RequestParam(required = false) String genre) {
        List<LivreDTO> result = livreService.rechercherLivres(titre, auteur, genre);
        return ResponseEntity.ok(result);

    }

}
