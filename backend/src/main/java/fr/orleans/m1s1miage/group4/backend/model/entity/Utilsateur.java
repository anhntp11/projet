package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class Utilsateur extends BaseEntity {
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    private RoleUtilisateur role;

    //constructeurs
    public Utilsateur() {
    }
    public Utilsateur(String email, String motDePasse, RoleUtilisateur role) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    //getters / setters

    public boolean isAdmin() {
        return role == RoleUtilisateur.ADMINISTRATEUR;
    }

    public RoleUtilisateur getRole() {
        return role;
    }

    public void setRole(RoleUtilisateur role) {
        this.role = role;
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
}
