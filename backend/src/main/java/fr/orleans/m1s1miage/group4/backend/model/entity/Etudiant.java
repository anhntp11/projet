package fr.orleans.m1s1miage.group4.backend.model.entity;

import fr.orleans.m1s1miage.group4.backend.model.dto.utilisateur.EtudiantRegisterDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

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

    public Etudiant(EtudiantRegisterDTO etudiantRegisterDTO) {
        super(
                etudiantRegisterDTO.email(),
                etudiantRegisterDTO.motDePasse(),
                RoleUtilisateur.ETUDIANT
        );
        this.nom = etudiantRegisterDTO.nom();
        this.prenom = etudiantRegisterDTO.prenom();
        this.departement = etudiantRegisterDTO.departement();
        this.numEtudiant = etudiantRegisterDTO.numEtudiant();
    }

    //getters / setters


    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

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
