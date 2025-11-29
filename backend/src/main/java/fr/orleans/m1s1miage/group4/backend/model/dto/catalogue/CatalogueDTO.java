package fr.orleans.m1s1miage.group4.backend.model.dto.catalogue;

import fr.orleans.m1s1miage.group4.backend.model.dto.livre.LivreDTO;

import java.util.List;

public class CatalogueDTO {
    private List<LivreDTO> listLivresCatalogue;

    public CatalogueDTO() {}

    public CatalogueDTO(List<LivreDTO> livres) {
        this.listLivresCatalogue = livres;
    }

    public List<LivreDTO> getLivres() {
        return listLivresCatalogue;
    }

    public void setLivres(List<LivreDTO> livres) {
        this.listLivresCatalogue = livres;
    }
}
