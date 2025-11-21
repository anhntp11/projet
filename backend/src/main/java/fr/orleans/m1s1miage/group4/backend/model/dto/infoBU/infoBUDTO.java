package fr.orleans.m1s1miage.group4.backend.model.dto.infoBU;

import fr.orleans.m1s1miage.group4.backend.model.entity.InfoBU;

public class infoBUDTO {
    private Long id;
    private String nom;
    private String informations;

    public infoBUDTO() {}

    public infoBUDTO(InfoBU infoBU) {
        this.id=infoBU.getId();
        this.informations=infoBU.getInformations();
        this.nom =infoBU.getNom();
    }

    public infoBUDTO(String inforamtions, String nom, Long id) {
        this.informations = inforamtions;
        this.nom = nom;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }
}
