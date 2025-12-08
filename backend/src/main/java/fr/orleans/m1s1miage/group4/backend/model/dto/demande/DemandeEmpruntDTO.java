package fr.orleans.m1s1miage.group4.backend.model.dto.demande;

import fr.orleans.m1s1miage.group4.backend.model.entity.Administrateur;
import fr.orleans.m1s1miage.group4.backend.model.entity.StatutEmprunt;

import java.time.LocalDateTime;

public class DemandeEmpruntDTO extends DemandeDTO {
    private Long idDemandeEmprunt;
    private Long livreId; // ID du livre associé

    // Constructeur par défaut
    public DemandeEmpruntDTO(Long idDemandeEmprunt, Long idEtudiant, LocalDateTime dateDemande, StatutEmprunt statutEmprunt, Administrateur approuverPar, LocalDateTime dateApprobationEmprunt, Long idLivre) {}

    // Constructeur complet
    public DemandeEmpruntDTO(Long idDemandeEmprunt, Long etudiantId, LocalDateTime dateDemande,
                             String statutEmprunt, Long approuveParId, LocalDateTime dateApprobationEmprunt,
                             Long livreId) {
        super(etudiantId, dateDemande, statutEmprunt, approuveParId, dateApprobationEmprunt);
        this.idDemandeEmprunt = idDemandeEmprunt;
        this.livreId = livreId;
    }

    // Getters et setters
    public Long getIdDemandeEmprunt() {
        return idDemandeEmprunt;
    }

    public void setIdDemandeEmprunt(Long idDemandeEmprunt) {
        this.idDemandeEmprunt = idDemandeEmprunt;
    }

    public Long getLivreId() {
        return livreId;
    }

    public void setLivreId(Long livreId) {
        this.livreId = livreId;
    }
}
