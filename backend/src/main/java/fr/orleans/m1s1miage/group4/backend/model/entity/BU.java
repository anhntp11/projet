package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BU")
public class BU extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBU;

    @Column(nullable = false, unique = true)
    private String nom;

    @OneToOne(mappedBy = "bu", cascade = CascadeType.ALL)
    private InfoBU info;

    @ManyToMany(mappedBy = "bus")
    private Set<Livre> livres = new HashSet<>();

    //constructeurs
    public BU() {}
    public BU(String nom) {
        this.nom = nom;
    }

    // getters / setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getIdBU() {
        return idBU;
    }

    public InfoBU getInfos() {
        return info;
    }

    public void setInfos(InfoBU info) {
        this.info = info;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }
}