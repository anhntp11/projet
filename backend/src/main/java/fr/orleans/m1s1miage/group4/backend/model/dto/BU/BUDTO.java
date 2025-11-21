package fr.orleans.m1s1miage.group4.backend.model.dto.BU;

import fr.orleans.m1s1miage.group4.backend.model.entity.BU;

public class BUDTO {
    private final Long id;
    private final String nom;
    private final Long idInfo;

    public BUDTO(Long id, String nom, Long idInfo) {
        this.id = id;
        this.nom = nom;
        this.idInfo = idInfo;
    }

    public BUDTO(BU bu) {
        this.id = bu.getIdBU();
        this.nom = bu.getNom();
        this.idInfo = bu.getInfos().getId();
    }

    public Long getId() {
        return id;
    }

    public Long getIdInfo() {
        return idInfo;
    }

    public String getNom() {
        return nom;
    }

}
