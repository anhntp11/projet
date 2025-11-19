package fr.orleans.m1s1miage.group4.backend.model.dto;

import java.util.Set;

public class BuDTO {
    private Long idBU;
    private String nom;
    private Set<Long> infosIds;   // IDs des InfoBU associés
    private Set<Long> livresIds;  // IDs des Livre associés

    // Constructeur par défaut
    public BuDTO() {}

    // Constructeur avec tous les champs
    public BuDTO(Long idBU, String nom, Set<Long> infosIds, Set<Long> livresIds) {
        this.idBU = idBU;
        this.nom = nom;
        this.infosIds = infosIds;
        this.livresIds = livresIds;
    }

    // Getters et setters
    public Long getIdBU() {
        return idBU;
    }

    public void setIdBU(Long idBU) {
        this.idBU = idBU;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Long> getInfosIds() {
        return infosIds;
    }

    public void setInfosIds(Set<Long> infosIds) {
        this.infosIds = infosIds;
    }

    public Set<Long> getLivresIds() {
        return livresIds;
    }

    public void setLivresIds(Set<Long> livresIds) {
        this.livresIds = livresIds;
    }
}
