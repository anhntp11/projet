package fr.orleans.m1s1miage.group4.backend.model.dto.livre;

import fr.orleans.m1s1miage.group4.backend.model.entity.BU;
import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;

import java.util.ArrayList;
import java.util.List;

public class LivreDTO {

    private Long idLivre;
    private String titre;
    private String langue;
    private String auteur;
    private int stock;
    private List<Long> genreIds;
    private List<Long> buIds;

    public LivreDTO() {}

    public LivreDTO(Long idLivre, String titre, String langue, String auteur, int stock,
                    List<Long> genreIds, List<Long> buIds) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.langue = langue;
        this.auteur = auteur;
        this.stock = stock;
        this.genreIds = genreIds;
        this.buIds = buIds;
    }

    public LivreDTO (Livre livre) {
        this.idLivre = livre.getIdLivre();
        this.titre = livre.getTitre();
        this.langue = livre.getLangue();
        this.stock = livre.getStock();
        this.auteur = livre.getAuteur();

        List<Long> genreIds = new ArrayList<>();
        for (Genre genre : livre.getGenres()){
            genreIds.add(genre.getIdGenre());
        }
        this.genreIds = genreIds;

        List<Long> buIds = new ArrayList<>();
        for (BU bu : livre.getBus()){
            buIds.add(bu.getIdBU());
        }
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

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Long> getBuIds() {
        return buIds;
    }

    public void setBuIds(List<Long> buIds) {
        this.buIds = buIds;
    }
}
