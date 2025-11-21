package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.DemandeDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.DemandeEmpruntDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.DemandeRetourDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeRetour;
import fr.orleans.m1s1miage.group4.backend.model.repository.DemandeEmpruntRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.DemandeRetourRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeService {

    private final DemandeEmpruntRepository empruntRepo;
    private final DemandeRetourRepository retourRepo;

    public DemandeService(DemandeEmpruntRepository empruntRepo, DemandeRetourRepository retourRepo) {
        this.empruntRepo = empruntRepo;
        this.retourRepo = retourRepo;
    }

    public List<DemandeDTO> getAllDemandes() {
        List<DemandeDTO> demandes = new ArrayList<>();

        // Emprunts
        for (DemandeEmprunt d : empruntRepo.findAll()) {
            demandes.add(new DemandeEmpruntDTO(
                    d.getId_DemandeEmprunt(),
                    d.getEtudiant().getIdEtudiant(),
                    d.getDateDemande(),
                    d.getStatut_Emprunt(),
                    d.getApprouverPar(),
                    d.getDateApprobationEmprunt(),
                    d.getLivre().getIdLivre()
            ));
        }

        // Retours
        for (DemandeRetour d : retourRepo.findAll()) {
            demandes.add(new DemandeRetourDTO(
                    d.getId_DemandeRetour(),
                    d.getEtudiant().getIdEtudiant(),
                    d.getDateDemande(),
                    d.getStatut_Emprunt(),
                    d.getApprouverPar(),
                    d.getDateApprobationEmprunt(),
                    d.getEmprunt().getId_Emprunt(),
                    d.isEst_renouvelle()
            ));
        }

        return demandes;
    }
}