package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BU")
public class BU extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @OneToMany(mappedBy = "bu", cascade = CascadeType.ALL)
    private Set<InfoBU> infos = new HashSet<>();

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

    public Long getId() {
        return id;
    }

    public Set<InfoBU> getInfos() {
        return infos;
    }

    public void setInfos(Set<InfoBU> infos) {
        this.infos = infos;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }
}