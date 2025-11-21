package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.Emprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.StatutEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.repository.DemandeEmpruntRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.EmpruntRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeEmpruntService {
    private final DemandeEmpruntRepository demandeEmpruntRepository;

    public DemandeEmpruntService(DemandeEmpruntRepository demEmpruntRepository) {
        this.demandeEmpruntRepository = demEmpruntRepository;
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de toutes les demandes d'emprunts sauvegardés en BD
     */
    public List<DemandeEmprunt> findAll() {
        return demandeEmpruntRepository.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param demandeEmprunt L'infoBU a sauvegarder
     */
    public void save(DemandeEmprunt demandeEmprunt) {
        demandeEmpruntRepository.save(demandeEmprunt);
    }

    public DemandeEmprunt validerDemande(Long id) {
        DemandeEmprunt demande = demandeEmpruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        demande.setStatut_Emprunt(StatutEmprunt.EMPRUNTER);
        return demandeEmpruntRepository.save(demande);
    }

    public DemandeEmprunt rejeterDemande(Long id) {
        DemandeEmprunt demande = demandeEmpruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        demande.setStatut_Emprunt(StatutEmprunt.RENDU);
        return demandeEmpruntRepository.save(demande);
    }
}
