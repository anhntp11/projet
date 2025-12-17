package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.demande.DemandeDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.demande.DemandeEmpruntDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.demande.DemandeRetourDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeRetour;
import fr.orleans.m1s1miage.group4.backend.model.entity.StatutEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.repository.DemandeEmpruntRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.DemandeRetourRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void validerDemande(Long id) {

        if (empruntRepo.findById(id).isPresent()) {
            DemandeEmprunt userDemande = empruntRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Demande emprunt non trouvée"));

            userDemande.setStatut_Emprunt(StatutEmprunt.VALIDER);
            userDemande.setDateApprobationEmprunt(LocalDateTime.now());

            empruntRepo.save(userDemande);
            return;
        }

        if (retourRepo.findById(id).isPresent()) {
            DemandeRetour userDemande = retourRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Demande retour non trouvée"));

            userDemande.setStatut_Emprunt(StatutEmprunt.VALIDER);
            userDemande.setDateApprobationEmprunt(LocalDateTime.now());

            retourRepo.save(userDemande);
            return;
        }

        throw new RuntimeException("Aucune demande trouvée avec l'id : " + id);
    }


    public void rejeterDemande(Long id) {

        if (empruntRepo.findById(id).isPresent()) {
            DemandeEmprunt userDemande = empruntRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Demande emprunt non trouvée"));

            userDemande.setStatut_Emprunt(StatutEmprunt.REJETER);
            userDemande.setDateApprobationEmprunt(LocalDateTime.now());

            empruntRepo.save(userDemande);
            return;
        }

        if (retourRepo.findById(id).isPresent()) {
            DemandeRetour userDemande = retourRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Demande retour non trouvée"));

            userDemande.setStatut_Emprunt(StatutEmprunt.REJETER);
            userDemande.setDateApprobationEmprunt(LocalDateTime.now());

            retourRepo.save(userDemande);
            return;
        }

        throw new RuntimeException("Aucune demande trouvée avec l'id : " + id);
    }

}