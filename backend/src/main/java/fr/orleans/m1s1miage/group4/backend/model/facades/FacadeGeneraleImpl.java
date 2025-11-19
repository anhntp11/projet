package fr.orleans.m1s1miage.group4.backend.model.facades;

import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.exception.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacadeGeneraleImpl implements FacadeGenerale {
    @Override
    public int inscription(String email, String motDePasse, String numEtudiant, String nom, String prenom, String departement) throws InformationManquanteException, InformationIncorrecteException {
        return 0;
    }

    @Override
    public int connexion(String email, String motDePasse) throws InformationManquanteException, InformationIncorrecteException, UtilsateurInconnuException {
        return 0;
    }

    @Override
    public Livre getLivre(Long idLivre) throws LivreInconnuException {
        return null;
    }

    @Override
    public List<Livre> getCatalogue() {
        return List.of();
    }

    @Override
    public String infoBU(Long idBu) throws BuInconnueException {
        return "";
    }
}
