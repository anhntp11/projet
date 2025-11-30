package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Administrateur;
import fr.orleans.m1s1miage.group4.backend.model.exception.UtilisateurDejaExistantException;
import fr.orleans.m1s1miage.group4.backend.model.repository.AdministrateurRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurService {
    private final AdministrateurRepository administrateurRepository;
    private final EtudiantRepository etudiantRepository;

    public AdministrateurService(AdministrateurRepository administrateurRepository,
                                 EtudiantRepository etudiantRepository) {
        this.administrateurRepository = administrateurRepository;
        this.etudiantRepository = etudiantRepository;
    }

    /**
     * Compte le nombre d'Administrateurs
     * @return le nombre d'Administrateurs
     */
    public int getAdministrateurCount(){
        return administrateurRepository.findAll().size();
    }

    /**
     * Sauvegarde l'Administrateur dans la BD
     * @param administrateur l'Administrateur à sauvegarder
     * @throws UtilisateurDejaExistantException si un compte Utilisateur existe déjà avec cet email
     */
    public void save(Administrateur administrateur)
            throws UtilisateurDejaExistantException {
        if (administrateurRepository.findByEmail(administrateur.getEmail()).isPresent()
                || etudiantRepository.findByEmail(administrateur.getEmail()).isPresent()) {
            throw new UtilisateurDejaExistantException();
        } else  {
            administrateurRepository.save(administrateur);
        }
    }
}
