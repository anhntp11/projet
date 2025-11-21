package fr.orleans.m1s1miage.group4.backend.model.entity;

import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import jakarta.persistence.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Livre")
public class Livre extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivre;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = true)
    private String langue;

    @Column(nullable = false)
    private String auteur;

    /**
     * Nombre d'unité en stock
     */
    @Column(nullable = false)
    private int stock;

    @ManyToMany
    @JoinTable(
            name = "genre_livre",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "bu_livre",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "bu_id")
    )
    private List<BU> bus = new ArrayList<>();


    // constructeurs
    public Livre() {
    }

    public Livre(String titre, String langue, String auteur, int stock) {
        this.titre = titre;
        this.langue = langue;
        this.auteur = auteur;
        this.stock = stock;
    }

    //getters setters

    public void setIdLivre(Long id) {
        this.idLivre = id;
    }

    public Long getIdLivre() {
        return idLivre;
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

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<BU> getBus() {
        return bus;
    }

    public void setBus(List<BU> bus) {
        this.bus = bus;
    }
}
