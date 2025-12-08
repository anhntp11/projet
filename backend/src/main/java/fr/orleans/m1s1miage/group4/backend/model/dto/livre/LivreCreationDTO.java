package fr.orleans.m1s1miage.group4.backend.model.dto.livre;

import java.util.List;

public class LivreCreationDTO {
    private String titre;
    private String langue;
    private String auteur;
    private int stock;
    private List<Long> genreIds;
    private List<Long> buIds;

    public LivreCreationDTO() {
    }

    public LivreCreationDTO(String titre, String langue, String auteur, int stock, List<Long> genreIds, List<Long> buIds) {
        this.titre = titre;
        this.langue = langue;
        this.auteur = auteur;
        this.stock = stock;
        this.genreIds = genreIds;
        this.buIds = buIds;
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

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Long> getBuIds() {
        return buIds;
    }

    public void setBuIds(List<Long> buIds) {
        this.buIds = buIds;
    }
}
