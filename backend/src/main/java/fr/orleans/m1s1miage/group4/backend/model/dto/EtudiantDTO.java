package fr.orleans.m1s1miage.group4.backend.model.dto;

import fr.orleans.m1s1miage.group4.backend.model.entity.RoleUtilisateur;

import java.util.Set;

public class EtudiantDTO {
    private Long idEtudiant;
    private String numEtudiant;
    private String nom;
    private String prenom;
    private String departement;
    private String email;
    private RoleUtilisateur role;
    private Set<Long> notificationsIds; // IDs des notifications associées

    // Constructeur par défaut
    public EtudiantDTO() {}

    // Constructeur complet
    public EtudiantDTO(Long idEtudiant, String numEtudiant, String nom, String prenom,
                       String departement, String email, RoleUtilisateur role, Set<Long> notificationsIds) {
        this.idEtudiant = idEtudiant;
        this.numEtudiant = numEtudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.departement = departement;
        this.email = email;
        this.role = role;
        this.notificationsIds = notificationsIds;
    }

    // Getters et setters
    public Long getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Long idEtudiant) {
        this.idEtudiant = idEtudiant;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleUtilisateur getRole() {
        return role;
    }

    public void setRole(RoleUtilisateur role) {
        this.role = role;
    }

    public Set<Long> getNotificationsIds() {
        return notificationsIds;
    }

    public void setNotificationsIds(Set<Long> notificationsIds) {
        this.notificationsIds = notificationsIds;
    }
}
