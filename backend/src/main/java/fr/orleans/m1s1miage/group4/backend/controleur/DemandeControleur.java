package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.dto.DemandeDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.DemandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DemandeControleur {

    private final DemandeService demandeService;

    public DemandeControleur(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @GetMapping("/demandes")
    public ResponseEntity<List<DemandeDTO>> getDemandes() {
        return ResponseEntity.ok(demandeService.getAllDemandes());
    }

    @PatchMapping("/demandes/{id}/validation")
    public ResponseEntity<Void> validerDemande(@PathVariable Long id) {
        // TODO: récupérer la demande et changer le statut + dateApprobation
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/demandes/{id}/rejet")
    public ResponseEntity<Void> rejeterDemande(@PathVariable Long id) {
        // TODO: récupérer la demande et changer le statut + dateApprobation
        return ResponseEntity.ok().build();
    }
}

