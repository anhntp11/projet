package fr.orleans.m1s1miage.group4.backend.model.dto.BU;

public class BUCreationDTO {
    private String nom;

    public BUCreationDTO(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
