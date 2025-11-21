package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeRetour;
import fr.orleans.m1s1miage.group4.backend.model.entity.StatutEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.repository.DemandeRetourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeRetourService {
    private final DemandeRetourRepository demandeRetourRepository;

    public DemandeRetourService(DemandeRetourRepository demRetourRepository) {
        this.demandeRetourRepository = demRetourRepository;
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de toutes les demandes de retour sauvegardés en BD
     */
    public List<DemandeRetour> findAll() {
        return demandeRetourRepository.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param demandeRetour L'infoBU a sauvegarder
     */
    public void save(DemandeRetour demandeRetour) {
        demandeRetourRepository.save(demandeRetour);
    }
    public DemandeRetour validerDemande(Long id) {
        DemandeRetour demande =demandeRetourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        demande.setStatut_Emprunt(StatutEmprunt.EMPRUNTER);
        return demandeRetourRepository.save(demande);
    }

    public DemandeRetour rejeterDemande(Long id) {
        DemandeRetour demande = demandeRetourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        demande.setStatut_Emprunt(StatutEmprunt.RENDU);
        return demandeRetourRepository.save(demande);
    }

}