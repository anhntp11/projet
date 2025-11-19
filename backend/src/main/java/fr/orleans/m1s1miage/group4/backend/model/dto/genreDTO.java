package fr.orleans.m1s1miage.group4.backend.model.dto;

import java.util.Set;

public class genreDTO {
    private Long idGenre;
    private String name;
    private String description;
    private Set<Long> livresIds; // IDs des livres associés

    // Constructeur par défaut
    public genreDTO() {}

    // Constructeur complet
    public genreDTO(Long idGenre, String name, String description, Set<Long> livresIds) {
        this.idGenre = idGenre;
        this.name = name;
        this.description = description;
        this.livresIds = livresIds;
    }

    // Getters et setters
    public Long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Long idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getLivresIds() {
        return livresIds;
    }

    public void setLivresIds(Set<Long> livresIds) {
        this.livresIds = livresIds;
    }
}
