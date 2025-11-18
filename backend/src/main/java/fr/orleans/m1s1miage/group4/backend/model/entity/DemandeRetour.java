package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="DemandeRetour")
public class DemandeRetour extends DemandeAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_DemandeRetour;

    @ManyToOne
    @JoinTable(
            name = "emprunt",
            joinColumns = @JoinColumn(name = "id_DemandeRetour"),
            inverseJoinColumns = @JoinColumn(name = "id_Emprunt")
    )
    private Emprunt emprunt;

    private boolean est_renouvelle = false;

    // constructeurs
    public DemandeRetour() {
    }

    public DemandeRetour(Etudiant etudiant, LocalDateTime dateDemande, StatutEmprunt statut_Emprunt, Administrateur approuverPar, LocalDateTime dateApprobationEmprunt, Long id_DemandeRetour, Emprunt emprunt, boolean est_renouvelle) {
        super(etudiant, dateDemande, statut_Emprunt, approuverPar, dateApprobationEmprunt);
        this.id_DemandeRetour = id_DemandeRetour;
        this.emprunt = emprunt;
        this.est_renouvelle = est_renouvelle;
    }

    /// getters et setters

    public Long getId_DemandeRetour() {
        return id_DemandeRetour;
    }


    public Emprunt getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Emprunt emprunt) {
        this.emprunt = emprunt;
    }

    public boolean isEst_renouvelle() {
        return est_renouvelle;
    }

    public void setEst_renouvelle(boolean est_renouvelle) {
        this.est_renouvelle = est_renouvelle;
    }
}
