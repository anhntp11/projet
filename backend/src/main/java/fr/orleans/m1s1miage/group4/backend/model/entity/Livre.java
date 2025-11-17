package fr.orleans.m1s1miage.group4.backend.model.entity;

import fr.orleans.m1s1miage.group4.backend.model.exception.LivreInconnuException;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Livre")
public class Livre extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column()
    private String langue;

    @Column(nullable = false)
    private String auteur;

    @ManyToMany
    @JoinTable(
            name = "genre_livre",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "bu_livre",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "bu_id")
    )
    private Set<BU> bus = new HashSet<>();

    /**
     * Nombre d'unité en stock
     */
    @Column(nullable = false)
    private int stock;

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

    public Long getId() {
        return id;
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<BU> getBus() {
        return bus;
    }

    public void setBus(Set<BU> bus) {
        this.bus = bus;
    }
}
