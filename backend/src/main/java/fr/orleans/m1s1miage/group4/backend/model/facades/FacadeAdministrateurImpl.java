package fr.orleans.m1s1miage.group4.backend.model.facades;

import fr.orleans.m1s1miage.group4.backend.model.dto.BU.BUCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.*;
import fr.orleans.m1s1miage.group4.backend.model.exception.*;
import fr.orleans.m1s1miage.group4.backend.model.repository.BURepository;
import fr.orleans.m1s1miage.group4.backend.model.service.BUService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacadeAdministrateurImpl implements FacadeAdministrateur{

    private final BUService BUService;

    public FacadeAdministrateurImpl(
            BUService buService
    ) {
        this.BUService = buService;
    }

    @Override
    public List<Emprunt> getTousLesEmprunts(StatutEmprunt statutEmprunt) {
        return List.of();
    }

    @Override
    public List<DemandeEmprunt> getDemandeEmprunts() {
        return List.of();
    }

    @Override
    public List<DemandeRetour> getDemandeRetours() {
        return List.of();
    }

    @Override
    public DemandeEmprunt getDemandeEmprunt(int idDemande) throws DemandeInconnueException {
        return null;
    }

    @Override
    public DemandeEmprunt getDemandeRetour(int idDemande) throws DemandeInconnueException {
        return null;
    }

    @Override
    public void validerDemande(DemandeAbstract demande) throws DemandeDejaTraiteeException {

    }

    @Override
    public void rejeterDemande(DemandeAbstract demande) throws DemandeDejaTraiteeException {

    }

    @Override
    public void supprimerLivre(int idLivre) throws LivreInconnuException {

    }

    @Override
    public void modifierInfoBU(int idBu, String infoBu) throws BuInconnueException {

    }

    @Override
    public void supprimerUtilisateur(int idUtilisateur) throws UtilsateurInconnuException, AdminSuppressionException {

    }

    @Override
    public void envoyerRappel(int idEmprunt) throws EmpruntInconnuException {

    }

    @Override
    public Utilsateur getUtilisateur(int idUtilisateur) throws UtilsateurInconnuException {
        return null;
    }

    @Override
    public Etudiant getEtudiant(int idEtudiant) throws UtilsateurInconnuException {
        return null;
    }

    public void creerBU(BUCreationDTO creationDTO) throws BuDejaExistateException {
        BU bu =
    }
}
