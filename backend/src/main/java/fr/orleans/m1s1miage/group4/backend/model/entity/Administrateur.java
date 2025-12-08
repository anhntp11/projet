package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Administrateur")
public class Administrateur extends Utilsateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrateur;

    // constructeur
    public Administrateur() {}

    public Administrateur(String email, String motDePasse, RoleUtilisateur role) {
        super(email, motDePasse, role);
    }

    //getters / setters
    public Long getIdAdministrateur() {
        return idAdministrateur;
    }
}
