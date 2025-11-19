package fr.orleans.m1s1miage.group4.backend.model.dto;

import fr.orleans.m1s1miage.group4.backend.model.entity.RoleUtilisateur;

public class AdministrateurDTO  {
    private Long idAdministrateur;
    private String email;
    private String motDePasse;
    private RoleUtilisateur role;

    // Constructeurs
    public AdministrateurDTO() {}

    public AdministrateurDTO(Long idAdministrateur, String email, String motDePasse, RoleUtilisateur role) {
        this.idAdministrateur = idAdministrateur;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et setters
    public Long getIdAdministrateur() {
        return idAdministrateur;
    }

    public void setIdAdministrateur(Long idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public RoleUtilisateur getRole() {
        return role;
    }

    public void setRole(RoleUtilisateur role) {
        this.role = role;
    }

}
