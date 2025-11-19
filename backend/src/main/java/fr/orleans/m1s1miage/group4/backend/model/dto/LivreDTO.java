package fr.orleans.m1s1miage.group4.backend.model.dto;

import java.util.Set;

public class LivreDTO {

    private Long idLivre;
    private String titre;
    private String langue;
    private String auteur;
    private int stock;
    private Set<Long> genreIds;
    private Set<Long> buIds;

    public LivreDTO() {}

    public LivreDTO(Long idLivre, String titre, String langue, String auteur, int stock,
                    Set<Long> genreIds, Set<Long> buIds) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.langue = langue;
        this.auteur = auteur;
        this.stock = stock;
        this.genreIds = genreIds;
        this.buIds = buIds;
    }

    // Getters et setters
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

    public Set<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(Set<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public Set<Long> getBuIds() {
        return buIds;
    }

    public void setBuIds(Set<Long> buIds) {
        this.buIds = buIds;
    }
}
