package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Etudiants")
public class Etudiant extends Utilsateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;

    @Column(nullable = false)
    private String numEtudiant;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String departement;

    //constructeur

    public Etudiant() {
    }

    public Etudiant(String email, String motDePasse, RoleUtilisateur role, String numEtudiant, String nom, String prenom, String departement) {
        super(email, motDePasse, role);
        this.numEtudiant = numEtudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.departement = departement;
    }

    //getters / setters


    public Long getIdEtudiant() {
        return idEtudiant;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
