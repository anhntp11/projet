package fr.orleans.m1s1miage.group4.backend.model.dto;

import java.time.LocalDateTime;

public class DemandeRetourDTO extends DemandeDTO {

    private Long idDemandeRetour;
    private Long empruntId; // ID de l'emprunt associé
    private boolean estRenouvelle;

    // Constructeur par défaut
    public DemandeRetourDTO() {}

    // Constructeur complet
    public DemandeRetourDTO(Long idDemandeRetour, Long etudiantId, LocalDateTime dateDemande,
                            String statutEmprunt, Long approuveParId, LocalDateTime dateApprobationEmprunt,
                            Long empruntId, boolean estRenouvelle) {
        super(etudiantId, dateDemande, statutEmprunt, approuveParId, dateApprobationEmprunt);
        this.idDemandeRetour = idDemandeRetour;
        this.empruntId = empruntId;
        this.estRenouvelle = estRenouvelle;
    }

    // Getters et setters
    public Long getIdDemandeRetour() {
        return idDemandeRetour;
    }

    public void setIdDemandeRetour(Long idDemandeRetour) {
        this.idDemandeRetour = idDemandeRetour;
    }

    public Long getEmpruntId() {
        return empruntId;
    }

    public void setEmpruntId(Long empruntId) {
        this.empruntId = empruntId;
    }

    public boolean isEstRenouvelle() {
        return estRenouvelle;
    }

    public void setEstRenouvelle(boolean estRenouvelle) {
        this.estRenouvelle = estRenouvelle;
    }
}
