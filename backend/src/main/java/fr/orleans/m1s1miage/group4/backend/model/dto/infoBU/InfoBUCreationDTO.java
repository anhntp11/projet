package fr.orleans.m1s1miage.group4.backend.model.dto.infoBU;

public class InfoBUCreationDTO {

    private String nom;
    private String informations;
    private Long idBu;

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

    public Long getIdBu() {
        return idBu;
    }

    public void setIdBu(Long idBu) {
        this.idBu = idBu;
    }

    public InfoBUCreationDTO() {
    }

    public InfoBUCreationDTO(String nom, String informations, Long idBu) {
        this.nom = nom;
        this.informations = informations;
        this.idBu = idBu;
    }
}
