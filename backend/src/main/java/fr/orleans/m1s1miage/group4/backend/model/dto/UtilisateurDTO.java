package fr.orleans.m1s1miage.group4.backend.model.dto;

import fr.orleans.m1s1miage.group4.backend.model.entity.RoleUtilisateur;

public class UtilisateurDTO {
    private String email;
    private RoleUtilisateur role;

    // Constructeur par défaut
    public UtilisateurDTO() {}

    // Constructeur complet
    public UtilisateurDTO(String email, RoleUtilisateur role) {
        this.email = email;
        this.role = role;
    }

    // Getters et setters
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
}
