package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Emprunt")

public class Emprunt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Emprunt;

    @ManyToOne
    @JoinTable(
            name="etudiant",
            joinColumns = @JoinColumn(name= "id_Emprunt"),
            inverseJoinColumns = @JoinColumn(name= "id_Etudiant")
    )
    private Etudiant etudiant;

    @ManyToOne
    @JoinTable(
            name = "livre",
            joinColumns = @JoinColumn(name = "id_Emprunt"),
            inverseJoinColumns = @JoinColumn(name = "id-Livre")
    )
    private Livre livre;

    @ManyToOne
    @JoinTable(
            name = "bu_livre",
            joinColumns = @JoinColumn(name = "id_Emprunt"),
            inverseJoinColumns = @JoinColumn(name = "bu_id")
    )
    private BU bu;

    /**
     * Nombre d'unité en stock
     */
    @Column(nullable = false)
    private LocalDateTime dateEmprunt;

    private LocalDateTime dateRetour;

    @Column(nullable = false)
    private LocalDateTime dateEcheance;

    @Column(nullable = false)
    private StatutEmprunt statut_Emprunt;

    @Column(nullable = false)
    private boolean est_renouvelle = false;

    @ManyToOne
    @JoinTable(
            name = "admin",
            joinColumns = @JoinColumn(name = "id_Emprunt"),
            inverseJoinColumns = @JoinColumn(name = "id_admin")
    )
    private Administrateur admin;

    // constructeurs
    public Emprunt() {
    }

    public Emprunt(Etudiant etudiant, Livre livre, BU bu,
                   LocalDateTime dateEmprunt, LocalDateTime dateEcheance,
                   StatutEmprunt statut_Emprunt, Administrateur admin) {
        this.etudiant = etudiant;
        this.livre = livre;
        this.bu = bu;
        this.dateEmprunt = dateEmprunt;
        this.dateEcheance = dateEcheance;
        this.statut_Emprunt = statut_Emprunt;
        this.admin = admin;
    }
    //getters setters


    public Long getId_Emprunt() {
        return id_Emprunt;
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

    public BU getBu() {
        return bu;
    }

    public void setBu(BU bu) {
        this.bu = bu;
    }

    public LocalDateTime getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDateTime dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDateTime getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDateTime dateRetour) {
        this.dateRetour = dateRetour;
    }

    public LocalDateTime getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDateTime dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public StatutEmprunt getStatut_Emprunt() {
        return statut_Emprunt;
    }

    public void setStatut_Emprunt(StatutEmprunt statut_Emprunt) {
        this.statut_Emprunt = statut_Emprunt;
    }

    public boolean isEst_renouvelle() {
        return est_renouvelle;
    }

    public void setEst_renouvelle(boolean est_renouvelle) {
        this.est_renouvelle = est_renouvelle;
    }

    public Administrateur getAdmin() {
        return admin;
    }

    public void setAdmin(Administrateur admin) {
        this.admin = admin;
    }
}
