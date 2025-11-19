package fr.orleans.m1s1miage.group4.backend.model.dto;

import java.time.LocalDateTime;

public class DemandeDTO {
    private Long etudiantId;
    private LocalDateTime dateDemande;
    private String statutEmprunt; // ou enum StatutEmprunt
    private Long approuveParId;
    private LocalDateTime dateApprobationEmprunt;

    public DemandeDTO() {}

    public DemandeDTO(Long etudiantId, LocalDateTime dateDemande, String statutEmprunt,
                      Long approuveParId, LocalDateTime dateApprobationEmprunt) {
        this.etudiantId = etudiantId;
        this.dateDemande = dateDemande;
        this.statutEmprunt = statutEmprunt;
        this.approuveParId = approuveParId;
        this.dateApprobationEmprunt = dateApprobationEmprunt;
    }

    // Getters et setters
    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getStatutEmprunt() {
        return statutEmprunt;
    }

    public void setStatutEmprunt(String statutEmprunt) {
        this.statutEmprunt = statutEmprunt;
    }

    public Long getApprouveParId() {
        return approuveParId;
    }

    public void setApprouveParId(Long approuveParId) {
        this.approuveParId = approuveParId;
    }

    public LocalDateTime getDateApprobationEmprunt() {
        return dateApprobationEmprunt;
    }

    public void setDateApprobationEmprunt(LocalDateTime dateApprobationEmprunt) {
        this.dateApprobationEmprunt = dateApprobationEmprunt;
    }
}
