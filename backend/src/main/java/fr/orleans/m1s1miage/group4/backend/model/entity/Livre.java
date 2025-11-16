package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Livre")

public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivre;
    private String titre;
    private String langue;
    private String edition;
    private String nomAuteur;
    private LocalDateTime tempsCreer;
    private LocalDateTime tempsMiseAJour;

    public Long getIdLivre() {
        return idLivre;
    }
    public void setIdLivre(Long idLivre) {
        this.idLivre = idLivre;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getLangue() {
        return langue;
    }
    public void setLangue(String langue) {
        this.langue = langue;
    }
    public String getEditon() {
        return edition;
    }
    public void setEditon(String edition) {
        this.edition = edition;
    }
    public String getNomAuteur() {
        this.nomAuteur = nomAuteur;
    }
    public LocalDateTime getTempsCreer() {
        return tempsCreer;
    }
    public LocalDateTime setTempsCreer(LocalDateTime tempsCreer) {
        this.tempsCreer = tempsCreer;
    }
    public LocalDateTime getTempsMiseAJour() {
        return tempsMiseAJour;
    }
    public void setTempsMiseAJour(LocalDateTime tempsMiseAJour) {
        this.tempsMiseAJour = tempsMiseAJour;
    }

    @Column(nullable = false)
    
}
