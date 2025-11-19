package fr.orleans.m1s1miage.group4.backend.model.dto;

public class InfoBuDTO {
    private Long idInfoBU;
    private String nom;
    private String informations;
    private Long buId; // ID de la BU associée

    // Constructeur par défaut
    public InfoBuDTO() {}

    // Constructeur complet
    public InfoBuDTO(Long idInfoBU, String nom, String informations, Long buId) {
        this.idInfoBU = idInfoBU;
        this.nom = nom;
        this.informations = informations;
        this.buId = buId;
    }

    // Getters et setters
    public Long getIdInfoBU() {
        return idInfoBU;
    }

    public void setIdInfoBU(Long idInfoBU) {
        this.idInfoBU = idInfoBU;
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

    public Long getBuId() {
        return buId;
    }

    public void setBuId(Long buId) {
        this.buId = buId;
    }
}
