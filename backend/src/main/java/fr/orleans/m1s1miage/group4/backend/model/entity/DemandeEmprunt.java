package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="DemandeEmprunt")
public class DemandeEmprunt extends DemandeAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Demande;

    @ManyToOne
    @JoinTable(
            name="etudiant",
            joinColumns = @JoinColumn(name= "id_Demande"),
            inverseJoinColumns = @JoinColumn(name= "id_Etudiant")
    )
    private Etudiant etudiant;

    @ManyToOne
    @JoinTable(
            name = "livre",
            joinColumns = @JoinColumn(name = "id_Demande"),
            inverseJoinColumns = @JoinColumn(name = "id-Livre")
    )
    private Livre livre;

    @Column(nullable = false)
    private LocalDateTime dateDemande;

    @Column(nullable = false)
    private StatutEmprunt statut_Emprunt;

    @ManyToOne
    @JoinTable(
            name = "admin",
            joinColumns = @JoinColumn(name = "id_Demande"),
            inverseJoinColumns = @JoinColumn(name = "id_admin")
    )
    private Administrateur approuverPar;

    @Column(nullable = false)
    private LocalDateTime dateApprobationEmprunt;

    // constructeurs
    public DemandeEmprunt() {
    }

    public DemandeEmprunt(Etudiant etudiant, Livre livre, LocalDateTime dateDemande,
                          StatutEmprunt statut_Emprunt, Administrateur admin,
                          LocalDateTime dateApprobationEmprunt) {
        this.etudiant = etudiant;
        this.livre = livre;
        this.dateDemande = dateDemande;
        this.statut_Emprunt = statut_Emprunt;
        this.approuverPar = admin;
        this.dateApprobationEmprunt = dateApprobationEmprunt;
    }

    public Long getId_Demande() {
        return id_Demande;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public StatutEmprunt getStatut_Emprunt() {
        return statut_Emprunt;
    }

    public void setStatut_Emprunt(StatutEmprunt statut_Emprunt) {
        this.statut_Emprunt = statut_Emprunt;
    }

    public Administrateur getAdmin() {
        return approuverPar;
    }

    public void setAdmin(Administrateur admin) {
        this.approuverPar = admin;
    }

    public LocalDateTime getDateApprobationEmprunt() {
        return dateApprobationEmprunt;
    }

    public void setDateApprobationEmprunt(LocalDateTime dateApprobationEmprunt) {
        this.dateApprobationEmprunt = dateApprobationEmprunt;
    }
}
