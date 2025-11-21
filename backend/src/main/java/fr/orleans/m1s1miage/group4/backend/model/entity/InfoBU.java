package fr.orleans.m1s1miage.group4.backend.model.entity;

import fr.orleans.m1s1miage.group4.backend.model.dto.infoBU.InfoBUCreationDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "InfoBU")
public class InfoBU extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInfoBU;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = true)
    private String informations;

    @OneToOne
    @JoinColumn(name = "bu_id")
    private BU bu;

    //Constructors

    public InfoBU() {
    }
    public InfoBU(String informations, String nom) {
        this.informations = informations;
        this.nom = nom;
    }

    // getters /setters


    public Long getId() {
        return idInfoBU;
    }

    public String getInformations() {
        return informations;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    public BU getBu() {
        return bu;
    }

    public void setBu(BU bu) {
        this.bu = bu;
    }
}
