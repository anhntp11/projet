package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class DemandeAbstract {
    @ManyToOne
    @JoinTable(
            name="etudiant",
            joinColumns = @JoinColumn(name= "id_Demande"),
            inverseJoinColumns = @JoinColumn(name= "id_Etudiant")
    )
    private Etudiant etudiant;

    @Column(nullable = false)
    private LocalDateTime dateDemande;

    @Column(nullable = false)
    private StatutEmprunt statutEmprunt;

    @ManyToOne
    @JoinTable(
            name = "admin",
            joinColumns = @JoinColumn(name = "id_Demande"),
            inverseJoinColumns = @JoinColumn(name = "id_admin")
    )
    private Administrateur approuverPar;

    @Column(nullable = false)
    private LocalDateTime dateApprobationEmprunt;

    //constructeurs
    public DemandeAbstract() {
    }

    public DemandeAbstract(Etudiant etudiant, LocalDateTime dateDemande,
                           StatutEmprunt statut_Emprunt, Administrateur approuverPar,
                           LocalDateTime dateApprobationEmprunt) {
        this.etudiant = etudiant;
        this.dateDemande = dateDemande;
        this.statutEmprunt = statut_Emprunt;
        this.approuverPar = approuverPar;
        this.dateApprobationEmprunt = dateApprobationEmprunt;
    }
    //getters et setters

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public StatutEmprunt getStatut_Emprunt() {
        return statutEmprunt;
    }

    public void setStatut_Emprunt(StatutEmprunt statut_Emprunt) {
        this.statutEmprunt = statut_Emprunt;
    }

    public Administrateur getApprouverPar() {
        return approuverPar;
    }

    public void setApprouverPar(Administrateur approuverPar) {
        this.approuverPar = approuverPar;
    }

    public LocalDateTime getDateApprobationEmprunt() {
        return dateApprobationEmprunt;
    }

    public void setDateApprobationEmprunt(LocalDateTime dateApprobationEmprunt) {
        this.dateApprobationEmprunt = dateApprobationEmprunt;
    }
    public void setStatutEmprunt(StatutEmprunt statut) {
        this.statutEmprunt = statut;
    }

}
